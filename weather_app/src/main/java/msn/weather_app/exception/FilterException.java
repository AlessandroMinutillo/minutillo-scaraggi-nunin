package msn.weather_app.exception;
/**
 * Implementazione della classe FilterException
 *  
 * Eccezione che si verifica quando si inserisce un filtro non valido
 *  
 * @author Alessandro Minutillo 
 * @author Vito Scaraggi
 * @author Davide Nunin
 */

public class FilterException extends Exception{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Costruttore
	 */
	public FilterException () {
		super();
	}
	/**
	 * Costruttore
	 * @param msg rappresenta il messaggio di errore
	 */
	public FilterException (String msg) {
		super(msg);
	}
}