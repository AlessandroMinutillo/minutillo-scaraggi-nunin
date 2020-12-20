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
	public CoordException (String msg) {
		super(msg);
	}
}
