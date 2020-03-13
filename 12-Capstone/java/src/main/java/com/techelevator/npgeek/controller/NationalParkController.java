package com.techelevator.npgeek.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.techelevator.npgeek.JdbcParkDao;
import com.techelevator.npgeek.JdbcSurveyDao;
import com.techelevator.npgeek.JdbcWeatherDao;
import com.techelevator.npgeek.Park;
import com.techelevator.npgeek.Survey;
import com.techelevator.npgeek.Weather;

@Controller
public class NationalParkController {

	@Autowired
	private JdbcParkDao parkDao;
	@Autowired
	private JdbcWeatherDao weatherDao;
	@Autowired
	private JdbcSurveyDao surveyDao;

	/* Home Page
	 * Gets all parks and adds them to ModelMap to display info*/
	@RequestMapping(path = { "/", "/homepage" }, method = RequestMethod.GET)
	public String displayHome(ModelMap map, HttpSession session) {

		List<Park> parks = parkDao.getAllParks();
		map.addAttribute("parks", parks);
		session.setAttribute("parks", parks);

		return "homepage";
	}
	
	/* Park Details Page
	 * Loads park detail page taking parkCode as its parameter. If no tempPreference session is found, it
	 * creates one and defaults to (F). If a session exists, it gets the tempPreference and loads
	 * list of forecasts. The Jdbc method converts temperature values based on tempPreference while 
	 * accessing and adding to list.
	 * Class method makeRecommendation is used to build a string and save to the Weather class.
	 * Add to ModelMap to be displayed.
	 * Displays form for tempPreference selection (GET)
	 * */
	@RequestMapping(path = "/park/details", method = RequestMethod.GET)
	public String displayParkDetails(@RequestParam String parkCode, ModelMap map, HttpSession session) {

		if (session.getAttribute("tempPreference") == null) {
			session.setAttribute("tempPreference", "F");
		}
		String tempPref = (String) session.getAttribute("tempPreference");
		
		List<Weather> weatherForecasts = weatherDao.getForecastByCode(parkCode, tempPref);
		for (int i = 0; i < weatherForecasts.size(); i++) {
			String recommendation = weatherForecasts.get(i).makeRecommendation(tempPref);
			weatherForecasts.get(i).setRecommendation(recommendation);
		
		}
		Park park = parkDao.getParkByCode(parkCode);
		map.addAttribute("forecasts", weatherForecasts);
		map.addAttribute("park", park);
		session.setAttribute("park", park);
		session.getAttribute("tempPreference");
		return "parkdetails";
	}

	
	/* Park Details - Temperature Selection POST
	 * POST method for tempPreference selection. Saves session data for tempPreference then
	 * redirects back to Park Detail Page.
	 * */
	@RequestMapping(path = "/park/details", method = RequestMethod.POST)
	public String getTemperaturePreference(@RequestParam String parkCode, @RequestParam String tempPreference,
			HttpSession session) {
		
		Park park = parkDao.getParkByCode(parkCode);
		session.setAttribute("park", park);
		session.setAttribute("tempPreference", tempPreference);
		
		return "redirect:/park/details?parkCode=" + parkCode;
	}
	
	/* Survey Page
	 * Allows user to vote for their favorite National Park. Displays the form which writes to DB*/
	@RequestMapping(path = "/survey", method = RequestMethod.GET)
	public String displaySurvey(ModelMap modelHolder) {

		 if (!modelHolder.containsAttribute("survey")) {
	            modelHolder.put("survey", new Survey());
	        }
		return "survey";
	}
	
	
	/* Survey Submit (POST)
	 * Validates user email and displays error message if invalid. Inserts information into database if 
	 * validation is successful. Redirects to topParks page
	 * */
	@RequestMapping(path = "/survey", method = RequestMethod.POST)
	public String submitSurvey(@Valid @ModelAttribute("survey") Survey survey, BindingResult result, 
			RedirectAttributes flash) {
		
		if (result.hasErrors()) {
            flash.addFlashAttribute("survey", survey);
            flash.addFlashAttribute(BindingResult.MODEL_KEY_PREFIX + "survey", result);
            flash.addFlashAttribute("message", "Please fix the following errors:");
            return "redirect:/survey";
        }
		
		surveyDao.saveSurvey(survey);
		
		return "redirect:/topParks";
	}
	
	/* Top Parks Page
	 * Access DB to get the top five parks by parkcode count. Takes parkcode and loads
	 * all park information and saves the ParkVotes to each park for display on the page.
	 * */
	@RequestMapping(path = "/topParks", method = RequestMethod.GET)
	public String displayTopParks(ModelMap map) {
		List<Park> surveyTopFive = surveyDao.getTopFiveParks();
		List <Park> topFiveParks = new ArrayList<>();
		
		for(int i = 0; i < surveyTopFive.size(); i++) {
			Park park = parkDao.getParkByCode(surveyTopFive.get(i).getParkCode());
			park.setParkVotes(surveyTopFive.get(i).getParkVotes());
			topFiveParks.add(park);
		}
		map.addAttribute("topFiveParks", topFiveParks);
		return "topparks";
	}

}
