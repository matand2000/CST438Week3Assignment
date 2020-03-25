package cst438.service;



import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cst438.domain.*;

@Service
public class CityService {
	
	
	@Autowired
	private RabbitTemplate rabbitTemplate;
	
	@Autowired
	private FanoutExchange fanout;
	
	@Autowired
	private CityRepository cityRepository;
	
	@Autowired
	private CountryRepository countryRepository;
	
	@Autowired
	private WeatherService weatherService;
	
	public CityInfo getCityInfo(String cityName) {
		
		List<City> cityList = cityRepository.findByName(cityName);
		
		if (cityList.size() > 0) {
			City city = cityList.get(0);
			Country country = countryRepository.findByCode(city.getCountryCode());
		
			TempAndTime temptime = weatherService.getTempAndTime(cityName);
		
			double temp = Math.round((temptime.temp - 273.15) * 9.0/5.0 + 32.0);
		
			Long timeEpoch = temptime.time;
			long timezone = temptime.timezone;
			long localEpochTime = timeEpoch + timezone;
			Date localDate = new Date(localEpochTime);
			SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
		
			String time = formatter.format(localDate);

			return new CityInfo(city.getId(), city.getName(), city.getCountryCode(), country.getName(), city.getDistrict(), city.getPopulation(), temp, time); 
		}
		else {
			return null;
		}
	}
	
	public void requestReservation(String cityName, String level, String email) {
		String msg = "{\"cityName\": \""+ cityName + "\" \"level\": \""+level+"\" \"email\": \""+email+"\"}";
		System.out.println("Sending message:"+msg);
		rabbitTemplate.convertSendAndReceive(fanout.getName(), "", msg);
	}
	
}