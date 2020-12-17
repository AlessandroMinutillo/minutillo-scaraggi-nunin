package msn.weather_app.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.json.JSONObject;

import msn.weather_app.model.Coord;
import msn.weather_app.model.RecordMeteo;
import msn.weather_app.util.parser.ParserMeteo;

public class OpenWeatherService {
	
	public static RecordMeteo APICall (String lat, String lon) {
		String url=new String ();
		JSONObject ret=new JSONObject();
		url="http://api.openweathermap.org/data/2.5/weather?lat="+lat+"&lon="+lon+"&appid=";
		url+="81678d8aee5508c1bcf509a2f142dbaa";
		String file = new String ();
		RecordMeteo rec=new RecordMeteo();
		try {
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
		catch (IOException e) {
			e.printStackTrace();
			
		}
		return rec;
	}
	
}
