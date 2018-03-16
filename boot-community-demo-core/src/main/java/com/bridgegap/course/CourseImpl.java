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
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.broadleafcommerce.common.presentation.AdminPresentation;
import org.broadleafcommerce.common.presentation.AdminPresentationToOneLookup;
import org.broadleafcommerce.common.presentation.RequiredOverride;
import org.broadleafcommerce.common.presentation.client.VisibilityEnum;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "BG_COURSE")
public class CourseImpl implements Course {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "ID")
	@GeneratedValue(generator = "CourseImplId")
	@GenericGenerator(name = "CourseImplId", strategy = "org.broadleafcommerce.common.persistence.IdOverrideTableGenerator", parameters = {
			@Parameter(name = "segment_value", value = "CourseImpl"),
			@Parameter(name = "entity_name", value = "com.bridgegap.course.CourseImpl") })
	protected Long id;

	@Column(name = "COURSE_NAME")
	@AdminPresentation(friendlyName = "Course Name", group = "Course", order = 10, requiredOverride = RequiredOverride.REQUIRED, prominent = true)
	protected String name;

	@Column(name = "COURSE_DESCRIPTION")
	@AdminPresentation(friendlyName = "Course Description", group = "Course", order = 20, requiredOverride = RequiredOverride.REQUIRED)
	protected String description;

	@OneToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, targetEntity = QuestionnaireImpl.class)
	@JoinColumn(name = "COURSE_QUESTIONNAIRE")
	@AdminPresentation(friendlyName = "Course Questionnaire", order = 30, group = "Course")
	@AdminPresentationToOneLookup
	protected Questionnaire questionnaire;
	
	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, targetEntity = ProgramImpl.class, optional=false)
    @JoinColumn(name = "PROGRAM_ID")
    @AdminPresentation(excluded = true, visibility = VisibilityEnum.HIDDEN_ALL)
	protected Program program;

	public Program getProgram() {
		return program;
	}

	public void setProgram(Program program) {
		this.program = program;
	}

	public Questionnaire getQuestionnaire() {
		return questionnaire;
	}

	public void setQuestionnaire(Questionnaire questionnaire) {
		this.questionnaire = questionnaire;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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

}
