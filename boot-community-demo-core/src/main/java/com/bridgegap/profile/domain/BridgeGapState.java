package com.bridgegap.profile.domain;

import java.io.Serializable;

public interface BridgeGapState extends Serializable{
	
	   public String getAbbreviation();

	    public void setAbbreviation(String abbreviation);
	    public String getName() ;

	    public void setName(String name) ;
	
}