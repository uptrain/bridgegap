package com.bridgegap.course;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.broadleafcommerce.common.presentation.AdminPresentation;
import org.broadleafcommerce.common.presentation.RequiredOverride;
import org.broadleafcommerce.common.presentation.client.VisibilityEnum;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "BG_ANSWER")
public class AnswerImpl implements Answer {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "ID")
	@GeneratedValue(generator = "AnswerImplId")
	@GenericGenerator(name = "AnswerImplId", strategy = "org.broadleafcommerce.common.persistence.IdOverrideTableGenerator", parameters = {
			@Parameter(name = "segment_value", value = "AnswerImpl"),
			@Parameter(name = "entity_name", value = "com.bridgegap.course.AnswerImpl") })
	protected Long id;

	@Column(name = "ANSWER_NAME")
	@AdminPresentation(friendlyName = "Answer Name", group = "Answer", order = 10, requiredOverride = RequiredOverride.REQUIRED, prominent = true)
	protected String name;

	@Column(name = "DISPLAY_NAME")
	@AdminPresentation(friendlyName = "Answer Display Name", group = "Answer", order = 20, requiredOverride = RequiredOverride.REQUIRED, prominent = true)
	protected String displayName;
	
	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, targetEntity = QuestionImpl.class, optional=false)
    @JoinColumn(name = "QUESTION_ID")
    @AdminPresentation(excluded = true, visibility = VisibilityEnum.HIDDEN_ALL)
    protected Question question;
	
	@Column(name = "IS_CORRECT_ANSWER")
	@AdminPresentation(friendlyName = "Is correct answer?", group = "Answer", order = 30, requiredOverride = RequiredOverride.REQUIRED, prominent = true)
	protected boolean correctAnswer;

	public boolean isCorrectAnswer() {
		return correctAnswer;
	}

	public void setCorrectAnswer(boolean correctAnswer) {
		this.correctAnswer = correctAnswer;
	}

	public Question getQuestion() {
		return question;
	}

	public void setQuestion(Question question) {
		this.question = question;
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
	public String getDisplayName() {
		return displayName;
	}

	@Override
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

}
