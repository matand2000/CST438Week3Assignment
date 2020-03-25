package cst438hw2.service;
 
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.BDDMockito.given;

import java.text.SimpleDateFormat;

import java.util.Date;
import java.util.List;

import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import static org.mockito.ArgumentMatchers.anyString;

import cst438.domain.*;
import cst438.service.CityService;
import cst438.service.WeatherService;
 
@SpringBootTest
public class CityServiceTest {

	@MockBean
	private WeatherService weatherService;
	
	@Autowired
	private CityService cityService;
	
	@MockBean
	private CityRepository cityRepository;
	
	@MockBean
	private CountryRepository countryRepository;

	
	@Test
	public void contextLoads() {
	}


	@Test
	public void testCityFound() throws Exception {
		CityInfo testCity = new CityInfo(1, "Kabul", "AFG", "Afghanistan", "Kabol", 1780000, 52.0, "00:10:00");
		
        given(cityRepository.findByName("Kabul")).willReturn((List<City>) testCity);
        
        CityInfo actual   = cityService.getCityInfo("Kabul");

        assertThat( actual ).isEqualTo(testCity);
	}
	
	@Test 
	public void  testCityNotFound() {
		CityInfo testCity = new CityInfo(1, "Jananan", "AFG", "Afghanistan", "Kabol", 1780000, 52.0, "00:10:00");
		
        given(cityRepository.findByName("Jananan")).willReturn((List<City>) testCity);
        
        CityInfo actual   = cityService.getCityInfo("Jananan");

        assertThat( actual ).isEqualTo(testCity);
	}
	
	@Test 
	public void  testCityMultiple() {
		// TODO 
		
	}

}
