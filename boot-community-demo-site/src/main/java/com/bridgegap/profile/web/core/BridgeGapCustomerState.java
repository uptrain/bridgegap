package com.bridgegap.profile.web.core;

import javax.servlet.http.HttpServletRequest;

import org.broadleafcommerce.common.web.BroadleafRequestContext;
import org.broadleafcommerce.common.web.BroadleafRequestCustomerResolverImpl;
import org.broadleafcommerce.profile.core.domain.Customer;
import org.broadleafcommerce.profile.web.core.CustomerStateRefresher;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.WebRequest;

import com.bridgegap.profile.BridgeGapCustomer;

/**
 * Convenient class to get the active customer from the current request. This
 * state is kept up-to-date in regards to the database throughout the lifetime
 * of the request via the {@link CustomerStateRefresher}.
 *
 * @author Jeff Fischer
 * @author Phillip Verheyden (phillipuniverse)
 */
@Component("bridgeGapCustomerState")
public class BridgeGapCustomerState {

	public static Customer getCustomer(HttpServletRequest request) {
		return (Customer) BroadleafRequestCustomerResolverImpl.getRequestCustomerResolver().getCustomer(request);
	}

	public static Customer getCustomer(WebRequest request) {
		return (Customer) BroadleafRequestCustomerResolverImpl.getRequestCustomerResolver().getCustomer(request);
	}

	public static BridgeGapCustomer getCustomer() {
		if (BroadleafRequestContext.getBroadleafRequestContext() == null
				|| BroadleafRequestContext.getBroadleafRequestContext().getWebRequest() == null) {
			return null;
		}
		return (BridgeGapCustomer) BroadleafRequestCustomerResolverImpl.getRequestCustomerResolver().getCustomer();
	}

	public static void setCustomer(BridgeGapCustomer customer) {
		BroadleafRequestCustomerResolverImpl.getRequestCustomerResolver().setCustomer(customer);
	}

}
