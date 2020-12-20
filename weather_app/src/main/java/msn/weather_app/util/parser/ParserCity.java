package msn.weather_app.util.parser;

import msn.weather_app.model.City;
import msn.weather_app.model.Coord;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class ParserCity implements Parser<City>{
	
	public  ArrayList<City> load() {
		
		ArrayList<City> cityList = new ArrayList<City>();
		
		try {
			
			//Scanner fin = new Scanner (new BufferedReader(new FileReader("config/city.list.min.json")));
			BufferedReader fin = new BufferedReader(new FileReader("config/city.list.min.json"));
			//String data = fin.nextLine();
			String data = fin.readLine();			
			fin.close();
			
			JSONArray v = new JSONArray(data);
			
			for(int i=0;i<v.length();i++) {
				JSONObject top = (JSONObject) v.get(i);
				City city = mkCity(top);
				cityList.add(city);
			}
		}
		catch(IOException e) {
			System.out.println("Eccezione I/O\n" + e);
		}
		
		return cityList;
	}
	
	private static City mkCity(JSONObject top) {
		JSONObject obj = top.getJSONObject("coord");
		Coord coord = new Coord(obj.getDouble("lat"), obj.getDouble("lon"));
		String name = top.getString("name");
		return new City(name,coord);
	}
}
