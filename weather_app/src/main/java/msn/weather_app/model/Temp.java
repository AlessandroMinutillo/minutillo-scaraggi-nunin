package msn.weather_app.model;
/**
 * Implementazione della classe Temp
 * 
 * Rappresenta le possibili temperature registrabili in una città suddivise in:
 * Temperatura corrente, temperatura minima, temperatura massima e temperatura percepita
 * 
 * @author Alessandro Minutillo 
 * @author Vito Scaraggi
 * @author Davide Nunin
 */

import org.json.JSONObject;

public class Temp {
	
	/**
	 * Rappresenta la temperatura corrente
	 */
	private double cur;
	
	/**
	 * Rappresenta la temperatura minima
	 */
	private double min;
	
	/**
	 * Rappresenta la temperatura massima
	 */
	private double max;
	
	/**
	 * Rappresenta la temperatura percepita
	 */
	private double felt;
	
	/**
	 * Costruttore
	 * @param cur indica la temperatura corrente
	 * @param min indica la temperatura minima
	 * @param max indica la temperatura massima
	 * @param felt indica la temperatura percepita
	 */
	public Temp(double cur, double min, double max, double felt) {
		this.cur = cur;
		this.min = min;
		this.max = max;
		this.felt = felt;
	}
	/**
	 * @return cur, la temperatura corrente
	 */
	public double getCur() {
		return cur;
	}
	
	/**
	 * Imposta la temperatura corrente
	 * @param cur
	 */
	public void setCur(double cur) {
		this.cur = cur;
	}
	
	/**
	 * @return min, la temperatura minima
	 */
	public double getMin() {
		return min;
	}
	
	/**
	 * Imposta la temperatura minima
	 * @param min
	 */
	public void setMin(double min) {
		this.min = min;
	}
	
	/**
	 * @return max, la temperatura massima
	 */
	public double getMax() {
		return max;
	}
	
	/**
	 * Imposta la temperatura massima
	 * @param max
	 */
	public void setMax(double max) {
		this.max = max;
	}
	
	/**
	 * @return felt, la temperatura percepita
	 */
	public double getFelt() {
		return felt;
	}
	
	/**
	 * Imposta la temperatura percepita
	 * @param felt
	 */
	public void setFelt(double felt) {
		this.felt = felt;
	}
	public JSONObject toJSONObject() {
		JSONObject ret =new JSONObject();
		ret.put("cur", this.cur);
		ret.put("min", this.min);
		ret.put("max", this.max);
		ret.put("felt", this.felt);
		return ret;
	}
}
