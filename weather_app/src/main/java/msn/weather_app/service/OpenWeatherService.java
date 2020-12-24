package msn.weather_app.service;
/**
 * Implementazione della classe OpenWeatherService
 * 
 * @author Alessandro Minutillo 
 * @author Vito Scaraggi
 * @author Davide Nunin
 */
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.http.HttpConnectTimeoutException;
import java.text.ParseException;

import org.json.JSONObject;

import msn.weather_app.model.Coord;
import msn.weather_app.model.RecordMeteo;
import msn.weather_app.util.parser.ParserMeteo;
import msn.weather_app.exception.CoordException;
public class OpenWeatherService {
	/**
	 * Effettua una richiesta alle API di Openweather restituendo un RecordMeteo
	 * contenente il meteo (attuale) di una coppia di coordinate geografiche valide
	 * @param lat indica la latitudine geografica della città 
	 * @param lon indica la longitudine geografica della città
	 * @return l'oggetto RecordMeteo contenente il meteo attuale alle coordinate passate
	 * @throws CoordException
	 * @see msn.weather_app.exception.CoordException
	 * @see msn.weather_app.model.RecordMeteo
	 */
	private static void setUrl(String lat, String lon) {
		try {
		BufferedReader fin = new BufferedReader(new FileReader("config/configuration.json"));
		String data = fin.readLine();			
		fin.close();
		JSONObject top =new JSONObject(data);
		url=top.getString("site")+top.getString("unit");
		url=url+"&lat="+lat+"&lon="+lon+"&appid="+top.getString("apikey");
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	private static String url;
	public static RecordMeteo APICall (String lat, String lon) throws CoordException {
	
	
		JSONObject ret=new JSONObject();
		String file = new String ();
		RecordMeteo rec=new RecordMeteo();
		
		try {
			
		double latitudine=Double.parseDouble(lat);
		double longitudine=Double.parseDouble(lon);
		Coord coord = new Coord(latitudine, longitudine);
		coord.validate();
		setUrl(lat,lon);
		HttpURLConnection  connection = (HttpURLConnection) new URL (url).openConnection();
		connection.setRequestMethod("GET");
		connection.setConnectTimeout(5000);
		connection.connect();
		
		String app= new String ();
		BufferedReader in =new BufferedReader(new InputStreamReader(connection.getInputStream()));
		 while( (app=in.readLine())!=null ) {
			 file+=app;
		 }
		 
		 ret = new JSONObject(file);
		 rec = ParserMeteo.JSONtoMeteo(ret);
		 
		}
		catch(MalformedURLException e) {
			e.printStackTrace();
		}
		catch (HttpConnectTimeoutException e) {
			e.printStackTrace();
		}
		catch(ConnectException e) {
			e.printStackTrace();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		catch (CoordException e) {
			e.printStackTrace();
		}
		return rec;
	}
		
}
