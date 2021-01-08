package msn.weather_app.exception;

/**
 * Implementazione della classe SampleException
 * 
 * Eccezione che si verifica quando ci sono errori relativi ai campioni meteo
 * 
 * @author Alessandro Minutillo 
 * @author Vito Scaraggi
 * @author Davide Nunin
 */


public class SampleException extends Exception{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Costruttore
	 */
	public SampleException () {
		super();
	}
	/**
	 * Costruttore
	 * @param msg rappresenta il messaggio di errore
	 */
	public SampleException (String msg) {
		super(msg);
	}
}
