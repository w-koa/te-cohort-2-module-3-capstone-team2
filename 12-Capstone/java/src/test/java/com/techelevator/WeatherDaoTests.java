package com.techelevator;

import static org.junit.Assert.assertEquals;

import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;

import com.techelevator.npgeek.JdbcParkDao;
import com.techelevator.npgeek.JdbcWeatherDao;
import com.techelevator.npgeek.Weather;

public class WeatherDaoTests {

private static SingleConnectionDataSource dataSource;
	
	private JdbcWeatherDao weatherDao;
	private JdbcParkDao parkDao;
	
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
		String sqlInsertPark = "INSERT INTO park (parkcode, parkname, "
				+ "state, acreage, elevationinfeet, milesoftrail, numberofcampsites, "
				+ "climate, yearfounded, annualvisitorcount, inspirationalquote, "
				+ "inspirationalquotesource, parkdescription, entryfee, numberofanimalspecies) "
				+ "VALUES ('TPTP', 'TestPark', 'Michigan', 30, 50, 120.0, 323, 'tropical', 2000, "
				+ "100000, 'test parks are great places', 'tim', 'test parks see millions of visitors', 8, 5000)";
	  
	    JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
	    jdbcTemplate.update(sqlInsertPark);
	    parkDao = new JdbcParkDao(dataSource);
		String sqlInsertWeather = "INSERT INTO weather (parkcode, fivedayforecastvalue, low, high, forecast)"
				+ "VALUES ('TPTP', 1, 50, 80, 'rain')";
	    jdbcTemplate.update(sqlInsertWeather);
	    weatherDao = new JdbcWeatherDao(dataSource);
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
	public void testGetForecastByCode() {
		List<Weather> forecastList = weatherDao.getForecastByCode("TPTP", "C");
		String expected = "rain";
		Weather weather = forecastList.get(0);
		assertEquals(expected, weather.getForecast());
	}

	
}
