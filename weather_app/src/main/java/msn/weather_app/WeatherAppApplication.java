package msn.weather_app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import msn.weather_app.database.DatabaseClass;

@SpringBootApplication
public class WeatherAppApplication {
	//questo è un commento
	public static void main(String[] args) {
		DatabaseClass.loadCityList();
		DatabaseClass.loadMeteoData();
		SpringApplication.run(WeatherAppApplication.class, args);
	}
}