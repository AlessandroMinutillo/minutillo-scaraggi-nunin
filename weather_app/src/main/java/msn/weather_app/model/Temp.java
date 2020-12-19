package msn.weather_app.model;
/**
 * Contiene le temperature previste per una città
 * 
 */
public class Temp {
	private double cur;
	private double min;
	private double max;
	private double felt;
	
	public Temp(double cur, double min, double max, double felt) {
		this.cur = cur;
		this.min = min;
		this.max = max;
		this.felt = felt;
	}
	/**
	 * restituisce la temperatura corrente 
	 * @return cur
	 */
	public double getCur() {
		return cur;
	}
	/**
	 * imposta la temperatura corrente
	 * @param cur
	 */
	public void setCur(double cur) {
		this.cur = cur;
	}
	/**
	 * restituisce la temperatura minima del giorno 
	 * @return min
	 */
	public double getMin() {
		return min;
	}
	/**
	 * imposta la temperatura minima
	 * @param min
	 */
	public void setMin(double min) {
		this.min = min;
	}
	/**
	 * restituisce la temperatura massima del giorno
	 * @return
	 */
	public double getMax() {
		return max;
	}
	/**
	 * imposta la temperatura massima
	 * @param max
	 */
	public void setMax(double max) {
		this.max = max;
	}
	/**
	 * restituisce la temperatura percepita
	 * @return
	 */
	public double getFelt() {
		return felt;
	}
	/**
	 * imposta la temperatura percepita
	 * @param felt
	 */
	public void setFelt(double felt) {
		this.felt = felt;
	}
	
	
}
