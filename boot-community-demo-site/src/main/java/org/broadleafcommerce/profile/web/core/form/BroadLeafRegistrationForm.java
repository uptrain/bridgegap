package org.broadleafcommerce.profile.web.core.form;

import java.util.List;

import org.broadleafcommerce.profile.core.domain.Address;
import org.broadleafcommerce.profile.core.domain.AddressImpl;
import org.broadleafcommerce.profile.core.domain.PhoneImpl;

import com.bridgegap.profile.domain.BridgeGapState;
import com.mycompany.sample.domain.MyCustomClass;

public class BroadLeafRegistrationForm extends RegisterCustomerForm {

	private List<BridgeGapState> states;

	protected static final long serialVersionUID = 1L;

	protected Address address = new AddressImpl();
	protected String addressName;
	protected Long customerAddressId;

	public List<MyCustomClass> subjects;

	public BroadLeafRegistrationForm() {
		address.setPhonePrimary(new PhoneImpl());
		address.setPhoneSecondary(new PhoneImpl());
		address.setPhoneFax(new PhoneImpl());
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		if (address.getPhonePrimary() == null) {
			address.setPhonePrimary(new PhoneImpl());
		}
		if (address.getPhoneSecondary() == null) {
			address.setPhoneSecondary(new PhoneImpl());
		}
		if (address.getPhoneFax() == null) {
			address.setPhoneFax(new PhoneImpl());
		}
		this.address = address;
	}

	public String getAddressName() {
		return addressName;
	}

	public void setAddressName(String addressName) {
		this.addressName = addressName;
	}

	public Long getCustomerAddressId() {
		return customerAddressId;
	}

	public void setCustomerAddressId(Long customerAddressId) {
		this.customerAddressId = customerAddressId;
	}

	public List<BridgeGapState> getStates() {
		return states;
	}

	public void setStates(List<BridgeGapState> states) {
		this.states = states;
	}

	public List<MyCustomClass> getSubjects() {
		return subjects;
	}

	public void setSubjects(List<MyCustomClass> subjects) {
		this.subjects = subjects;
	}

}
