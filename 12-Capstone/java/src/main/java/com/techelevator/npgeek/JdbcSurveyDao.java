package com.techelevator.npgeek;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

@Component
public class JdbcSurveyDao implements SurveyDao {

	private JdbcTemplate jdbcTemplate;

	@Autowired
	public JdbcSurveyDao(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public void saveSurvey(Survey survey) {
		String sqlInsertSurveyResult = "INSERT INTO survey_result "
				+ "(surveyid, parkcode, emailaddress, state, activitylevel) " + "VALUES (?, ?, ?, ?, ?)";
		int newId = getNextId();
		jdbcTemplate.update(sqlInsertSurveyResult, newId, survey.getParkCode(), survey.getEmail(), survey.getState(),
				survey.getActivityLevel());

	}

	private int getNextId() {
		String sqlSelectNextId = "SELECT NEXTVAL('seq_surveyid')";
		SqlRowSet result = jdbcTemplate.queryForRowSet(sqlSelectNextId);
		if (result.next()) {
			return result.getInt(1);
		} else {
			throw new RuntimeException("Something went wrong while getting the next survey id");
		}
	}

	@Override
	public List<Park> getTopFiveParks() {
		List<Park> topFiveParks = new ArrayList<>();
		String sqlCountVotes = "SELECT COUNT(parkcode) AS park_vote, parkcode FROM "
				+ "survey_result GROUP BY parkcode ORDER BY park_vote desc limit 5";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlCountVotes);
		while (results.next()) {
			Park park = new Park();
			park.setParkVotes(results.getInt("park_vote"));
			park.setParkCode(results.getString("parkcode"));
			topFiveParks.add(park);
		}

		return topFiveParks;
	}

}
