package com.techelevator.npgeek;

public class Weather {
private String parkCode;
private int forecastValue;
private int lowInF;
private int highInF;
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
public int getLowInF() {
	return lowInF;
}
public void setLowInF(int lowInF) {
	this.lowInF = lowInF;
}
public int getHighInF() {
	return highInF;
}
public void setHighInF(int highInF) {
	this.highInF = highInF;
}
public String getForecast() {
	return forecast;
}
public void setForecast(String forecast) {
	this.forecast = forecast;
}
}
