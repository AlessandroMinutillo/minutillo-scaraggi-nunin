package msn.weather_app.controller;
/**
 * Implementazione della classe APIController
 * In questa classe verranno definite tutti i possbili metodi GET e POST 
 * della nostra SpringBootApp
 * @author Alessandro Minutillo 
 * @author Vito Scaraggi
 * @author Davide Nunin
 */
import java.util.ArrayList;
import java.util.HashMap;

import msn.weather_app.model.Metadata;
import msn.weather_app.model.City;
import msn.weather_app.model.RecordMeteo;
import msn.weather_app.service.OpenWeatherService;
import msn.weather_app.service.Scheduler;
import msn.weather_app.util.filter.Filter;
import msn.weather_app.util.parser.ParserFilter;
import msn.weather_app.util.stats.Stats;
import msn.weather_app.util.stats.StatsCalculator;
import msn.weather_app.database.DatabaseClass;
import msn.weather_app.exception.CoordException;

import org.springframework.web.bind.annotation.RestController;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
public class APIController {
	/**
	 * Metodo GET per la visualizzazione dei metadata
	 * @return i metadata del progetto stesso
	 * @see msn.weather_app.database.DatabaseClass#getMetadata()
	 */
	@GetMapping("/metadata")
	public ArrayList<Metadata> getMetadata(){
		return DatabaseClass.getMetadata();
	}
	/**
	 * Metodo GET per la visualizzazione di ArrayList<City> cityList
	 * @return il file di configurazione in formato JSON
	 * @see msn.weather_app.database.DatabaseClass#getCityList()
	*/
	
	@GetMapping("/data/city")
	public ArrayList<City> getAllCities(){
		return DatabaseClass.getCityList();
	} 
	
	/**
	 * Metodo GET per la visualizzazione del file di configurazione CitiesToSample.json
	 * @return il file di configurazione in formato JSON
	 * @see msn.weather_app.service.Scheduler#readSample()
	*/
	
	
	@GetMapping("/data/sampled")
	public ArrayList<City> getSampled(){
		return Scheduler.readSample();
	} 
	
	/**
	 * Metodo GET per la visualizzazione di ArrayList<RecordMeteo> meteoData 
	 * @return il record MeteoData
	 * @see msn.weather_app.database.DatabaseClass#getMeteoData()
	 */
	
	@GetMapping("/data/meteo")
	public ArrayList<RecordMeteo> getMeteo(){
		return DatabaseClass.getMeteoData();
	}
	/**
	 * Metodo POST per visualizzare determinati record in
	 * base ai filtri utilizzati
	 * @param body rappresenta la struttura del filtro da introdurre
	 * @return i record filtrati
	 * @see msn.weather_app.util.parser.ParserFilter#getFilter(JSONObject)
	 * @see msn.weather_app.database.DatabaseClass#getSearchedRecord(Filter)
	 */
	@PostMapping("/data/meteo/filtered")
	public ArrayList<RecordMeteo> getMeteoFiltered(@RequestBody String body){
		try {
			Filter<RecordMeteo> filter = ParserFilter.getFilter(new JSONObject(body));
			return DatabaseClass.getSearchedRecord(filter);
		}
		catch(JSONException e) {
			System.out.println("Malformed JSONObject: returning empty array\n" + e);
			return new ArrayList<RecordMeteo>();
		}
	}
	/**
	 * Metodo GET per visualizzare le coordinate di una determinata città 
	 * oppure le coordinate di tutte le città contenenti l'eventuale sottostringa
	 * @param string rappresenta la sottostringa che verrà cercata all'interno del nostro data
	 * @return tutte le città che contengono la sottostringa e le loro relative coordinate
	 * @see msn.weather_app.database.DatabaseClass#getSearchedCity(String)
	 */
	@GetMapping("/dictionary")
	public ArrayList<City> searchCity(@RequestParam(name = "string")String string){
		return DatabaseClass.getSearchedCity(string);
	}
	
	/**
	 * Metodo GET per visualizzare il meteo corrente di una coppia di coordinate geografiche valide come JSONObject
	 * @param lat indica la latitudine
	 * @param lon indica la longitudine
	 * @return il JSONObject rappresentante il meteo attuale di una determinata città
	 * @throws CoordException
	 * @see msn.weather_app.service.OpenWeatherService#APICall
	 * @see msn.weather_app.exception.CoordException
	 */
	@GetMapping("/now")
	public RecordMeteo getMeteoNow(@RequestParam(name="lat",defaultValue = "0")String lat ,@RequestParam(name="lon",defaultValue = "0") String lon) throws CoordException {
	
		return OpenWeatherService.APICall(lat,lon);
	}
	
	/**
	 * Metodo POST per visualizzare le statistiche sulla temperatura dei dati meteo filtrati
	 * @param body rappresenta la struttura del filtro da introdurre
	 * @return le statistiche sui dati meteo filtrati
	 * @see msn.weather_app.stats.StatsCalculator#calcTemp(ArrayList<RecordMeteo>)
	 * @see msn.weather_app.util.parser.ParserFilter#getFilter(JSONObject)
	 */
	
	@PostMapping("/stats/temp")
	public HashMap<String,Double> getStatsTempFiltered(@RequestBody String body){
		try {
			Filter<RecordMeteo> filter = ParserFilter.getFilter(new JSONObject(body));
			ArrayList<RecordMeteo> sample = DatabaseClass.getSearchedRecord(filter);
			return StatsCalculator.calcTemp(sample).getStat();
		}
		catch(JSONException e) {
			System.out.println("Malformed JSONObject: returning empty stats\n" + e);
			return new Stats().getStat();
		}
	}
	
	/**
	 * Metodo POST per visualizzare le statistiche sulla pressione dei dati meteo filtrati
	 * @param body rappresenta la struttura del filtro da introdurre
	 * @return le statistiche sui dati meteo filtrati
	 * @see msn.weather_app.stats.StatsCalculator#calcPress(ArrayList<RecordMeteo>)
	 * @see msn.weather_app.util.parser.ParserFilter#getFilter(JSONObject)
	 */
	
	@PostMapping("/stats/press")
	public HashMap<String,Double> getStatsPressFiltered(@RequestBody String body){
		try {
			Filter<RecordMeteo> filter = ParserFilter.getFilter(new JSONObject(body));
			ArrayList<RecordMeteo> sample = DatabaseClass.getSearchedRecord(filter);
			return StatsCalculator.calcPress(sample).getStat();
		}
		catch(JSONException e) {
			System.out.println("Malformed JSONObject: returning empty stats\n" + e);
			return new Stats().getStat();
		}
	}
}
