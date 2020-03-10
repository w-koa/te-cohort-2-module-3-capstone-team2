package com.techelevator.npgeek.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.techelevator.npgeek.JdbcParkDao;
import com.techelevator.npgeek.JdbcWeatherDao;
import com.techelevator.npgeek.Park;
import com.techelevator.npgeek.Weather;

@Controller
public class NationalParkController {

	@Autowired
	private JdbcParkDao parkDao;
	@Autowired
	private JdbcWeatherDao weatherDao;
	
	@RequestMapping(path = {"/", "/homepage"}, method = RequestMethod.GET)
	public String displayHome(ModelMap map, HttpSession session) {
		
		List<Park> parks = parkDao.getAllParks();
		map.addAttribute("parks", parks);
		session.setAttribute("parks", parks);
		
		return "homepage";
	}
	
	@RequestMapping(path = "/park/details", method = RequestMethod.GET)
	public String displayParkDetails(@RequestParam String parkCode, ModelMap map, HttpSession session) {
		
		List<Weather> weatherForecasts = weatherDao.getForecastByCode(parkCode);
		
		Park park = parkDao.getParkByCode(parkCode);
		map.addAttribute("park", park);
		session.setAttribute("park", park);
		return "parkdetails";
	}
	
	@RequestMapping(path="/survey", method = RequestMethod.GET)
	public String displaySurvey() {
		
		return "survey";
	}
	
}
