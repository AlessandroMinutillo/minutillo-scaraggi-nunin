package msn.weather_app.model;
	/**
	 * la classe che rappresenta una città 
	 * 
	 */
public class City {
	/**
	 * il nome della città
	 */
	private String name;
	/**
	 * le coordinate della città
	 * @see Coord
	 */
	private Coord coords;
	
	public City(String name, Coord coords) {
		this.name = name;
		this.coords = coords;
	}
	/**
	 * 
	 * @return il nome della città
	 */
	public String getName() {
		return name;
	}
	/** 
	 * imposta il nome della città
	 * @param name il nome da impostare
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 
	 * @return le coordinate della città
	 * @see Coord
	 */
	public Coord getCoords() {
		return coords;
	}
	/**
	 * imposta le coordinate della città
	 * @param coords coordinate da impostare
	 * @see Coord
	 */
	public void setCoords(Coord coords) {
		this.coords = coords;
	}
	
}
