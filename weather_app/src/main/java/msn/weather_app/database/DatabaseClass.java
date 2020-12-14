package msn.weather_app.database;
import java.util.ArrayList;
import msn.weather_app.model.Metadata;

public class DatabaseClass {

	private static ArrayList<Metadata> meta = new ArrayList<Metadata>();
	
	public static ArrayList<Metadata> getMeta(){
		meta.add(new Metadata("CityName","Nome della citta'","String"));
		meta.add(new Metadata("Lat","Latitudine","Double"));
		meta.add(new Metadata("Lon","Longitudine","Double"));
		meta.add(new Metadata("TempAtt","Temperatura attuale (°C)","Double"));
		meta.add(new Metadata("PressAtt","Pressione attuale (hPa)","Double"));
		meta.add(new Metadata("TempMin","Temperatura minima (°C)","Double"));
		meta.add(new Metadata("TempMax","Temperatura massima (°C)","Double"));
		meta.add(new Metadata("PressMin","Pressione minima (hPa)","Double"));
		meta.add(new Metadata("PressMax","Pressione massima (hPa)","Double"));
		meta.add(new Metadata("TempMedia","Media della temperatura (°C)","Double"));
		meta.add(new Metadata("TempVar","Varianza della temperatura (°C)","Double"));
		meta.add(new Metadata("PressMedia","Media della pressione (hPa)","Double"));
		meta.add(new Metadata("PressVar","Varianza della pressione (hPa)","Double"));
		return meta;
	}
}
