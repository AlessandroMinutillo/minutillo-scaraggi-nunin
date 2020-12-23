package msn.weather_app.util.stats;

import java.util.HashMap;

/** Classe che implementa la statistica
 * 
 * @author Alessandro Minutillo 
 * @author Vito Scaraggi
 * @author Davide Nunin
 * 
 * 
 */

public class Stats {
	
	private HashMap <String,Double> stat;
	
	/**
	 * Costruttore vuoto
	 */
	
	public Stats(){
		stat = new HashMap<String,Double>();
	}
	
	/**
	 * @return stat
	 */
	
	public HashMap <String,Double> getStat() {
		return stat;
	}
	
	/**
	 * Metodo che aggiunge un campo alla statistica
	 * 
	 * @param key
	 * @param value
	 */
	
	public void addField(String key, Double value) {
		stat.put(key,value);
	}
	
}
