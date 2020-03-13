package com.techelevator.npgeek;

public class Weather {

	// Attributes
	private String parkCode;
	private int forecastValue;
	private int lowTemp;
	private int highTemp;
	private String forecast;
	private String recommendation;

	// Method(s)
	// Builds a string for weather recommendations
	public String makeRecommendation(String tempPreference) {
		String recommendation = "";
		int highTemp = this.highTemp;
		int lowTemp = this.lowTemp;
		
		// If Celcius, convert to Fahrenheit in order to properly build string
		if (tempPreference.equals("C")) {
			highTemp = (int)  (9 * highTemp / 5) + 32;
			lowTemp = (int) (9 * lowTemp / 5) + 32;
		}
		
		// Adds to recommendation string based on weather conditions
		if (lowTemp < 20) {
			recommendation = recommendation + "Beware of frigid cold and stay warm! ";
		}
		if (highTemp > 75) {
			recommendation = recommendation + "Bring an extra gallon of water! ";
		}
		if ((highTemp - lowTemp) >= 20) {
			recommendation = recommendation + "Wear breathable layers! ";
		}
		if (getForecast().equals("sunny")) {
			recommendation = recommendation + "Pack sun block! ";
		}
		if (getForecast().equals("rain")) {
			recommendation = recommendation + "Pack rain gear and wear waterproof shoes! ";
		}
		if (getForecast().equals("snow")) {
			recommendation = recommendation + "Pack snow shoes! ";
		}
		if (getForecast().equals("thunderstorms")) {
			recommendation = recommendation + "Seek shelter and avoid hiking on exposed ridges! ";
		} 
		recommendation = recommendation + "Enjoy the day! ";

		// Convert back to Celcius for display
		if (tempPreference.equals("C")) {
			highTemp = (int) ((highTemp - 32) / 1.8);
			lowTemp = (int) ((lowTemp - 32) / 1.8);
		}
		return recommendation;
	}

	// Gets and Sets
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

	public String getRecommendation() {
		return recommendation;
	}

	public void setRecommendation(String recommendation) {
		this.recommendation = recommendation;
	}
}
