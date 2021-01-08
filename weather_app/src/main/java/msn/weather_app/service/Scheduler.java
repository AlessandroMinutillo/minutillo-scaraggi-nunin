package msn.weather_app.service;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import org.json.JSONArray;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import msn.weather_app.database.DatabaseClass;
import msn.weather_app.exception.CoordException;
import msn.weather_app.model.City;
import msn.weather_app.model.Coord;
import msn.weather_app.model.RecordMeteo;

/**
 * Implementazione dello Scheduler
 * 
 * @author Alessandro Minutillo 
 * @author Vito Scaraggi
 * @author Davide Nunin
 */

@Component
public class Scheduler {
	/**
	 * 
	 * @return cities un ArrayList di città contenute nel file CitiesToSample.json
	 */
	public static ArrayList<City> readSample() {
		String text = "";
		try {
			BufferedReader fin = new BufferedReader(new FileReader("config/CitiesToSample.json"));
			text= fin.readLine();
			fin.close();
		}
		catch(FileNotFoundException e) {
			System.out.println("File not found\n" + e);
		}
		catch(IOException e) {
			System.out.println("I/O error\n" + e);
		}
		
		JSONArray top = new JSONArray(text);
		ArrayList<City> cities= new ArrayList<City>();
		for(int i=0;i<top.length();i++) {
			Coord coord = new Coord(top.getJSONObject(i).getDouble("lat"), top.getJSONObject(i).getDouble("lon") );
			cities.add(new City(top.getJSONObject(i).getString("name"), coord));
		}
		return cities;
	}
	
	/**Aggiorna ad intervalli di un' ora il file meteo.data.json
	 */
	@Scheduled(fixedRate =3600000 )
	public static void update() {
		try {
		ArrayList<City> cities= readSample();
		ArrayList<RecordMeteo> data =new ArrayList<RecordMeteo>();
		for(City element : cities) {
			data.add( OpenWeatherService.APICall( String.valueOf( element.getCoord().getLat() ), String.valueOf( element.getCoord().getLon() ) ) ); 
		}
		
		DatabaseClass.updateMeteoData(data);
		DatabaseClass.save();		
		
		}catch(CoordException e) {
			System.out.println("Wrong coordinates\n" + e);
		}
	}
	
}
