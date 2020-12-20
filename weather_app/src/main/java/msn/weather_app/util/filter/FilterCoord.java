package msn.weather_app.util.filter;
/**
 * Implementazione della classe FilterCoord
 *  
 * Classe che implementa un filtro applicabile a ArrayList<RecordMeteo>:
 * seleziona tutti i RecordMeteo che corrispondono ai valori di latitudine e longitudine
 * inseriti come parametri di ricerca
 *  
 * @author Alessandro Minutillo 
 * @author Vito Scaraggi
 * @author Davide Nunin
 */

import msn.weather_app.model.Coord;

import msn.weather_app.model.RecordMeteo;

public class FilterCoord extends Filter<RecordMeteo>{
	/**
	 * Costruttore
	 * @param rappresenta l'oggetto passato 
	 * @see msn.weather_app.util.filter.FilterCoord#buildLogic(Object)
	 */
	public FilterCoord(Object param) {
		super();
		buildLogic(param);
	}
	
	/**
	 * Costruisce l'oggetto di tipo Predicate
	 * @param rappresenta l'oggetto passato
	 * @see msn.weather_app.model.RecordMeteo#getCity()
	 * @see msn.weather_app.model.City#getCoords()
	 * @see msn.weather_app.model.Coord#getLat()
	 * @see msn.weather_app.model.Coord#getLon()
	 */
	
	private void buildLogic(Object param) {
		Coord coord = (Coord) param;
		logic = rm -> rm.getCity().getCoords().getLat() == coord.getLat() &&
					rm.getCity().getCoords().getLon() == coord.getLon();
	}
}
