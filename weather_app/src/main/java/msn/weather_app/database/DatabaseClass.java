package msn.weather_app.database;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
/**
 * Implementazione della classe DatabaseClass
 * 
 * Contiene i dati relativi al meteo e alle città esistenti
 * 
 * @author Alessandro Minutillo 
 * @author Vito Scaraggi
 * @author Davide Nunin
 * 
 */
import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONObject;

import msn.weather_app.model.Metadata;
import msn.weather_app.model.Range;
import msn.weather_app.model.RecordMeteo;
import msn.weather_app.model.TimeData;
import msn.weather_app.service.FilterService;
import msn.weather_app.model.City;
import msn.weather_app.util.filter.Filter;
import msn.weather_app.util.filter.FilterFreq;
import msn.weather_app.util.filter.FilterSubstrC;
import msn.weather_app.util.parser.*;

public class DatabaseClass {
	/**
	 * ArrayList di Metadata
	 * @see msn.weather_app.model.Metadata
	 */
	private static ArrayList<Metadata> metadata= new ArrayList<Metadata>();
	/**
	 * ArrayList di City
	 * @see msn.weather_app.model.City
	 */
	private static ArrayList<City> cityList = new ArrayList<City>();
	/**
	 * ArrayList di RecordMeteo
	 * @see msn.weather_app.model.RecordMeteo
	 */
	private static ArrayList<RecordMeteo> meteoData = new ArrayList<RecordMeteo>();
	/**
	 * costruisce l'ArrayList metadata
	 * @return metadata stesso
	 * @see msn.weather_app.model.Metadata
	 */
	public static ArrayList<Metadata> getMetadata(){
		
		//metadata.add(new Metadata("city","Città","City"));
		metadata.add(new Metadata("name","Nome/i della/e città","String"));
		metadata.add(new Metadata("coord","Coordinate della città","Coord"));
		metadata.add(new Metadata("lat","Latitudine","Double"));
		metadata.add(new Metadata("lon","Longitudine","Double"));
		metadata.add(new Metadata("period","Periodo di interesse","String"));
		metadata.add(new Metadata("freq","Frequenza di interesse","String"));
		//metadata.add(new Metadata("epoch","Data in formato UNIX","Long"));
		metadata.add(new Metadata("temp","Temperatura","Temp"));
		metadata.add(new Metadata("cur","Temperatura attuale (°C)","Double"));
		metadata.add(new Metadata("min","Temperatura attuale minima (°C)","Double"));
		metadata.add(new Metadata("max","Temperatura attuale massima (°C)","Double"));
		metadata.add(new Metadata("felt","Temperatura attuale percepita (°C)","Double"));
		metadata.add(new Metadata("press","Pressione attuale (hPa)","Double"));
		
		metadata.add(new Metadata("from","Valore numerico minimo","Double"));
		metadata.add(new Metadata("to","Valore numerico massimo","Double"));
		
		/*metadata.add(new Metadata("tempMin","Temperatura minima (°C)","Double"));
		metadata.add(new Metadata("tempMax","Temperatura massima (°C)","Double"));
		metadata.add(new Metadata("tempCurAvg","Media temperatura (°C)","Double"));
		metadata.add(new Metadata("tempFeltAvg","Media temperatura percepita (°C)","Double"));
		metadata.add(new Metadata("tempCurVar","Varianza temperatura","Double"));
		metadata.add(new Metadata("tempFeltVar","Varianza temperatura percepita","Double"));
		
		metadata.add(new Metadata("pressMin","Pressione minima (hPa)","Double"));
		metadata.add(new Metadata("pressMax","Pressione massima (hPa)","Double"));
		metadata.add(new Metadata("pressMedia","Media della pressione (hPa)","Double"));
		metadata.add(new Metadata("pressVar","Varianza della pressione","Double"));
		*/
		return metadata;
	}
	/**
	 * Restituisce la lista delle città
	 * @return cityList
	 * @see msn.weather_app.model.City
	 */
	public static ArrayList<City> getCityList(){
		return cityList;
	}
	/**
	 * Restituisce la lista delle rilevazioni meteo
	 * @return meteoData
	 * @see msn.weather_app.model.RecordMeteo
	 */
	public static ArrayList<RecordMeteo> getMeteoData(){
		return meteoData;
	}
	/**
	 * Restituisce la lista delle città in base a un determinato filtro (una stringa)
	 * @param string rappresenta la stringa che verrà cercata in cityList
	 * @return la lista delle città contenenti la stringa 
	 * @see msn.weather_app.service.FilterService
	 * @see msn.weather_app.service.FilterService#applyFilter(Filter, ArrayList)
	 */
	public static ArrayList<City> getSearchedCity(String string){
		FilterService <City> fs = new FilterService <City>();
        return fs.applyFilter(new FilterSubstrC(string), cityList);
	}
	/**
	 * Restituisce la lista di RecordMeteo opportunamente filtrati
	 * @param filter rappresenta il filtro che viene applicato
	 * @return la lista di RecordMeteo filtrata
	 * @see msn.weather_app.service.FilterService
	 * @see msn.weather_app.service.FilterService#applyFilter(Filter, ArrayList)
	 */
	public static ArrayList<RecordMeteo> getSearchedRecord(Filter<RecordMeteo> filter){
		FilterService <RecordMeteo> fs = new FilterService <RecordMeteo>();
        return fs.applyFilter(filter, meteoData);
	}
	
