package msn.weather_app.controller;

import java.util.ArrayList;
import java.util.HashMap;

import msn.weather_app.model.Metadata;
import msn.weather_app.model.City;
import msn.weather_app.model.RecordMeteo;
import msn.weather_app.model.TimeData;
import msn.weather_app.service.OpenWeatherService;
import msn.weather_app.service.Scheduler;
import msn.weather_app.util.filter.Filter;
import msn.weather_app.util.parser.ParserFilter;
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

/**
 * Implementazione della classe APIController
 * In questa classe sono definiti tutti i possbili metodi GET e POST 
 * della nostra SpringBootApp
 * @author Alessandro Minutillo 
 * @author Vito Scaraggi
 * @author Davide Nunin
 */

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
	 * Metodo GET per la visualizzazione di ArrayList di City cityList
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
	 * Metodo GET per la visualizzazione di ArrayList di RecordMeteo meteoData 
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
	 * @see msn.weather_app.model.TimeData
	 * @see msn.weather_app.util.parser.ParserFilter#getFilter(JSONObject)
	 * @see msn.weather_app.database.DatabaseClass#getSearchedRecord(Filter)
	 * @see msn.weather_app.database.DatabaseClass#freqDiv(ArrayList<RecordMeteo>,JSONObject)
	 */
	@PostMapping("/data/meteo/filtered")
	public HashMap<String,ArrayList<RecordMeteo>> getMeteoFiltered(@RequestBody String body){
		TimeData.NOW = System.currentTimeMillis()/1000;
		try {
			Filter<RecordMeteo> filter = ParserFilter.getFilter(new JSONObject(body));
			ArrayList<RecordMeteo> sample = DatabaseClass.getSearchedRecord(filter);
			return DatabaseClass.freqDiv(sample, new JSONObject(body));
		}
		catch(JSONException e) {
			System.out.println("Malformed JSONObject: returning empty data\n" + e);
			return new HashMap<String,ArrayList<RecordMeteo>>();
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
	 * @see msn.weather_app.service.OpenWeatherService#APICall(String,String)
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
	 * @see msn.weather_app.model.TimeData
	 * @see msn.weather_app.util.stats.StatsCalculator#calc(ArrayList <RecordMeteo> ,String,JSONObject)
	 * @see msn.weather_app.util.parser.ParserFilter#getFilter(JSONObject)
	 * @see msn.weather_app.database.DatabaseClass#getSearchedRecord(Filter)
	 */
	
	@PostMapping("/stats/temp")
	public HashMap<String, HashMap<String,Double> > getStatsTempFiltered(@RequestBody String body){
		TimeData.NOW = System.currentTimeMillis()/1000;
		try {
			Filter<RecordMeteo> filter = ParserFilter.getFilter(new JSONObject(body));
			ArrayList<RecordMeteo> sample = DatabaseClass.getSearchedRecord(filter);
			return StatsCalculator.calc(sample, "temp", new JSONObject(body));
		}
		catch(JSONException e) {
			System.out.println("Malformed JSONObject: returning empty stats\n" + e);
			return new HashMap<String, HashMap<String,Double> >();
		}
	}
	
	/**
	 * Metodo POST per visualizzare le statistiche sulla pressione dei dati meteo filtrati
	 * @param body rappresenta la struttura del filtro da introdurre
	 * @return le statistiche sui dati meteo filtrati
	 * @see msn.weather_app.model.TimeData
	 * @see msn.weather_app.util.stats.StatsCalculator#calc(ArrayList<RecordMeteo>,String,JSONObject)
	 * @see msn.weather_app.util.parser.ParserFilter#getFilter(JSONObject)
	 * @see msn.weather_app.database.DatabaseClass#getSearchedRecord(Filter)
	 */
	
	@PostMapping("/stats/press")
	public HashMap<String, HashMap<String,Double> > getStatsPressFiltered(@RequestBody String body){
		TimeData.NOW = System.currentTimeMillis()/1000;
		try {
			Filter<RecordMeteo> filter = ParserFilter.getFilter(new JSONObject(body));
			ArrayList<RecordMeteo> sample = DatabaseClass.getSearchedRecord(filter);
			return StatsCalculator.calc(sample, "press", new JSONObject(body));
		}
		catch(JSONException e) {
			System.out.println("Malformed JSONObject: returning empty stats\n" + e);
			return new HashMap<String, HashMap<String,Double> >();
		}
	}
}
