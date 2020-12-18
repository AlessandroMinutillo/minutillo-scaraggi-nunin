package msn.weather_app.controller;

import java.util.ArrayList;

import msn.weather_app.model.Metadata;
import msn.weather_app.model.City;
import msn.weather_app.model.RecordMeteo;
import msn.weather_app.database.DatabaseClass;
import org.springframework.web.bind.annotation.RestController;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
	
	@GetMapping("/dictionary")
	public ArrayList<City> searchCity(@RequestParam(name = "string")String string){
		return DatabaseClass.getSearchedCity(string);
	}
	
	/*@PostMapping("/data/meteo/filtered")
	public ArrayList<RecordMeteo> getMeteoFiltered(@RequestBody JSONObject body){
		return FilterService.getMeteoFilteredData();
	}*/
}
