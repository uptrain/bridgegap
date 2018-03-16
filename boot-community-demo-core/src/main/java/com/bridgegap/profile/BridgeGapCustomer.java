/**
 * 
 */
package com.bridgegap.profile;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.broadleafcommerce.common.presentation.AdminPresentation;
import org.broadleafcommerce.common.presentation.AdminPresentationCollection;
import org.broadleafcommerce.common.presentation.client.AddMethodType;
import org.broadleafcommerce.profile.core.domain.CustomerImpl;
import org.hibernate.annotations.Cascade;

import com.bridgegap.course.Program;
import com.bridgegap.course.ProgramImpl;

/**
 * @author naveen.k.ganachari
 *
 */

@Entity
@Table(name = "BRIDGE_CUSTOMER")
public class BridgeGapCustomer extends CustomerImpl {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Column(name = "COLLEGE_NAME")
    @AdminPresentation(friendlyName = "CustomerImpl_College_Name",
            group = GroupName.Customer, order = 5000,
            prominent = true, gridOrder = 5000)
    protected String collegeName;
	
	@Column(name = "ACCOUNT_TYPE")
    @AdminPresentation(friendlyName = "CustomerImpl_Account_Type",
            group = GroupName.Customer, order = 6000,
            prominent = true, gridOrder = 6000)
	protected String accountType;
	
	@Column(name = "BRANCH_NAME")
    @AdminPresentation(friendlyName = "CustomerImpl_Branch_Name",
            group = GroupName.Customer, order = 7000,
            prominent = true, gridOrder = 7000)
	protected String branchName;
	
	@ElementCollection
	@CollectionTable(name = "BG_SUBJECTS")
	@Column(name = "SUBJECT_OF_INTEREST")
	protected List<String> subjectsOfInterest = new ArrayList();
	
	@Column(name = "CUSTOMER_NUMBER")
    @AdminPresentation(friendlyName = "CustomerImpl_PhoneNumber",
            group = GroupName.Customer, order = 8000,
            prominent = true, gridOrder = 8000)
	protected String customerPhoneNumber;
	
	@OneToMany(mappedBy = "customer", targetEntity = ProgramImpl.class, cascade = { CascadeType.ALL })
	@Cascade(value = { org.hibernate.annotations.CascadeType.ALL, org.hibernate.annotations.CascadeType.DELETE_ORPHAN })
	@AdminPresentationCollection(friendlyName = "Programs", order = 9000, addType = AddMethodType.PERSIST)
	protected List<Program> programs = new ArrayList<>();
	
	public List<Program> getPrograms() {
		return programs;
	}

	public void setPrograms(List<Program> programs) {
		this.programs = programs;
	}

	public String getCustomerPhoneNumber() {
		return customerPhoneNumber;
	}

	public void setCustomerPhoneNumber(String customerPhoneNumber) {
		this.customerPhoneNumber = customerPhoneNumber;
	}

	public List<String> getSubjectsOfInterest() {
		return subjectsOfInterest;
	}

	public void setSubjectsOfInterest(List<String> subjectsOfInterest) {
		this.subjectsOfInterest = subjectsOfInterest;
	}

	public String getBranchName() {
		return branchName;
	}

	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	public String getCollegeName() {
		return collegeName;
	}

	public void setCollegeName(String collegeName) {
		this.collegeName = collegeName;
	}
}
