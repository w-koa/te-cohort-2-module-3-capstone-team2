package com.techelevator.npgeek;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

@Component
public class JdbcWeatherDao implements WeatherDao {

	private JdbcTemplate jdbcTemplate;

	@Autowired
	public JdbcWeatherDao(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public List<Weather> getForecastByCode(String parkCode, String tempPreference) {
		List<Weather> weatherForecasts = new ArrayList<>();
		String sqlSelectByParkCode = "SELECT fivedayforecastvalue, low, high, forecast "
				+ "FROM weather WHERE parkcode = ?";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlSelectByParkCode, parkCode);
		while (results.next()) {
			weatherForecasts.add(mapRowToWeather(results));
		}
		if(tempPreference.equals("C")) {
			for(int i = 0; i < weatherForecasts.size(); i++) {
				double tempLowInFahrenheit = weatherForecasts.get(i).getLowTemp();
				double tempHighInFahrenheit = weatherForecasts.get(i).getHighTemp();
				double tempLowInCelcius = ((tempLowInFahrenheit - 32) * 5.0 / 9);
				double tempHighInCelcius = ((tempHighInFahrenheit - 32) * 5.0 / 9);
				int lowCInt = (int) Math.round(tempLowInCelcius);
				int highCInt = (int) Math.round(tempHighInCelcius);
				weatherForecasts.get(i).setLowTemp(lowCInt);
				weatherForecasts.get(i).setHighTemp(highCInt);
			}
		}
		return weatherForecasts; 
	}
	

	private Weather mapRowToWeather(SqlRowSet row) {

		Weather weather = new Weather();
		weather.setForecastValue(row.getInt("fivedayforecastvalue"));
		weather.setLowTemp(row.getInt("low"));
		weather.setHighTemp(row.getInt("high"));
		weather.setForecast(row.getString("forecast"));
		return weather;
	}

}
