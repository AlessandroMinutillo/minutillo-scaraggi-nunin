package msn.weather_app.model;

import org.json.JSONObject;

/**
	 * Implementazione della classe City
	 * 
	 * Rappresenta una città
	 *  
	 * @author Alessandro Minutillo
	 * @author Vito Scaraggi
	 * @author Davide Nunin
	 */
public class City {
	/**
	 * Rappresenta il nome della città
	 */
	private String name;
	/**
	 * Coordinate della città
	 * @see msn.weather_app.model.Coord
	 */
	private Coord coord;
	/**
	 * Costruttore
	 * @param name rappresenta il nome della città
	 * @param coord rappresenta le coordinate geografiche della città
	 */
	public City(String name, Coord coord) {
		this.name = name;
		this.coord = coord;
	}
	/**
	 * @return il nome della città
	 */
	public String getName() {
		return name;
	}
	/** 
	 * Imposta il nome della città
	 * @param name indica il nome da impostare
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return le coordinate della città
	 * @see msn.weather_app.model.Coord
	 */
	public Coord getCoord() {
		return coord;
	}
	/**
	 * Imposta le coordinate della città
	 * @param coord indica le coordinate da impostare
	 * @see msn.weather_app.model.Coord
	 */
	public void setCoord(Coord coord) {
		this.coord = coord;
	}
	
	/**
	 * Converte l'oggetto in JSONObject
	 * @return ret JSONObject
	 */
	
	public JSONObject toJSONObject() {
		JSONObject ret =new JSONObject();
		ret.put("name", this.name);
		ret.put("coord", this.coord.toJSONObject());
		return ret;
	}
}
