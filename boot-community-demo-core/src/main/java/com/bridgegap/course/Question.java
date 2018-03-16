package com.bridgegap.course;

import java.io.Serializable;
import java.util.List;

public interface Question extends Serializable {
	
	public Long getId();

    public void setId(Long id);

    public String getName();

    public void setName(String name);
    
    public String getQuestionType();

    public void setQuestionType(String name);
    
    public List getAnswers();
    
    public void setAnswers(List answers);
    
    /*public List getCorrectAnswers();
    
    public void setCorrectAnswers(List correctAnswers);*/
 
    

}
