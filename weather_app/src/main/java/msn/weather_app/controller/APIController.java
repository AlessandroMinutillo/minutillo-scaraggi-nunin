package msn.weather_app.controller;

import java.util.ArrayList;

import msn.weather_app.model.Metadata;
import msn.weather_app.model.City;
import msn.weather_app.model.RecordMeteo;
import msn.weather_app.service.OpenWeatherService;
import msn.weather_app.database.DatabaseClass;
import org.springframework.web.bind.annotation.RestController;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
	@GetMapping("/now")
	public RecordMeteo getMeteoNow(@RequestParam(name="lat",defaultValue = "0")String lat ,@RequestParam(name="lon",defaultValue = "0") String lon) {
	
		return OpenWeatherService.APICall(lat,lon);
	}
	
}
