package msn.weather_app.model;
/**
 * Implementazione della classe RecordMeteo
 * 
 * Rappresenta il Record in cui andranno memorizzate informazioni riguardanti
 * il meteo di una città
 * 
 * @author Alessandro Minutillo 
 * @author Vito Scaraggi
 * @author Davide Nunin
 */

public class RecordMeteo {
	/**
	 * Oggetto che rappresenta la temperatura
	 * @see msn.weather_app.model.Temp
	 */
	private Temp temp;
	
	/**
	 * Rappresenta la pressione
	 */
	private double press;
	
	/**
	 * Rappresenta i secondi passati dal 01/01/1970 fino al momento
	 * di una eventuale chiamata da parte dell'utente
	 */
	private long epoch;
	
	/**
	 * Oggetto che rappresenta una città
	 * @see msn.weather_app.model.City
	 */
	private City city;
	
	/**
	 * Costruttore 
	 * @param temp indica la temperatura
	 * @param press indica la pressione
	 * @param epoch indica i secondi passati dal 01/01/1970
	 * @param city indica la città
	 */
	public RecordMeteo(Temp temp, double press, long epoch, City city) {
		this.temp = temp;
		this.press = press;
		this.epoch = epoch;
		this.city = city;
	}
	
	public RecordMeteo() {
	}
	
	/**
	 * @return temp
	 */
	public Temp getTemp() {
		return temp;
	}
	
	/**
	 * Imposta il tempo
	 * @param temp
	 */
	public void setTemp(Temp temp) {
		this.temp = temp;
	}
	
	/**
	 * @return press
	 */
	public double getPress() {
		return press;
	}
	
	/**
	 * Imposta la pressione
	 * @param press
	 */
	public void setPress(double press) {
		this.press = press;
	}
	
	/**
	 * @return epoch
	 */
	public long getEpoch() {
		return epoch;
	}
	
	/**
	 * Imposta epoch
	 * @param epoch
	 */
	public void setEpoch(long epoch) {
		this.epoch = epoch;
	}
	
	/**
	 * @return city
	 */
	public City getCity() {
		return city;
	}
	
	/**
	 * Imposta la città
	 * @param city
	 */
	public void setCity(City city) {
		this.city = city;
	}
}
