package cst438.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import cst438.domain.*;
import cst438.service.CityService;

@Controller
public class CityController {
	
	@Autowired
	private CityService cityService;
	
	@GetMapping("/cities/{city}")
	public String getWeather(@PathVariable("city") String cityName, Model model) {
		CityInfo city = cityService.getCityInfo(cityName);
        model.addAttribute("id", city.getId());
        model.addAttribute("name", city.getName());
        model.addAttribute("countryCode", city.getCountryCode());
        model.addAttribute("countryName", city.getCountryName());
        model.addAttribute("district", city.getDistrict());
        model.addAttribute("population", city.getPopulation());
        model.addAttribute("temp", city.getTemp());
        model.addAttribute("time", city.getTime());
		return "cities";
	} 
	
	@PostMapping("/cities/reservation")
	public String createReservation(
			@RequestParam("city") String cityName,
			@RequestParam("level") String level,
			@RequestParam("email") String email,
			Model model) {
		model.addAttribute("city", cityName);
		model.addAttribute("level", level);
		model.addAttribute("email", email);
		cityService.requestReservation(cityName, level, email);
		return "request_reservation";
	}
}