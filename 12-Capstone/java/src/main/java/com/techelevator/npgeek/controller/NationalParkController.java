package com.techelevator.npgeek.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.techelevator.npgeek.JdbcParkDao;
import com.techelevator.npgeek.Park;

@Controller
public class NationalParkController {

	@Autowired
	private JdbcParkDao parkDao;
	
	@RequestMapping(path = {"/", "/homepage"}, method = RequestMethod.GET)
	public String displayHome(ModelMap map) {
		
		List<Park> parks = parkDao.getAllParks();
		map.addAttribute("parks", parks);
		
		return "homepage";
	}
	
	
	
}
