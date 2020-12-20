package msn.weather_app.util.filter;

import msn.weather_app.model.City;

/* Classe che implementa un filtro applicabile a ArrayList<City>:
 * seleziona tutti i City in cui il nome della città contiene la
 * sottrostringa come parametro di ricerca
 */

public class FilterSubstrC extends Filter<City>{
	
	public FilterSubstrC(Object param) {
		super();
		buildLogic(param);
	}
	
	/* costruisce l'oggetto di tipo Predicate
	 * @param param
	 */
	
	private void buildLogic(Object param) {
		String string = (String) param;
		logic = c -> c.getName().contains(string);
	}
}
