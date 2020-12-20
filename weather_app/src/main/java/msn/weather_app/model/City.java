package msn.weather_app.model;
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
	private Coord coords;
	/**
	 * Costruttore
	 * @param name rappresenta il nome della città
	 * @param coords rappresenta le coordinate geografiche della città
	 */
	public City(String name, Coord coords) {
		this.name = name;
		this.coords = coords;
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
	public Coord getCoords() {
		return coords;
	}
	/**
	 * Imposta le coordinate della città
	 * @param coords indica le coordinate da impostare
	 * @see msn.weather_app.model.Coord
	 */
	public void setCoords(Coord coords) {
		this.coords = coords;
	}
	
}
