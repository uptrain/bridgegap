package com.bridgegap.profile.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Entity
@Table(name = "ACCOUNT")
public class AccountImpl implements Account {
	
	 @Id
	    @GeneratedValue(generator= "AccountId")
	    @GenericGenerator(
	        name="AccountId",
	        strategy="org.broadleafcommerce.common.persistence.IdOverrideTableGenerator",
	        parameters = {
	            @Parameter(name="segment_value", value="AccountImpl"),
	            @Parameter(name="entity_name", value="com.bridgegap.profile.domain.AccountImpl")
	        }
	    )    
	    

    @Column(name = "ACCOUNT_ID")
    protected Long id;

    @Column(name = "NAME")
    protected String name;

    @Column(name = "ACCOUNT_NBR")
    protected String accountNumber;

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

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }
}