	/**
	 * Restituisce i dati RecordMeteo relativi al periodo selezionato tramite filtro,
	 * partizionandoli in base alla frequenza richiesta dall'utente
	 * 
	 * @param sample ArrayList<RecordMeteo> da partizionare in base alla frequenza richiesta
	 * @param top JSONObject
	 * @return ret HashMap<String, ArrayList<RecordMeteo> >
	 */
	
	
	public static HashMap<String,ArrayList<RecordMeteo>> freqDiv(ArrayList<RecordMeteo> sample, JSONObject top){
		HashMap<String, ArrayList<RecordMeteo> > ret = new HashMap<String, ArrayList<RecordMeteo> >();
		
		long from, to, step;
		to = TimeData.NOW;
		
		String freq = FilterService.setFreq(top);
		step = FilterService.setStep(freq);
		from = FilterService.setFrom(top);
		
		if(freq.equals("total") ) 
			ret.put(freq, sample);
		else {
			FilterService<RecordMeteo> fs = new FilterService<RecordMeteo>();
			
			for(long cont = 1; from < to; cont++) {
				Range range = new Range(from, Math.min(from+step,to));
				Filter<RecordMeteo> filter = new FilterFreq(range);
				ArrayList<RecordMeteo> array = fs.applyFilter(filter, sample);
				ret.put(freq + "_" + cont, array);
				from += step;
			}
		}
		
		return ret;
	}
	
	/**
	 * Carica la lista delle città dal file di configurazione city.list.min.json
	 * @see msn.weather_app.util.parser.ParserCity
	 * @see msn.weather_app.util.parser.ParserCity#load()
	 */
	public static void loadCityList() {
		ParserCity parser =new ParserCity();
		cityList = parser.load();
	}
	/**
	 * Carica la lista delle rilevazioni meteo dal file meteo.data.json
	 * @see msn.weather_app.util.parser.ParserMeteo
	 * @see msn.weather_app.util.parser.ParserMeteo#load()
	 */
	public static void loadMeteoData() {
		ParserMeteo parser= new ParserMeteo();
		meteoData = parser.load();
	}
	/**
	 *aggiunge i dati contenuti nell' ArrayList data a meteoData
	 *
	 * @param data
	 */
	public static void updateMeteoData(ArrayList<RecordMeteo> data) {
			meteoData.addAll(data);
	}
	/**
	 * Salva i dati contenuti nell' ArrayList meteoData nel file meteo.data.json in formato JSON
	 * @throws IOException 
	 * 
	 */
	public static void save() {
		try {
			BufferedWriter out =new BufferedWriter (new FileWriter("config/meteo.data.json")) ;
			JSONArray newarray = new JSONArray();
			
			for(RecordMeteo element :meteoData) {
				newarray.put(element.toJSONObject()); 
			}
			out.write(newarray.toString());
			out.close();
		}catch(IOException e) {
			System.out.println("I/O error\n" + e);
		}
	}
	
}
