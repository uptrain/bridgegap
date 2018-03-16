package org.broadleafcommerce.core.web.controller.account;

import java.util.ArrayList;
import java.util.List;

import org.broadleafcommerce.profile.core.domain.Customer;

import com.mycompany.sample.domain.MyCustomClass;

public class BridgeGapUpdateAccountForm extends UpdateAccountForm {


    private static final long serialVersionUID = 1L;

    private String emailAddress;
    private String firstName;
    private String lastName;
    private String collegeName;
    private String branchName;
    private List<String> subjectsOfInterest = new ArrayList();
    private boolean student;
    private String accountType;
    private String customerPhoneNumber;
    //Testing purpose need to delete if nt required later
    private Customer customer;
    private List<MyCustomClass> listOfSubjects;
    
    public String getCustomerPhoneNumber() {
		return customerPhoneNumber;
	}

	public void setCustomerPhoneNumber(String customerPhoneNumber) {
		this.customerPhoneNumber = customerPhoneNumber;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public String getCollegeName() {
		return collegeName;
	}

	public void setCollegeName(String collegeName) {
		this.collegeName = collegeName;
	}

	public String getBranchName() {
		return branchName;
	}

	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}

	public List<String> getSubjectsOfInterest() {
		return subjectsOfInterest;
	}

	public void setSubjectsOfInterest(List<String> subjectsOfInterest) {
		this.subjectsOfInterest = subjectsOfInterest;
	}

	public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

	public boolean isStudent() {
		return student;
	}

	public void setStudent(boolean student) {
		this.student = student;
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	public List<MyCustomClass> getListOfSubjects() {
		return listOfSubjects;
	}

	public void setListOfSubjects(List<MyCustomClass> listOfSubjects) {
		this.listOfSubjects = listOfSubjects;
	}


}
