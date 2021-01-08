package msn.weather_app.util.filter;
import msn.weather_app.model.Range;
import msn.weather_app.model.RecordMeteo;

/**
 * Implementazione della classe FilterTempMin
 *  
 * Classe che implementa un filtro applicabile a ArrayList di RecordMeteo:
 * seleziona tutti i RecordMeteo che corrispondono al range di temperatura minima
 * inserito come parametro di ricerca
 *
 *
 * @author Alessandro Minutillo 
 * @author Vito Scaraggi
 * @author Davide Nunin
 */

public class FilterTempMin extends Filter<RecordMeteo>{
	/**
	 * Costruttore
	 * @param param indica l'oggetto passato
	 * @see msn.weather_app.util.filter.FilterTempMin#buildLogic(Object)
	 */
	public FilterTempMin(Object param) {
		super();
		buildLogic(param);
	}
	
	/**
	 * Costruisce l'oggetto di tipo Predicate
	 * @param param indica l'oggetto passato
	 * @see msn.weather_app.model.Range#contains(double)
	 * @see msn.weather_app.model.RecordMeteo#getTemp()
	 * @see msn.weather_app.model.Temp#getMin()
	 */
	
	private void buildLogic(Object param) {
		try {
			Range range = (Range) param;
			logic = rm -> range.contains(rm.getTemp().getMin());
		}
		catch(ClassCastException e) {
			System.out.println("TempMin filter error: invalide cast\n" + e);
		}
		catch(LinkageError e) {
			System.out.println("TempMin filter error: linkage error\n" + e);
		}
	}
}
