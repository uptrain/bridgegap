/*
 * Copyright 2012 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.community.controller.account;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.broadleafcommerce.common.exception.ServiceException;
import org.broadleafcommerce.common.i18n.service.ISOService;
import org.broadleafcommerce.common.web.form.BroadleafFormType;
import org.broadleafcommerce.core.order.domain.NullOrderImpl;
import org.broadleafcommerce.core.order.domain.Order;
import org.broadleafcommerce.core.pricing.service.exception.PricingException;
import org.broadleafcommerce.core.web.controller.account.BroadleafRegisterController;
import org.broadleafcommerce.core.web.controller.account.CustomerAddressForm;
import org.broadleafcommerce.core.web.controller.account.validator.CustomerAddressValidator;
import org.broadleafcommerce.core.web.order.CartState;
import org.broadleafcommerce.profile.core.domain.Address;
import org.broadleafcommerce.profile.core.domain.Customer;
import org.broadleafcommerce.profile.core.domain.CustomerAddress;
import org.broadleafcommerce.profile.core.service.AddressService;
import org.broadleafcommerce.profile.core.service.CustomerAddressService;
import org.broadleafcommerce.profile.web.core.CustomerState;
import org.broadleafcommerce.profile.web.core.form.BroadLeafRegistrationForm;
import org.broadleafcommerce.profile.web.core.form.RegisterCustomerForm;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * The controller responsible for registering a customer
 */
@Controller
@RequestMapping("/register")
public class RegisterController extends BroadleafRegisterController {
	
	 @Resource(name = "blAddressService")
	    protected AddressService addressService;

	    @Resource(name = "blCustomerAddressValidator")
	    protected CustomerAddressValidator customerAddressValidator;
	    
	    @Resource(name = "blCustomerAddressService")
	    protected CustomerAddressService customerAddressService;

	    @Resource(name = "blISOService")
	    protected ISOService isoService;
    
    @RequestMapping(method=RequestMethod.GET)
    public String register(HttpServletRequest request, HttpServletResponse response, Model model,
            @ModelAttribute("registrationForm") RegisterCustomerForm registerCustomerForm) {
        return super.register(registerCustomerForm, request, response, model);
    }
    
    @RequestMapping(method=RequestMethod.POST)
    public String processRegister(HttpServletRequest request, HttpServletResponse response, Model model,
            @ModelAttribute("registrationForm") RegisterCustomerForm registerCustomerForm, BindingResult errors) throws ServiceException, PricingException {
        return processRegister(registerCustomerForm, errors, request, response, model);
    }
    
    @ModelAttribute("registrationForm") 
    public RegisterCustomerForm initCustomerRegistrationForm() {
        return super.initCustomerRegistrationForm();        
    }
    
    public String processRegister(RegisterCustomerForm registerCustomerForm, BindingResult errors, 
            HttpServletRequest request, HttpServletResponse response, Model model)
            throws ServiceException, PricingException {
        
        if (useEmailForLogin) {
            Customer customer = registerCustomerForm.getCustomer();
            customer.setUsername(customer.getEmailAddress());
        }
        
        registerCustomerValidator.validate(registerCustomerForm, errors, useEmailForLogin);
        if (!errors.hasErrors()) {
            Customer newCustomer = customerService.registerCustomer(registerCustomerForm.getCustomer(), 
                    registerCustomerForm.getPassword(), registerCustomerForm.getPasswordConfirm());
            assert(newCustomer != null);
            
            // The next line needs to use the customer from the input form and not the customer returned after registration
            // so that we still have the unencoded password for use by the authentication mechanism.
            loginService.loginCustomer(registerCustomerForm.getCustomer());

            // Need to ensure that the Cart on CartState is owned by the newly registered customer.
            Order cart = CartState.getCart();
            if (cart != null && !(cart instanceof NullOrderImpl) && cart.getEmailAddress() == null) {
                cart.setEmailAddress(newCustomer.getEmailAddress());
                orderService.save(cart, false);
            }
            
            if(null != ((BroadLeafRegistrationForm)registerCustomerForm).getAddress() && null != ((BroadLeafRegistrationForm)registerCustomerForm).getAddress().getAddressLine1()) {
            	Address addr = ((BroadLeafRegistrationForm)registerCustomerForm).getAddress();
            	Customer customer = registerCustomerForm.getCustomer();
            	addr.setFirstName(customer.getFirstName());
            	addr.setLastName(customer.getLastName());
            	addressService.populateAddressISOCountrySub(((BroadLeafRegistrationForm)registerCustomerForm).getAddress());
                
                removeUnusedPhones((BroadLeafRegistrationForm)registerCustomerForm);

                Address address = addressService.saveAddress(((BroadLeafRegistrationForm)registerCustomerForm).getAddress());
                CustomerAddress customerAddress = customerAddressService.create();
                customerAddress.setAddress(address);
                customerAddress.setAddressName(((BroadLeafRegistrationForm)registerCustomerForm).getAddressName());
                customerAddress.setCustomer(CustomerState.getCustomer());
                customerAddress = customerAddressService.saveCustomerAddress(customerAddress);
                if (((BroadLeafRegistrationForm)registerCustomerForm).getAddress().isDefault()) {
                    customerAddressService.makeCustomerAddressDefault(customerAddress.getId(), customerAddress.getCustomer().getId());
                }
                if (!isAjaxRequest(request)) {
                    List<CustomerAddress> addresses = customerAddressService.readActiveCustomerAddressesByCustomerId(CustomerState.getCustomer().getId());
                    model.addAttribute("addresses", addresses);
                }
            }
            
            String redirectUrl = registerCustomerForm.getRedirectUrl();
            if (StringUtils.isNotBlank(redirectUrl) && redirectUrl.contains(":")) {
                redirectUrl = null;
            }	
            return StringUtils.isBlank(redirectUrl) ? getRegisterSuccessView() : "redirect:" + redirectUrl;
        } else {
            return getRegisterView();
        }
        
        
    }
    
    public void removeUnusedPhones(BroadLeafRegistrationForm registerCustomerForm) {
        if ((registerCustomerForm.getAddress().getPhonePrimary() != null) &&
                    (StringUtils.isEmpty(registerCustomerForm.getAddress().getPhonePrimary().getPhoneNumber()))) {
            registerCustomerForm.getAddress().setPhonePrimary(null);
        }
        if ((registerCustomerForm.getAddress().getPhoneSecondary() != null) &&
                    (StringUtils.isEmpty(registerCustomerForm.getAddress().getPhoneSecondary().getPhoneNumber()))) {
            registerCustomerForm.getAddress().setPhoneSecondary(null);
        }
        if ((registerCustomerForm.getAddress().getPhoneFax() != null) &&
                    (StringUtils.isEmpty(registerCustomerForm.getAddress().getPhoneFax().getPhoneNumber()))) {
            registerCustomerForm.getAddress().setPhoneFax(null);
        }
    }
    
    public void validate(BroadleafFormType formType, Address address, Errors errors) {

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "address.addressLine1", "addressLine1.required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "address.city", "city.required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "address.stateProvinceRegion", "state.required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "address.postalCode", "postalCode.required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "address.phonePrimary.phoneNumber", "phonePrimary.required");

    
        if (address.getIsoCountryAlpha2() == null && address.getCountry() == null) {
            errors.rejectValue("address.isoCountryAlpha2", "country.required", null, null);
        }
    }
}
