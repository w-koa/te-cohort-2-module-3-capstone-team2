package com.techelevator;

import static org.junit.Assert.*;

import java.sql.SQLException;

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

public class WeatherDaoTests {

private static SingleConnectionDataSource dataSource;
	
	private JdbcWeatherDao weatherDao;

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
		String sqlInsertWeather = "INSERT INTO weather (parkcode, fivedayforecastvalue, low, high, forecast)"
				+ "VALUES (“TP”, 1, 50, 80, “rain”)";
	    JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
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
	public void test() {
		fail("Not yet implemented");
	}

}
