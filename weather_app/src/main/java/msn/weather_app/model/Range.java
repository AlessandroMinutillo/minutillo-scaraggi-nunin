package msn.weather_app.model;
/**
 * Implementazione della classe Range
 * 
 * Rappresenta un intervallo numerico
 * 
 * @author Alessandro Minutillo 
 * @author Vito Scaraggi
 * @author Davide Nunin
 */
public class Range{
	/**
	 * from e to rappresentano rispettivamente il primo e il secondo
	 * estremo di un determinato intervallo (range)
	 */
	private Number from, to;
	/**
	 * Costruttore
	 * @param from indica il primo estremo dell'intervallo
	 * @param to indica il secondo estremo dell'intervallo
	 */
	public Range(Number from, Number to) {
		this.from = from;
		this.to = to;
	}
	/**
	 * @param d indica il numero (double) di cui si vuole verificare
	 * l'appartenenza all'intervallo
	 * @return true se il numero d appartiene al range dato
	 * @return false se il numero d non appartiene al range dato
	 */
	public boolean contains(double d) {
		return d >= from.doubleValue() && d<=to.doubleValue();
	}
	/**
	 * @param l indica il numero (long) di cui si vuole verificare
	 * l'appartenenza all'intervallo
	 * @return true se il numero l appartiene al range dato
	 * @return false se il numero l non appartiene al range dato
	 */
	public boolean contains(long l) {
		return l >= from.longValue() && l<=to.longValue();
	}
}
