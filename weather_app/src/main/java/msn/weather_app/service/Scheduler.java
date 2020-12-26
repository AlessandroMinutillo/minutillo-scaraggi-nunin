package msn.weather_app.service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import org.json.JSONArray;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import msn.weather_app.database.DatabaseClass;
import msn.weather_app.exception.CoordException;
import msn.weather_app.model.Coord;
import msn.weather_app.model.RecordMeteo;

@Component
public class Scheduler {
	private static void readSample() {
		try {
		BufferedReader fin = new BufferedReader(new FileReader("config/CitiesToSample.json"));
		text= fin.readLine();
		fin.close();
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	
		private static String text;
	@Scheduled(fixedRate =3600000 )
	public static void update() {
		try {
		readSample();
		JSONArray top =new JSONArray(text);
		ArrayList<Coord> cities= new ArrayList<Coord>();
		for(int i=0;i<top.length();i++) {
			cities.add(new Coord(top.getJSONObject(i).getDouble("lat"), top.getJSONObject(i).getDouble("lon") ) );
		}
		ArrayList<RecordMeteo> data =new ArrayList<RecordMeteo>();
		for(Coord element : cities) {
			data.add( OpenWeatherService.APICall( String.valueOf( element.getLat() ), String.valueOf( element.getLon() ) ) ); 
		}
		
		DatabaseClass.updateMeteoData(data);
		DatabaseClass.save();		
		
		}catch(CoordException e) {
			System.out.println("Wrong coordinates");
		}
	}
	
}
