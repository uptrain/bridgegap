package com.bridgegap.program.data;

import java.io.Serializable;

import com.bridgegap.course.Program;

public class ProgramDataForm implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected Program program;

	public Program getProgram() {
		return program;
	}

	public void setProgram(Program program) {
		this.program = program;
	}

}
