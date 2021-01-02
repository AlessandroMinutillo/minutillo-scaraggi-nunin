package msn.weather_app.model;

/**
 * Implementazione della classe TimeData
 * Contiene alcune informazioni relative al tempo
 * 
 * @author Alessandro Minutillo 
 * @author Vito Scaraggi
 * @author Davide Nunin
 */

public class TimeData {
	
	/**
	 * NOW rappresenta il tempo in formato UNIX dell'ultima chiamata API POST
	 * effettuata dall'utente
	 * 
	 * LOW_LIMIT è una costante che indica un valore di tempo in formato UNIX
	 * prima del quale non esistono dati meteo salvati
	 */
	
	public static long NOW;
	public static final long LOW_LIMIT = 1609286400;
}
