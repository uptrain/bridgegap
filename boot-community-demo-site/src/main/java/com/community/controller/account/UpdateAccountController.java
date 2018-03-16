/*
 * Copyright 2008-2012 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.community.controller.account;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.servlet.http.HttpServletRequest;

import org.broadleafcommerce.common.exception.ServiceException;
import org.broadleafcommerce.core.web.controller.account.BridgeGapUpdateAccountForm;
import org.broadleafcommerce.core.web.controller.account.BroadleafUpdateAccountController;
import org.broadleafcommerce.core.web.controller.account.validator.UpdateAccountValidator;
import org.broadleafcommerce.profile.core.domain.Customer;
import org.broadleafcommerce.profile.core.service.CustomerService;
import org.broadleafcommerce.profile.web.core.CustomerState;
import org.hibernate.ejb.QueryHints;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.bridgegap.profile.BridgeGapCustomer;
import com.mycompany.sample.domain.MyCustomClass;


@Controller
@RequestMapping("/account")
public class UpdateAccountController extends BroadleafUpdateAccountController {
	
	 @Resource(name="blUserDetailsService")
	    private UserDetailsService userDetailsService;

	    @Resource(name = "blCustomerService")
	    protected CustomerService customerService;
	    
	    @Resource(name = "blUpdateAccountValidator")
	    protected UpdateAccountValidator updateAccountValidator;
	    
	    @PersistenceContext(unitName = "blPU")
	    protected EntityManager em;

    @RequestMapping(method = RequestMethod.GET)
    public String viewUpdateAccount(HttpServletRequest request, Model model, @ModelAttribute("updateAccountForm") BridgeGapUpdateAccountForm bgForm) {
    	   Customer customer = CustomerState.getCustomer();
           bgForm.setEmailAddress(customer.getEmailAddress());
           bgForm.setFirstName(customer.getFirstName());
           bgForm.setLastName(customer.getLastName());
           bgForm.setCollegeName(((BridgeGapCustomer)customer).getCollegeName());
           bgForm.setBranchName(((BridgeGapCustomer)customer).getBranchName());
           bgForm.setAccountType(((BridgeGapCustomer)customer).getAccountType());
           bgForm.setSubjectsOfInterest(((BridgeGapCustomer)customer).getSubjectsOfInterest());
           bgForm.setCustomerPhoneNumber(((BridgeGapCustomer)customer).getCustomerPhoneNumber());
           bgForm.setCustomer(customer);
           bgForm.setListOfSubjects(populateSubjectOfInterest());
           return getUpdateAccountView();
        
    }

    @RequestMapping(method = RequestMethod.POST)
    public String processUpdateAccount(HttpServletRequest request, Model model, @ModelAttribute("updateAccountForm") BridgeGapUpdateAccountForm form, BindingResult result, RedirectAttributes redirectAttributes) throws ServiceException {

        updateAccountValidator.validate(form, result);
        
        if (result.hasErrors()) {
            return getUpdateAccountView();
        }
        
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null || !auth.isAuthenticated()) {
    		throw new AuthenticationCredentialsNotFoundException("Authentication was null, not authenticated, or not logged in.");
    	}
        
        Customer customer = CustomerState.getCustomer();
        customer.setEmailAddress(form.getEmailAddress());
        customer.setFirstName(form.getFirstName());
        customer.setLastName(form.getLastName());
        ((BridgeGapCustomer)customer).setBranchName(form.getBranchName());
        ((BridgeGapCustomer)customer).setCollegeName((form.getCollegeName()));
        
        if(!form.getSubjectsOfInterest().isEmpty()) {
        	 ((BridgeGapCustomer)customer).getSubjectsOfInterest().clear();
        	 ((BridgeGapCustomer)customer).setSubjectsOfInterest(form.getSubjectsOfInterest());
        } else {
        	((BridgeGapCustomer)customer).getSubjectsOfInterest().clear();
        }
       
        
        if (useEmailForLogin) {
            customer.setUsername(form.getEmailAddress());
        }
        
        customer = customerService.saveCustomer(customer);
        
        if (useEmailForLogin) {
        	UserDetails principal = userDetailsService.loadUserByUsername(customer.getUsername());
            UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(principal, principal.getPassword(), auth.getAuthorities());
            
            SecurityContextHolder.getContext().setAuthentication(token);
        }
        
        redirectAttributes.addFlashAttribute("successMessage", getAccountUpdatedMessage());
        return getAccountRedirectView();
    
    }

    private List<MyCustomClass> populateSubjectOfInterest() {
		List<String> subjects = new ArrayList<String>();
		Query query = em.createNamedQuery("BG_FIND_SUBJECTS");
        query.setHint(QueryHints.HINT_CACHEABLE, true);
        return query.getResultList();
	}
    
}
