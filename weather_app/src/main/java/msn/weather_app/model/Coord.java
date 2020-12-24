package msn.weather_app.model;
/**
 * Implementazione della classe Coord
 * 
 * Rappresenta una coppia di coordinate (double) geografiche
 * 
 * @author Alessandro Minutillo 
 * @author Vito Scaraggi
 * @author Davide Nunin
 */


import org.json.JSONObject;

import msn.weather_app.exception.CoordException;

public class Coord {
	/**
	 * Rappresenta la latitudine
	 */
	private double lat;
	/**
	 * Rappresenta la longitudine
	 */
	private double lon;
	/**
	 * Costruttore
	 * @param lat indica la latitudine
	 * @param lon indica la longitudine
	 */
	public Coord (double lat, double lon) {
		this.lat = lat;
		this.lon = lon;
	}
	/**
	 * @return lat 
	 */
	public double getLat() {
		return lat;
	}
	/**
	 * Imposta la latitudine
	 * @param lat indica la latitudine
	 */
	public void setLat(double lat) {
		this.lat = lat;
	}
	/**
	 * @return lon
	 */
	public double getLon() {
		return lon;
	}
	/**
	 * Imposta la longitudine
	 * @param lon indica la longitudine
	 */
	public void setLon(double lon) {
		this.lon = lon;
	}
	/**
	 * Controlla se le coordinate sono valide (-90<=lat<=90,-180<=lon<=180)
	 * @return true se le coordinate sono valide
	 * @return false se le coordinate non sono valide
	 * @throws CoordException 
	 */
	public void validate() throws CoordException {
		if ((this.lat<-90 || this.lat>90)||(this.lon<-180 || this.lon>180)) {
			throw new CoordException ("Wrong Coordinates");
		}
	}
	/**
	 * Overriding del metodo toString
	 * @return una stringa (String) che rappresenta l'oggetto Coord 
	 */
	public String toString () {
		return lat+" "+lon;
	}
	public JSONObject toJSONObject() {
		JSONObject ret =new JSONObject();
		ret.put("lat", this.lat);
		ret.put("lon", this.lon);
		return ret;
	}
}
