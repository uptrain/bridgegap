package com.bridgegap.course;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.broadleafcommerce.common.presentation.AdminPresentation;
import org.broadleafcommerce.common.presentation.AdminPresentationCollection;
import org.broadleafcommerce.common.presentation.RequiredOverride;
import org.broadleafcommerce.common.presentation.client.AddMethodType;
import org.broadleafcommerce.common.presentation.client.VisibilityEnum;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "BG_QUESTION")
public class QuestionImpl implements Question {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "ID")
	@GeneratedValue(generator = "QuestionImplId")
	@GenericGenerator(name = "QuestionImplId", strategy = "org.broadleafcommerce.common.persistence.IdOverrideTableGenerator", parameters = {
			@Parameter(name = "segment_value", value = "QuestionImpl"),
			@Parameter(name = "entity_name", value = "com.bridgegap.course.QuestionImpl") })
	protected Long id;

	@Column(name = "QUESTION_NAME")
	@AdminPresentation(friendlyName = "Question", group = "Question", order = 10, requiredOverride = RequiredOverride.REQUIRED, prominent = true)
	protected String name;

	@Column(name = "QUESTION_TYPE")
	@AdminPresentation(friendlyName = "Question type", group = "Question", order = 20, requiredOverride = RequiredOverride.REQUIRED, prominent = true)
	protected String questionType;

	@OneToMany(mappedBy = "question", targetEntity = AnswerImpl.class, cascade = { CascadeType.ALL }, fetch = FetchType.EAGER)
	@Cascade(value = { org.hibernate.annotations.CascadeType.ALL, org.hibernate.annotations.CascadeType.DELETE_ORPHAN })
	@AdminPresentationCollection(friendlyName = "Answers", order = 10, addType = AddMethodType.PERSIST)
	protected List<Answer> answers = new ArrayList<>();

	
	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, targetEntity = QuestionnaireImpl.class, optional=false)
    @JoinColumn(name = "QUESTIONNAIRE_ID")
    @AdminPresentation(excluded = true, visibility = VisibilityEnum.HIDDEN_ALL)
    protected Questionnaire questionnaire;
	

	public List<Answer> getAnswers() {
		return answers;
	}

	@SuppressWarnings("unchecked")
	public void setAnswers(List answers) {
		this.answers = answers;
	}

	@Override
	public Long getId() {
		return id;
	}

	@Override
	public void setId(Long id) {
		this.id = id;

	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setName(String name) {
		this.name = name;

	}

	@Override
	public String getQuestionType() {
		return questionType;
	}

	@Override
	public void setQuestionType(String questionType) {
		this.questionType = questionType;
	}
}
