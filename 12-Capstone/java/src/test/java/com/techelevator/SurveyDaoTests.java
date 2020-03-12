package com.techelevator;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.time.LocalDate;

import javax.sql.DataSource;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;

import com.techelevator.npgeek.JdbcParkDao;
import com.techelevator.npgeek.JdbcSurveyDao;
import com.techelevator.npgeek.JdbcWeatherDao;
import com.techelevator.npgeek.Survey;


public class SurveyDaoTests {

	private static SingleConnectionDataSource dataSource;

	private JdbcWeatherDao weatherDao;
	private JdbcParkDao parkDao;
	private JdbcSurveyDao surveyDao;

	@BeforeClass
	public static void setupDataSource() {
		dataSource = new SingleConnectionDataSource();
		dataSource.setUrl("jdbc:postgresql://localhost:5432/npgeek");
		dataSource.setUsername("postgres");
		dataSource.setPassword("postgres1");
		dataSource.setAutoCommit(false);
	}

	@Before
	public void setup() {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

		String sqlInsertPark = "INSERT INTO park (parkcode, parkname, "
				+ "state, acreage, elevationinfeet, milesoftrail, numberofcampsites, "
				+ "climate, yearfounded, annualvisitorcount, inspirationalquote, "
				+ "inspirationalquotesource, parkdescription, entryfee, numberofanimalspecies) "
				+ "VALUES ('TPTP', 'TestPark', 'Michigan', 30, 50, 120.0, 323, 'tropical', 2000, "
				+ "100000, 'test parks are great places', 'tim', 'test parks see millions of visitors', 8, 5000)";
		jdbcTemplate.update(sqlInsertPark);
		parkDao = new JdbcParkDao(dataSource);

		String sqlInsertWeather = "INSERT INTO weather (parkcode, fivedayforecastvalue, low, high, forecast)"
				+ "VALUES ('TPTP', 1, 50, 80, 'rain')";
		jdbcTemplate.update(sqlInsertWeather);
		weatherDao = new JdbcWeatherDao(dataSource);
		
		surveyDao = new JdbcSurveyDao(dataSource);
	}

	@AfterClass
	public static void closeDataSource() throws SQLException {
		dataSource.destroy();
	}

	@After
	public void rollback() throws SQLException {
		dataSource.getConnection().rollback();
	}

	public DataSource getDataSource() {
		return dataSource;
	}

	@Test
	public void testSaveSurvey() {
	Survey testSurvey = new Survey();
	testSurvey.setParkCode("TPTP");
	testSurvey.setEmail("tim@tim.com");
	testSurvey.setState("MI");
	testSurvey.setActivityLevel("active");
	surveyDao.saveSurvey(testSurvey);
	boolean foundSurvey = false;
	
	
	if (surveyDao.saveSurvey(survey);(testSurvey.() != null) {
		foundFlag = true;
	}
	assertTrue(foundFlag);
}
}
