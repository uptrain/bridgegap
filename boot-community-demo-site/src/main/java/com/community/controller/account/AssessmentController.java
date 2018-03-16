package com.community.controller.account;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.broadleafcommerce.common.web.BroadleafRequestContext;
import org.broadleafcommerce.common.web.controller.BroadleafAbstractController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.WebRequest;

import com.bridgegap.assessment.Assessment;
import com.bridgegap.assessment.AssessmentDataForm;
import com.bridgegap.course.Answer;
import com.bridgegap.course.Question;
import com.bridgegap.course.QuestionImpl;
import com.bridgegap.course.Questionnaire;

@Controller
@RequestMapping("/assessment")
public class AssessmentController extends BroadleafAbstractController {

	@PersistenceContext(unitName = "blPU")
	protected EntityManager em;

	@RequestMapping(method = RequestMethod.GET)
	public String getAssessment(HttpServletRequest request, HttpServletResponse response, Model model,
			@ModelAttribute("assessmentData") AssessmentDataForm assessmentData) {
		String lQuestionnaireId = request.getParameter("questionnaireId");
		Assessment lAssessment = getAssessment(lQuestionnaireId);
		if (lAssessment != null) {
			WebRequest webRequest = BroadleafRequestContext.getBroadleafRequestContext().getWebRequest();
			Integer nextQue = getNextQuestion(lAssessment);
			if (nextQue == 9999) {
				assessmentData.setEndOfAssessment(true);
				float score = (lAssessment.getNumOfQuestAnsweredCorrectly() / lAssessment.getQuestionnaire().getQuestions().size()) * 100;
				lAssessment.setScore(score);
				assessmentData.setScore(score);
			} else {
				lAssessment.getAskedQuestions().add(nextQue);
				Question lNextQue = lAssessment.getQuestionnaire().getQuestions().get(nextQue);
				assessmentData.setQuestion(lNextQue);
				webRequest.setAttribute("currentQuestion", lNextQue, WebRequest.SCOPE_SESSION);
			}
		}
		String lView = "program/assessment";
		return lView;
	}

	@RequestMapping(method = RequestMethod.POST)
	public String handleAnswer(HttpServletRequest request, HttpServletResponse response, Model model,
			@ModelAttribute("assessmentData") AssessmentDataForm assessmentData) {

		WebRequest webRequest = BroadleafRequestContext.getBroadleafRequestContext().getWebRequest();
		QuestionImpl question = (QuestionImpl) webRequest.getAttribute("currentQuestion", WebRequest.SCOPE_SESSION);
		String lQuestionnaireId = request.getParameter("questionnaireId");
		Assessment lAssessment = getAssessment(lQuestionnaireId);
		boolean answeredCorrectly = false;
		if (question != null) {
			List<Answer> answers = question.getAnswers();
			for (Answer answer : answers) {
				if (answer.isCorrectAnswer()) {
					if (assessmentData.getAnswers().contains(answer.getName())) {
						answeredCorrectly = true;
					} else {
						answeredCorrectly = false;
					}
				}
			}
		}

		if (answeredCorrectly) {
			lAssessment.setNumOfQuestAnsweredCorrectly(lAssessment.getNumOfQuestAnsweredCorrectly() + 1);
		}
		return "redirect:/assessment?questionnaireId=" + lQuestionnaireId;
	}

	private int getNextQuestion(Assessment lAssessment) {

		int nextQue = 0;
		if (lAssessment.getAskedQuestions().size() >= lAssessment.getQuestionnaire().getQuestions().size()) {
			nextQue = 9999;
		} else {
			Questionnaire lQuestions = lAssessment.getQuestionnaire();
			Random random = new Random();
			nextQue = random.nextInt((lQuestions.getQuestions().size()));
			if (lAssessment.getAskedQuestions().contains(new Integer(nextQue))) {
				nextQue = getNextQuestion(lAssessment);
			}
		}
		return nextQue;
	}

	private Assessment getAssessment(String pQuestionnireId) {

		Assessment lAssessment = null;
		WebRequest webRequest = BroadleafRequestContext.getBroadleafRequestContext().getWebRequest();
		Object lAssessmentList = webRequest.getAttribute("assessments", WebRequest.SCOPE_SESSION);
		if (lAssessmentList == null) {
			List<Questionnaire> lListOfQuestionnaire = findQuestionnaire(pQuestionnireId);
			Questionnaire lQuestionnaire = lListOfQuestionnaire.get(0);
			lAssessmentList = new ArrayList<Assessment>();
			lAssessment = new Assessment();
			lAssessment.setQuestionnaire(lQuestionnaire);
			((ArrayList<Assessment>) lAssessmentList).add(lAssessment);
			webRequest.setAttribute("assessments", lAssessmentList, WebRequest.SCOPE_SESSION);
		} else if (lAssessmentList != null && !((List<Assessment>) lAssessmentList).isEmpty()) {
			for (Assessment lIteQuest : ((List<Assessment>) lAssessmentList)) {
				if (lIteQuest.getQuestionnaire().getId().equals(Long.parseLong(pQuestionnireId))) {
					lAssessment = lIteQuest;
					break;
				}
			}
			if (lAssessment == null) {
				List<Questionnaire> lListOfQuestionnaire = findQuestionnaire(pQuestionnireId);
				lAssessment = new Assessment();
				lAssessment.setQuestionnaire(lListOfQuestionnaire.get(0));
				((ArrayList<Assessment>) lAssessmentList).add(lAssessment);
			}
		}
		return lAssessment;
	}

	private List<Questionnaire> findQuestionnaire(String pQuestionnaireId) {
		Query query = em.createQuery("SELECT Q FROM com.bridgegap.course.Questionnaire Q where Q.id = ?1")
				.setParameter(1, Long.parseLong(pQuestionnaireId));
		return query.getResultList();
	}
}
