package msn.weather_app;
/**
 * Implementazione della classe WeatherAppApplication
 *  
 * @author Alessandro Minutillo 
 * @author Vito Scaraggi
 * @author Davide Nunin
 */
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import msn.weather_app.database.DatabaseClass;

@SpringBootApplication
@EnableScheduling
public class WeatherAppApplication {
	/**
	 * Carica i file di configurazione e avvia l'applicazione SpringBoot
	 * @param args
	 * @see msn.weather_app.database.DatabaseClass#loadCityList()
	 * @see msn.weather_app.database.DatabaseClass#loadMeteoData()
	 */
	public static void main(String[] args) {
		DatabaseClass.loadCityList();
		DatabaseClass.loadMeteoData();
		SpringApplication.run(WeatherAppApplication.class, args);
	}
}