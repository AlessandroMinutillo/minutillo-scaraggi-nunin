package msn.weather_app.exception;
/**
 * Implementazione della classe CoordException
 * 
 * Eccezione che si verifica quando le coordinate non sono valide
 * 
 * @author Alessandro Minutillo 
 * @author Vito Scaraggi
 * @author Davide Nunin
 */

public class CoordException extends Exception{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Costruttore
	 */
	
	public CoordException () {
		super();
	}
	
	/**
	 * Costruttore
	 * @param msg rappresenta il messaggio di errore
	 */
	
	public CoordException (String msg) {
		super(msg);
	}
}
