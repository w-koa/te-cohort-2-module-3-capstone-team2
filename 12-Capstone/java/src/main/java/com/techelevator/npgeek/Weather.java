package com.techelevator.npgeek;

public class Weather {
private String parkCode;
private int forecastValue;
private int lowTemp;
private int highTemp;
private String forecast;

public String getParkCode() {
	return parkCode;
}
public void setParkCode(String parkCode) {
	this.parkCode = parkCode;
}
public int getForecastValue() {
	return forecastValue;
}
public void setForecastValue(int forecastValue) {
	this.forecastValue = forecastValue;
}
public int getLowTemp() {
	return lowTemp;
}
public void setLowTemp(int lowTemp) {
	this.lowTemp = lowTemp;
}
public int getHighTemp() {
	return highTemp;
}
public void setHighTemp(int highTemp) {
	this.highTemp = highTemp;
}
public String getForecast() {
	return forecast;
}
public void setForecast(String forecast) {
	this.forecast = forecast;
}
}
