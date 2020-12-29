package msn.weather_app.util.parser;

import msn.weather_app.model.City;
import msn.weather_app.model.Coord;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.StreamCorruptedException;
import java.util.ArrayList;
/**
 * Classe contenente tutti i metodi per il parsing del file city.list.min.json
 * @author Alessandro Minutillo 
 * @author Vito Scaraggi
 * @author Davide Nunin
 * 
 */
public class ParserCity implements Parser<City>{
	/**
	 * Legge i dati contenuti in city.list.min.json , e li restituisce sotto forma di ArrayList<City>
	 * @return cityList
	 * @throws FileNotFoundException, IoException, StreamCorruptedException
	 */
	public  ArrayList<City> load() {
		
		ArrayList<City> cityList = new ArrayList<City>();
		
		try {
			
			BufferedReader fin = new BufferedReader(new FileReader("config/city.list.min.json"));
			String data = fin.readLine();			
			fin.close();
			
			JSONArray v = new JSONArray(data);
			
			for(int i=0;i<v.length();i++) {
				JSONObject top = (JSONObject) v.get(i);
				City city = mkCity(top);
				cityList.add(city);
			}
		}
		catch(FileNotFoundException e) {
			e.printStackTrace();
		}
		catch (StreamCorruptedException e) {
			e.printStackTrace();
		}
		catch(IOException e) {
			System.out.println("I/O exception\n" + e);
		}
		
		return cityList;
	}
	/**
	 * Costruisce un oggetto di tipo city a partire da un JSONObject 
	 * @param top
	 * @return city
	 */
	private static City mkCity(JSONObject top) {
		JSONObject obj = top.getJSONObject("coord");
		Coord coord = new Coord(obj.getDouble("lat"), obj.getDouble("lon"));
		String name = top.getString("name");
		return new City(name,coord);
	}
}
