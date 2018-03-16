package com.bridgegap.course;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.broadleafcommerce.common.presentation.AdminPresentation;
import org.broadleafcommerce.common.presentation.AdminPresentationCollection;
import org.broadleafcommerce.common.presentation.RequiredOverride;
import org.broadleafcommerce.common.presentation.client.AddMethodType;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "BG_QUESTIONNAIRE")
public class QuestionnaireImpl implements Questionnaire {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "ID")
	@GeneratedValue(generator = "QuestionnaireImplId")
	@GenericGenerator(name = "QuestionnaireImplId", strategy = "org.broadleafcommerce.common.persistence.IdOverrideTableGenerator", parameters = {
			@Parameter(name = "segment_value", value = "QuestionnaireImpl"),
			@Parameter(name = "entity_name", value = "com.bridgegap.course.QuestionnaireImpl") })
	protected Long id;

	@OneToMany(mappedBy = "questionnaire", targetEntity = QuestionImpl.class, cascade = { CascadeType.ALL })
	@Cascade(value = { org.hibernate.annotations.CascadeType.ALL, org.hibernate.annotations.CascadeType.DELETE_ORPHAN })
	@AdminPresentationCollection(friendlyName = "List of questions", order = 10, addType = AddMethodType.PERSIST)
	protected List<Question> questions;

	@Column(name = "QUESTIONAIRE_NAME")
	@AdminPresentation(friendlyName = "Questionnaire Name", group = "Questionnaire", order = 10, requiredOverride = RequiredOverride.REQUIRED, prominent = true)
	protected String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Question> getQuestions() {
		return questions;
	}

	public void setQuestions(List<Question> questions) {
		this.questions = questions;
	}

	@Override
	public Long getId() {
		return id;
	}

	@Override
	public void setId(Long id) {
		this.id = id;

	}

}
