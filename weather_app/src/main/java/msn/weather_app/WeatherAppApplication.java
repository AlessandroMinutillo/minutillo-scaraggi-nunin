package msn.weather_app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import msn.weather_app.database.DatabaseClass;

@SpringBootApplication
public class WeatherAppApplication {
	/**
	 * carica i file di configurazione e avvia l' applicazione SpringBoot
	 * @param args
	 * 
	 */
	public static void main(String[] args) {
		DatabaseClass.loadCityList();
		DatabaseClass.loadMeteoData();
		SpringApplication.run(WeatherAppApplication.class, args);
	}
}