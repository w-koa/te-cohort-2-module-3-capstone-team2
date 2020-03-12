package com.techelevator.npgeek;

public class IgnoreTHis {
	
	public static void main(String[] args) {

		Weather weatherTest = new Weather();
		weatherTest.setForecast("partly cloudy");
		weatherTest.setForecastValue(70);
		weatherTest.setHighTemp(70);
		weatherTest.setLowTemp(50);
		weatherTest.setRecommendation(weatherTest.makeRecommendation("F"));

		System.out.println(weatherTest.getRecommendation());
	}

}
