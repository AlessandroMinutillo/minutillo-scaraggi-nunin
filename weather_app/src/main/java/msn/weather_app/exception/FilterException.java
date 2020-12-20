package msn.weather_app.exception;

/**
 * Eccezione che si verifica quando si inserisce un filtro non valido
 * 
 */


public class FilterException extends Exception{
	
	public FilterException () {
		super();
	}
	
	public FilterException (String msg) {
		super(msg);
	}
}