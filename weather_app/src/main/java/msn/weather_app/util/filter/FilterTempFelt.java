package msn.weather_app.util.filter;
/**
 * Implementazione della classe FilterSubstrRM
 *  
 * Classe che implementa un filtro applicabile a ArrayList<RecordMeteo>:
 * seleziona tutti i RecordMeteo che corrispondono al range di temperatura percepita
 * inserito come parametro di ricerca
 *
 * @author Alessandro Minutillo 
 * @author Vito Scaraggi
 * @author Davide Nunin
 */

import msn.weather_app.model.Range;
import msn.weather_app.model.RecordMeteo;

public class FilterTempFelt extends Filter<RecordMeteo>{
	/**
	 * Costruttore
	 * @param indica l'oggetto passato
	 * @see msn.weather_app.util.filter.FilterTempFelt#buildLogic(Object)
	 */
	public FilterTempFelt(Object param) {
		super();
		buildLogic(param);
	}
	
	/**
	 * Costruisce l'oggetto di tipo Predicate
	 * @param indica l'oggetto passato
	 * @see msn.weather_app.model.Range#contains(double)
	 * @see msn.weather_app.model.RecordMeteo#getTemp()
	 * @see msn.weather_app.model.Temp#getFelt()
	 */
	
	private void buildLogic(Object param) {
		try {
			Range range = (Range) param;
			logic = rm -> range.contains(rm.getTemp().getFelt());
		}
		catch(ClassCastException e) {
			System.out.println("TempFelt filter error: invalide cast\n" + e);
		}
		catch(LinkageError e) {
			System.out.println("TempFelt filter error: linkage error\n" + e);
		}
	}
}
