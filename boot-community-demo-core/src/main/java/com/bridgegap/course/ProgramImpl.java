package com.bridgegap.course;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
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
import org.broadleafcommerce.profile.core.domain.Customer;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import com.bridgegap.profile.BridgeGapCustomer;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "BG_PROGRAM")
public class ProgramImpl implements Program {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "ID")
	@GeneratedValue(generator = "ProgramImplId")
	@GenericGenerator(name = "ProgramImplId", strategy = "org.broadleafcommerce.common.persistence.IdOverrideTableGenerator", parameters = {
			@Parameter(name = "segment_value", value = "ProgramImpl"),
			@Parameter(name = "entity_name", value = "com.bridgegap.course.ProgramImpl") })
	protected Long id;

	@Column(name = "PROGRAM_NAME")
	@AdminPresentation(friendlyName = "Program Name", group = "Answer", order = 10, requiredOverride = RequiredOverride.REQUIRED, prominent = true)
	protected String name;

	@OneToMany(mappedBy = "program", targetEntity = CourseImpl.class, cascade = { CascadeType.ALL })
	@Cascade(value = { org.hibernate.annotations.CascadeType.ALL, org.hibernate.annotations.CascadeType.DELETE_ORPHAN })
	@AdminPresentationCollection(friendlyName = "Courses", order = 10, addType = AddMethodType.PERSIST)
	protected List<Course> courses = new ArrayList<>();
	
	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, targetEntity = BridgeGapCustomer.class, optional=false)
    @JoinColumn(name = "CUSTOMER_ID")
    @AdminPresentation(excluded = true, visibility = VisibilityEnum.HIDDEN_ALL)
	protected Customer customer;

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public List<Course> getCourses() {
		return courses;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void setCourses(List courses) {
		this.courses = courses;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
