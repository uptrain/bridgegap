package com.bridgegap.course;

import java.io.Serializable;

public interface Course extends Serializable {
	
	public Long getId();

    public void setId(Long id);

    public String getName();

    public void setName(String name);
    
    public String getDescription();
    
    public void setDescription(String description);
    
    public Questionnaire getQuestionnaire();
    
    public void setQuestionnaire(Questionnaire questionnaire);

}
