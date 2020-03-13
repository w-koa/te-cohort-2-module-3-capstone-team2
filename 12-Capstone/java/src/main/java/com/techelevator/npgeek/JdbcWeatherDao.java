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
			for(int i = 0; i< weatherForecasts.size(); i++) {
				int tempLowInFahrenheit = weatherForecasts.get(i).getLowTemp();
				int tempHighInFahrenheit = weatherForecasts.get(i).getHighTemp();
				int tempLowInCelcius = (int) ((tempLowInFahrenheit - 32) / 1.8);
				int tempHighInCelcius = (int) ((tempHighInFahrenheit - 32) / 1.8);
				weatherForecasts.get(i).setLowTemp(tempLowInCelcius);
				weatherForecasts.get(i).setHighTemp(tempHighInCelcius);
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
