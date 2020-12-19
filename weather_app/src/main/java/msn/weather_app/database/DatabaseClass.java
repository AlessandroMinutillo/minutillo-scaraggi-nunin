package msn.weather_app.database;
import java.util.ArrayList;

import msn.weather_app.model.Metadata;
import msn.weather_app.model.RecordMeteo;
import msn.weather_app.service.FilterService;
import msn.weather_app.model.City;
import msn.weather_app.util.filter.Filter;
import msn.weather_app.util.filter.FilterSubstrC;
import msn.weather_app.util.parser.*;
/**
 * contiene i dati del meteo e delle città esistenti
 * @see City, RecordMeteo
 *
 */

public class DatabaseClass {

	private static ArrayList<Metadata> metadata= new ArrayList<Metadata>();
	private static ArrayList<City> cityList = new ArrayList<City>();
	private static ArrayList<RecordMeteo> meteoData = new ArrayList<RecordMeteo>();
	
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
	 * restituisce la lista delle città
	 * @return cityList
	 */
	public static ArrayList<City> getCityList(){
		return cityList;
	}
	/**
	 * restituisce la lista delle rilevazioni meteo
	 * @return meteodata
	 */
	public static ArrayList<RecordMeteo> getMeteoData(){
		return meteoData;
	}
	/**
	 * restituisce la lista delle città in base a un determinato filtro
	 * @param string
	 * @return
	 */
	public static ArrayList<City> getSearchedCity(String string){
		FilterService <City> fs = new FilterService <City>();
        return fs.applyFilter(new FilterSubstrC(string), cityList);
	}
	
	public static ArrayList<RecordMeteo> getSearchedRecord(Filter<RecordMeteo> filter){
		FilterService <RecordMeteo> fs = new FilterService <RecordMeteo>();
        return fs.applyFilter(filter, meteoData);
	}
	
	/**
	 * carica la lista delle città dal file di configurazione city.list.min.json
	 */
	public static void loadCityList() {
		cityList = ParserCity.load();
	}
	/**
	 * carica la lista delle rilevazioni meteo dal file meteo.data.json
	 */
	public static void loadMeteoData() {
		meteoData = ParserMeteo.load();
	}
}
