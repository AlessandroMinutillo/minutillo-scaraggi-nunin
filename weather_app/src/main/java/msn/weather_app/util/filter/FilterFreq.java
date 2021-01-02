package msn.weather_app.util.filter;

import msn.weather_app.model.Range;
import msn.weather_app.model.RecordMeteo;

/**
 * Implementazione della classe FilterSubstrRM
 *  
 * Classe che implementa un filtro applicabile a ArrayList<RecordMeteo>:
 * seleziona tutti i RecordMeteo che corrispondono al range di epoch
 * inserito come parametro di ricerca
 *
 * @author Alessandro Minutillo 
 * @author Vito Scaraggi
 * @author Davide Nunin
 */

public class FilterFreq extends Filter <RecordMeteo> {
	/**
	 * Costruttore
	 * @param indica l'oggetto passato
	 * @see msn.weather_app.util.filter.FilterFreq#buildLogic(Object)
	 */
	public FilterFreq(Object param) {
		super();
		buildLogic(param);
	}
	
	/**
	 * Costruisce l'oggetto di tipo Predicate
	 * @param indica l'oggetto passato
	 * @see msn.weather_app.model.Range#contains(long)
	 * @see msn.weather_app.model.RecordMeteo#getEpoch()
	 */
	
	private void buildLogic(Object param) {
		try {
			Range range = (Range) param;
			logic = rm -> range.contains(rm.getEpoch());
		}
		catch(ClassCastException e) {
			System.out.println("Freq filter error: invalide cast\n" + e);
		}
		catch(LinkageError e) {
			System.out.println("Freq filter error: linkage error\n" + e);
		}
	}
}
