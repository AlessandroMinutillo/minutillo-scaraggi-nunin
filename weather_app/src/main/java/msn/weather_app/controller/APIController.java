package msn.weather_app.controller;

import java.util.ArrayList;

import msn.weather_app.model.Metadata;
import msn.weather_app.model.City;
import msn.weather_app.model.RecordMeteo;
import msn.weather_app.database.DatabaseClass;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
public class APIController {
	
	@GetMapping("/metadata")
	public ArrayList<Metadata> getMetadata(){
		return DatabaseClass.getMetadata();
	}
	
	@GetMapping("/data/city")
	public ArrayList<City> getAllCities(){
		return DatabaseClass.getCityList();
	}
	
	@GetMapping("/data/meteo")
	public ArrayList<RecordMeteo> getMeteo(){
		return DatabaseClass.getMeteoData();
	}
}
