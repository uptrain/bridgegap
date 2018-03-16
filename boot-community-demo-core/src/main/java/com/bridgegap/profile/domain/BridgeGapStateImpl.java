package com.bridgegap.profile.domain;


	import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import org.broadleafcommerce.common.presentation.AdminPresentation;
import org.broadleafcommerce.common.presentation.AdminPresentationClass;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Index;

	
	@Entity
	@Inheritance(strategy = InheritanceType.JOINED)
	@Table(name = "BG_STATE")
	@Cache(usage = CacheConcurrencyStrategy.READ_WRITE, region="blStandardElements")
	@AdminPresentationClass(friendlyName = "BG_StateImpl_baseState")
	public class BridgeGapStateImpl implements BridgeGapState {

	    private static final long serialVersionUID = 1L;

	    @Id
	    @Column(name = "ABBREVIATION")
	    protected String abbreviation;

	    @Column(name = "NAME", nullable = false)
	    @Index(name="BG_STATE_NAME_INDEX", columnNames={"NAME"})
	    @AdminPresentation(friendlyName = "BG_StateImpl_State", order=9, group = "BG_StateImpl_Address", prominent = true)
	    protected String name;

	    public String getAbbreviation() {
	        return abbreviation;
	    }

	    public void setAbbreviation(String abbreviation) {
	        this.abbreviation = abbreviation;
	    }

	    public String getName() {
	        return name;
	    }

	    public void setName(String name) {
	        this.name = name;
	    }

	 

	    @Override
	    public int hashCode() {
	        final int prime = 31;
	        int result = 1;
	        result = prime * result + ((abbreviation == null) ? 0 : abbreviation.hashCode());
	        result = prime * result + ((name == null) ? 0 : name.hashCode());
	        return result;
	    }

	    @Override
	    public boolean equals(Object obj) {
	        if (this == obj)
	            return true;
	        if (obj == null)
	            return false;
	        if (!getClass().isAssignableFrom(obj.getClass()))
	            return false;
	        BridgeGapStateImpl other = (BridgeGapStateImpl) obj;
	        if (abbreviation == null) {
	            if (other.abbreviation != null)
	                return false;
	        } else if (!abbreviation.equals(other.abbreviation))
	            return false;
	     
	        if (name == null) {
	            if (other.name != null)
	                return false;
	        } else if (!name.equals(other.name))
	            return false;
	        return true;
	    }


}
