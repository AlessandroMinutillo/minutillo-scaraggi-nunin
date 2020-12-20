package msn.weather_app.database;
/**
 * Implementazione della classe DatabaseClass
 * 
 * Contiene i dati relativi al meteo e alle città esistenti
 * 
 * @author Alessandro Minutillo 
 * @author Vito Scaraggi
 * @author Davide Nunin
 */
import java.util.ArrayList;

import msn.weather_app.model.Metadata;
import msn.weather_app.model.RecordMeteo;
import msn.weather_app.service.FilterService;
import msn.weather_app.model.City;
import msn.weather_app.util.filter.Filter;
import msn.weather_app.util.filter.FilterSubstrC;
import msn.weather_app.util.parser.*;

public class DatabaseClass {
	/**
	 * ArrayList di Metadata
	 * @see msn.weather_app.model.Metadata
	 */
	private static ArrayList<Metadata> metadata= new ArrayList<Metadata>();
	/**
	 * ArrayList di City
	 * @see msn.weather_app.model.City
	 */
	private static ArrayList<City> cityList = new ArrayList<City>();
	/**
	 * ArrayList di RecordMeteo
	 * @see msn.weather_app.model.RecordMeteo
	 */
	private static ArrayList<RecordMeteo> meteoData = new ArrayList<RecordMeteo>();
	/**
	 * Viene costruito il nostro ArrayList metadata
	 * @return metadata stesso
	 * @see msn.weather_app.model.Metadata
	 */
	public static ArrayList<Metadata> getMetadata(){
		metadata.add(new Metadata("CityName","Nome della citta'","String"));
		metadata.add(new Metadata("Lat","Latitudine","Double"));
		metadata.add(new Metadata("Lon","Longitudine","Double"));
		metadata.add(new Metadata("TempAtt","Temperatura attuale (°C)","Double"));
		metadata.add(new Metadata("PressAtt","Pressione attuale (hPa)","Double"));
		metadata.add(new Metadata("TempMin","Temperatura minima (°C)","Double"));
		metadata.add(new Metadata("TempMax","Temperatura massima (°C)","Double"));
		metadata.add(new Metadata("PressMin","Pressione minima (hPa)","Double"));
		metadata.add(new Metadata("PressMax","Pressione massima (hPa)","Double"));
		metadata.add(new Metadata("TempMedia","Media della temperatura (°C)","Double"));
		metadata.add(new Metadata("TempVar","Varianza della temperatura (°C)","Double"));
		metadata.add(new Metadata("PressMedia","Media della pressione (hPa)","Double"));
		metadata.add(new Metadata("PressVar","Varianza della pressione (hPa)","Double"));
		return metadata;
	}
	/**
	 * Restituisce la lista delle città
	 * @return cityList
	 * @see msn.weather_app.model.City
	 */
	public static ArrayList<City> getCityList(){
		return cityList;
	}
	/**
	 * Restituisce la lista delle rilevazioni meteo
	 * @return meteoData
	 * @see msn.weather_app.model.RecordMeteo
	 */
	public static ArrayList<RecordMeteo> getMeteoData(){
		return meteoData;
	}
	/**
	 * Restituisce la lista delle città in base a un determinato filtro (una stringa)
	 * @param string rappresenta la stringa che verrà cercata in cityList
	 * @return la lista delle città contenenti la stringa 
	 * @see msn.weather_app.service.FilterService
	 * @see msn.weather_app.service.FilterService#applyFilter(Filter, ArrayList)
	 */
	public static ArrayList<City> getSearchedCity(String string){
		FilterService <City> fs = new FilterService <City>();
        return fs.applyFilter(new FilterSubstrC(string), cityList);
	}
	/**
	 * Restituisce la lista di RecordMeteo opportunamente filtrati
	 * @param filter rappresenta il filtro che viene applicato
	 * @return la lista di RecordMeteo filtrata
	 * @see msn.weather_app.service.FilterService
	 * @see msn.weather_app.service.FilterService#applyFilter(Filter, ArrayList)
	 */
	public static ArrayList<RecordMeteo> getSearchedRecord(Filter<RecordMeteo> filter){
		FilterService <RecordMeteo> fs = new FilterService <RecordMeteo>();
        return fs.applyFilter(filter, meteoData);
	}
	
	/**
	 * Carica la lista delle città dal file di configurazione city.list.min.json
	 * @see msn.weather_app.util.parser.ParserCity
	 * @see msn.weather_app.util.parser.ParserCity#load()
	 */
	public static void loadCityList() {
		cityList = ParserCity.load();
	}
	/**
	 * Carica la lista delle rilevazioni meteo dal file meteo.data.json
	 * @see msn.weather_app.util.parser.ParserMeteo
	 * @see msn.weather_app.util.parser.ParserMeteo#load()
	 */
	public static void loadMeteoData() {
		meteoData = ParserMeteo.load();
	}
}
