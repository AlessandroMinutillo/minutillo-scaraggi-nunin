package msn.weather_app.util.parser;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.StreamCorruptedException;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import msn.weather_app.model.RecordMeteo;
import msn.weather_app.model.City;
import msn.weather_app.model.Coord;
import msn.weather_app.model.Temp;

/**
 * Contiene i metodi usati per il parsing del file meteo.data.json
 * 
 * @author Alessandro Minutillo 
 * @author Vito Scaraggi
 * @author Davide Nunin
 */
public class ParserMeteo implements Parser<RecordMeteo>{
	
	/**
	 * Restituisce l' ArrayList contenente i dati del file meteo.data.json
	 * @return meteoData
	 * @throws IOException, FileNotFoundException, StreamCorruptedException
	 */
public ArrayList<RecordMeteo> load() {
		
		ArrayList<RecordMeteo> meteoData = new ArrayList<RecordMeteo>();
		
		try {
			
			
			BufferedReader fin = new BufferedReader(new FileReader("config/meteo.data.json"));
			String data = fin.readLine();
			
			fin.close();
			JSONArray v = new JSONArray(data);
			
			for(int i=0;i<v.length();i++) {
				JSONObject top = (JSONObject) v.get(i);
				RecordMeteo meteo = mkRecordMeteo(top);
				meteoData.add(meteo);
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
		
		return meteoData;
	}
	/**
	 * Costruisce un oggetto di tipo RecordMeteo a partire da un JSONObject
	 * @param top
	 * @return RecordMeteo
	 */
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
	/**
	 * Converte un JSONObject in RecordMeteo
	 * @return res
	 */
	public static RecordMeteo JSONtoMeteo(JSONObject obj){
		JSONObject main =obj.getJSONObject("main");
		JSONObject coord= obj.getJSONObject("coord");
		double press=main.getDouble("pressure");
		long epoch=obj.getLong("dt");
		Temp temp= new Temp(main.getDouble("temp"), main.getDouble("temp_min"), main.getDouble("temp_max"), main.getDouble("feels_like"));
		City city =new City(obj.getString("name"), new Coord(coord.getDouble("lat"),coord.getDouble("lon")));
		
		RecordMeteo res=new RecordMeteo(temp, press, epoch, city);
		return res;
	}
}
