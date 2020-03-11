package com.techelevator.npgeek;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

@Component
public class JdbcParkDao implements ParkDao {

	private JdbcTemplate jdbcTemplate;

	@Autowired
	public JdbcParkDao(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public List<Park> getAllParks() {
		List<Park> getAllParks = new ArrayList<>();
		String sqlSelectAllParks = "SELECT * FROM park";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlSelectAllParks);
		while (results.next()) {
			getAllParks.add(mapRowToPark(results));
		}
		return getAllParks;
	}

	private Park mapRowToPark(SqlRowSet row) {
		Park park = new Park();
		park.setParkCode(row.getString("parkcode"));
		park.setParkName(row.getString("parkname"));
		park.setState(row.getString("state"));
		park.setDescription(row.getString("parkdescription"));
		park.setAcreage(row.getInt("acreage"));
		park.setElevationInFeet(row.getInt("elevationinfeet"));
		park.setMilesOfTrail(row.getDouble("milesoftrail"));
		park.setNumberOfCampsites(row.getInt("numberofcampsites"));
		park.setClimate(row.getString("climate"));
		park.setYearFounded(row.getInt("yearfounded"));
		park.setAnnualVisitors(row.getInt("annualvisitorcount"));
		park.setQuoteAuthor(row.getString("inspirationalquotesource"));
		park.setQuote(row.getString("inspirationalquote"));
		park.setDescription(row.getString("parkdescription"));
		park.setEntryFee(row.getInt("entryfee"));
		park.setAnimalSpecies(row.getInt("numberofanimalspecies"));
		return park;
	}

	@Override
	public Park getParkByCode(String parkCode) {
		Park park = null;
		String sqlSelectParkByCode = "SELECT * FROM park WHERE parkcode = ?";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlSelectParkByCode, parkCode);
		if(results.next()) {
			park = mapRowToPark(results);
		}
		return park;
	}

}
