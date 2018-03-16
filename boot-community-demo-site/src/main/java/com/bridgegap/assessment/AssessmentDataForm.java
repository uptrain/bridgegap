package com.bridgegap.assessment;

import java.io.Serializable;
import java.util.List;

import com.bridgegap.course.Question;

public class AssessmentDataForm implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Question question;
	
	private List<String> answers;
	
	private boolean endOfAssessment;
	
	private float score;
	
	public float getScore() {
		return score;
	}

	public void setScore(float score) {
		this.score = score;
	}

	public boolean isEndOfAssessment() {
		return endOfAssessment;
	}

	public void setEndOfAssessment(boolean endOfAssessment) {
		this.endOfAssessment = endOfAssessment;
	}

	public List<String> getAnswers() {
		return answers;
	}

	public void setAnswers(List<String> answers) {
		this.answers = answers;
	}

	public Question getQuestion() {
		return question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}

}
