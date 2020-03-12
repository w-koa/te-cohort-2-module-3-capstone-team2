package com.techelevator.npgeek;

import java.util.List;

public interface SurveyDao {

	public void saveSurvey (Survey survey);

	public List<Park> getTopFiveParks();

	public List<Survey> getSurveyByParkId(String parkCode);
	
}
