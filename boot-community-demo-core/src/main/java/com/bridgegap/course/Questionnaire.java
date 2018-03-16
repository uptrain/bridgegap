package com.bridgegap.course;

import java.io.Serializable;
import java.util.List;

public interface Questionnaire extends Serializable {
	
	public Long getId();

    public void setId(Long id);
    
    public List<Question> getQuestions();
    
    public void setQuestions(List<Question> questions);
    
    public String getName();

    public void setName(String name);

}
