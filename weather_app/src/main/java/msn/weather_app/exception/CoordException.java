package msn.weather_app.exception;
/**
 * Eccezione che si verifica quando si inseriscono coordinate non valide
 * @see Coord
 * 
 *
 */
public class CoordException extends Exception{
	public CoordException (String msg) {
		super(msg);
	}
}
