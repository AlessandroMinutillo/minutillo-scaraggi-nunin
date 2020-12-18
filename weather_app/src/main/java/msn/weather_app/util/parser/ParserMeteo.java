package msn.weather_app.util.parser;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import msn.weather_app.model.RecordMeteo;
import msn.weather_app.model.City;
import msn.weather_app.model.Coord;
import msn.weather_app.model.Temp;

public class ParserMeteo {
	
public static ArrayList<RecordMeteo> load() {
		
		ArrayList<RecordMeteo> meteoData = new ArrayList<RecordMeteo>();
		
		try {
			
			//Scanner fin = new Scanner (new BufferedReader(new FileReader("config/city.list.min.json")));
			BufferedReader fin = new BufferedReader(new FileReader("config/meteo.data.json"));
			//String data = fin.nextLine();
			String data = fin.readLine();
			
			fin.close();
			JSONArray v = new JSONArray(data);
			
			for(int i=0;i<v.length();i++) {
				JSONObject top = (JSONObject) v.get(i);
				RecordMeteo meteo = mkRecordMeteo(top);
				meteoData.add(meteo);
			}
		}
		catch(IOException e) {
			System.out.println("Eccezione I/O\n" + e);
		}
		
		return meteoData;
	}
	
	private static RecordMeteo mkRecordMeteo(JSONObject top) {
		
		long epoch = top.getLong("epoch");
		double press = top.getDouble("press");
		
		JSONObject obj1 = top.getJSONObject("city");
		JSONObject obj2 = top.getJSONObject("temp");
		JSONObject obj3 = obj1.getJSONObject("coord");
		
		String name = obj1.getString("name");
		Coord coord = new Coord(obj3.getDouble("lat"),obj3.getDouble("lon"));
		City city = new City(name,coord);
		Temp temp = new Temp(obj2.getDouble("cur"),obj2.getDouble("min"),obj2.getDouble("max"),obj2.getDouble("felt"));
		
		return new RecordMeteo(temp,press,epoch,city);
	}
	
}
