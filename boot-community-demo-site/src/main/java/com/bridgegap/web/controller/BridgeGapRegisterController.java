/**
 * 
 */
package com.bridgegap.web.controller;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.broadleafcommerce.profile.core.domain.Customer;
import org.broadleafcommerce.profile.web.core.CustomerState;
import org.broadleafcommerce.profile.web.core.form.BroadLeafRegistrationForm;
import org.broadleafcommerce.profile.web.core.form.RegisterCustomerForm;
import org.broadleafcommerce.profile.web.core.service.register.RegistrationServiceImpl;
import org.hibernate.ejb.QueryHints;

import com.bridgegap.profile.domain.BridgeGapState;
import com.mycompany.sample.domain.MyCustomClass;

/**
 * @author naveen.k.ganachari
 *
 */
public class BridgeGapRegisterController extends RegistrationServiceImpl {
	
	  @PersistenceContext(unitName = "blPU")
	    protected EntityManager em;
	
	@Override
    public RegisterCustomerForm initCustomerRegistrationForm() {
        Customer customer = CustomerState.getCustomer();
        if (customer == null || ! customer.isAnonymous()) {
            customer = customerService.createCustomerFromId(null);
        }
        System.out.println("inside Custom Method");
        BroadLeafRegistrationForm customerRegistrationForm = new BroadLeafRegistrationForm();
        List<MyCustomClass> list = populateSubjectOfInterest(); 
        customerRegistrationForm.setCustomer(customer);
        customerRegistrationForm.setSubjects(list);
        customerRegistrationForm.setStates(populateStates());
        return customerRegistrationForm;
    }

	private List<MyCustomClass> populateSubjectOfInterest() {
		Query query = em.createNamedQuery("BG_FIND_SUBJECTS");
        query.setHint(QueryHints.HINT_CACHEABLE, true);
        return query.getResultList();
	}
	
	private List<BridgeGapState> populateStates() {
		Query query = em.createNamedQuery("BG_FIND_STATES");
        query.setHint(QueryHints.HINT_CACHEABLE, true);
        return query.getResultList();
	}
}
