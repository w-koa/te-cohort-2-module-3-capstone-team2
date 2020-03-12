package com.techelevator.npgeek;

public class IgnoreTHis {
	
	static JdbcWeatherDao weatherDao;
	public static void main(String[] args) {

		Weather weatherTest = new Weather();
		weatherTest.setForecast("partly cloudy");
		weatherTest.setForecastValue(70);
		weatherTest.setHighTemp(70);
		weatherTest.setLowTemp(60);
		weatherTest.setRecommendation(weatherDao.makeRecommendation(weatherTest, "F"));

		System.out.println(weatherTest.getRecommendation());
	}

}
