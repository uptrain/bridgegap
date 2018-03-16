package com.bridgegap.course;

import java.io.Serializable;

public interface Answer extends Serializable {
	
	public Long getId();

    public void setId(Long id);

    public String getName();

    public void setName(String name);
    
    public String getDisplayName();
    
    public void setDisplayName(String displayName);
    
    public void setCorrectAnswer(boolean bool);
    public boolean isCorrectAnswer();
    
}
