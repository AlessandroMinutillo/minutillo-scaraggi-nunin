package msn.weather_app.exception;

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
