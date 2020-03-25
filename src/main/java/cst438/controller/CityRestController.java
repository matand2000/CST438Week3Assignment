package cst438.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import cst438.domain.*;
import cst438.service.CityService;

@RestController
public class CityRestController {
	
	@Autowired
	private CityService cityService;
	
	@GetMapping("/api/cities/{city}")
	public ResponseEntity<CityInfo> getWeather(@PathVariable("city") String cityName) {
		CityInfo city = cityService.getCityInfo(cityName);
		if (city == null) {
			return new ResponseEntity<CityInfo>( HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<CityInfo>(city, HttpStatus.OK);
	}
	
}
