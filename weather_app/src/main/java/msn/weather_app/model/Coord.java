package msn.weather_app.model;
/**
 * rappresenta una coppia di coordinate (double) geografiche
 * 
 *
 */
public class Coord {
	/**
	 * latitudine
	 */
	private double lat;
	/**
	 * longitudine
	 */
	private double lon;
	
	public Coord (double lat, double lon) {
		this.lat = lat;
		this.lon = lon;
	}
	/**
	 * 
	 * @return lat 
	 */
	public double getLat() {
		return lat;
	}
	/**
	 * imposta la latitudine
	 * @param lat
	 */
	public void setLat(double lat) {
		this.lat = lat;
	}
	/**
	 * 
	 * @return lon
	 */
	public double getLon() {
		return lon;
	}
	/**
	 * imposta la longitudine
	 * @param lon
	 */
	public void setLon(double lon) {
		this.lon = lon;
	}
	/**
	 * controlla se le coordinate sono valide (-90<=lat<=90,-180<=lon<=180)
	 * @return true se le coordinate sono valide
	 * @return false se le coordinate non sono valide
	 */
	public boolean validate() {
		return (this.lat>=-90 && this.lat<=90) 
							  && 
			   (this.lon>=-180 && this.lon<=180);
	}
}
