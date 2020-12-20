package msn.weather_app.model;
/**
 * Implementazione della classe Range
 * 
 * Rappresenta un determinato range su cui sarà successivamente
 * possibile effettuare delle statistiche
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
	double from, to;
	/**
	 * Costruttore
	 * @param from indica il primo estremo dell'intervallo
	 * @param to indica il secondo estremo dell'intervallo
	 */
	public Range(double from, double to) {
		this.from = from;
		this.to = to;
	}
	/**
	 * @param d indica l'elemento (double) di cui si vuole studiare
	 * l'eventuale appartenenza all'intervallo
	 * @return true se l'elemento d appartiene al range dato
	 * @return false se l'elemento non appartiene al range dato
	 */
	public boolean contains(double d) {
		return d >= from && d<=to;
	}
}
