package msn.weather_app.controller;

import java.util.ArrayList;

import msn.weather_app.model.Metadata;
import msn.weather_app.database.DatabaseClass;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
public class APIController {
	
	@GetMapping("/metadata")
	public ArrayList<Metadata> getMetadata(){
		return DatabaseClass.getMeta();
	}
	
}
