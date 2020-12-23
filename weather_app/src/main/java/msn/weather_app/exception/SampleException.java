package msn.weather_app.exception;

public class SampleException extends Exception{
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
