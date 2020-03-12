package com.techelevator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.sql.SQLException;
import java.time.LocalDate;
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
import com.techelevator.npgeek.JdbcSurveyDao;
import com.techelevator.npgeek.JdbcWeatherDao;
import com.techelevator.npgeek.Park;

public class ParkDaoTests {

	private static SingleConnectionDataSource dataSource;
	
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
				+ "VALUES ('TP', 'TestPark', 'Michigan', 30, 50, 120.0, 323, 'tropical', 2000, "
				+ "100000, 'test parks are great places', 'tim', 'test parks see millions of visitors', 8, 5000)";
	  
	    JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
	    jdbcTemplate.update(sqlInsertPark);
	    parkDao = new JdbcParkDao(dataSource);

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
	public void testGetAllParks() {
		List<Park> parkList = parkDao.getAllParks();
		int counter = 0;
		for (Park park : parkList) {
			counter++;
		}
		assertTrue(counter > 0);
		assertTrue(parkList.size() == counter);
	}

	@Test
	public void testGetParkByCode() {
		Park park = parkDao.getParkByCode("TP");
		String expected = "TP";
		String actual = park.getParkCode();
		assertEquals(expected, actual);
	}
	
	@Test
	public void testGetAllParks2() {
		
	}
}
