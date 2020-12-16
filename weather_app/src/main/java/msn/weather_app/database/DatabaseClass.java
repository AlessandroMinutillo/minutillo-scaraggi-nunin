package msn.weather_app.database;
import java.util.ArrayList;

import msn.weather_app.model.Metadata;
import msn.weather_app.model.RecordMeteo;
import msn.weather_app.model.City;
import msn.weather_app.util.parser.*;


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
	
	public static ArrayList<City> getCityList(){
		return cityList;
	}
	
	public static ArrayList<RecordMeteo> getMeteoData(){
		return meteoData;
	}
	
	public static void loadCityList() {
		cityList = ParserCity.load();
	}
	
	public static void loadMeteoData() {
		meteoData = ParserMeteo.load();
	}
}
