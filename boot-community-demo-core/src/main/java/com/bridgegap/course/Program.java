package com.bridgegap.course;

import java.io.Serializable;
import java.util.List;

public interface Program extends Serializable {

	public Long getId();

    public void setId(Long id);

    public String getName();

    public void setName(String name);
    
    public List getCourses();
    
    public void setCourses(List courses);
}
