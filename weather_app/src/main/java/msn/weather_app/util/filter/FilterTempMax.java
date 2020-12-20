package msn.weather_app.util.filter;

import msn.weather_app.model.Range;
import msn.weather_app.model.RecordMeteo;

/* Classe che implementa un filtro applicabile a ArrayList<RecordMeteo>:
 * seleziona tutti i RecordMeteo che corrispondono al range di temperatura massima
 * inserito come parametro di ricerca
 */

public class FilterTempMax extends Filter<RecordMeteo>{
	
	public FilterTempMax(Object param) {
		super();
		buildLogic(param);
	}
	
	/* costruisce l'oggetto di tipo Predicate
	 * @param param
	 */
	
	private void buildLogic(Object param) {
		Range range = (Range) param;
		logic = rm -> range.contains(rm.getTemp().getMax());
	}
	
}
