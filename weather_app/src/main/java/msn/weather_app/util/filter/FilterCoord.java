package msn.weather_app.util.filter;

import msn.weather_app.model.Coord;
import msn.weather_app.model.RecordMeteo;

/* Classe che implementa un filtro applicabile a ArrayList<RecordMeteo>:
 * seleziona tutti i RecordMeteo che corrispondono ai valori di latitudine e longitudine
 * inseriti come parametri di ricerca
 */

public class FilterCoord extends Filter<RecordMeteo>{
	
	public FilterCoord(Object param) {
		super();
		buildLogic(param);
	}
	
	/* costruisce l'oggetto di tipo Predicate
	 * @param param
	 */
	
	private void buildLogic(Object param) {
		Coord coord = (Coord) param;
		logic = rm -> rm.getCity().getCoords().getLat() == coord.getLat() &&
					rm.getCity().getCoords().getLon() == coord.getLon();
	}
}
