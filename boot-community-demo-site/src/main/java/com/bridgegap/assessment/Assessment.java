package com.bridgegap.assessment;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import com.bridgegap.course.Questionnaire;

public class Assessment implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private float score;

	private int numOfQuestAnsweredCorrectly;

	public float getScore() {
		return score;
	}

	public void setScore(float score) {
		this.score = score;
	}

	public int getNumOfQuestAnsweredCorrectly() {
		return numOfQuestAnsweredCorrectly;
	}

	public void setNumOfQuestAnsweredCorrectly(int numOfQuestAnsweredCorrectly) {
		this.numOfQuestAnsweredCorrectly = numOfQuestAnsweredCorrectly;
	}

	Set<Integer> askedQuestions = new HashSet<Integer>();

	private Questionnaire questionnaire;

	public Questionnaire getQuestionnaire() {
		return questionnaire;
	}

	public void setQuestionnaire(Questionnaire questionnaire) {
		this.questionnaire = questionnaire;
	}

	public Set<Integer> getAskedQuestions() {
		return askedQuestions;
	}

	public void setAskedQuestions(Set<Integer> askedQuestions) {
		this.askedQuestions = askedQuestions;
	}

}
