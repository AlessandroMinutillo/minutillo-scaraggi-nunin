package msn.weather_app.util.filter;
import msn.weather_app.model.Range;
import msn.weather_app.model.RecordMeteo;

/**
 * Implementazione della classe FilterTempMax
 *  
 * Classe che implementa un filtro applicabile a ArrayList di RecordMeteo:
 * seleziona tutti i RecordMeteo che corrispondono al range di temperatura massima
 * inserito come parametro di ricerca
 *
 * @author Alessandro Minutillo 
 * @author Vito Scaraggi
 * @author Davide Nunin
 */

public class FilterTempMax extends Filter<RecordMeteo>{
	/**
	 * Costruttore
	 * @param param indica l'oggetto passato
	 * @see msn.weather_app.util.filter.FilterTempMax#buildLogic(Object)
	 */
	public FilterTempMax(Object param) {
		super();
		buildLogic(param);
	}
	
	/**
	 * Costruisce l'oggetto di tipo Predicate
	 * @param param indica l'oggetto passato
	 * @see msn.weather_app.model.Range#contains(double)
	 * @see msn.weather_app.model.RecordMeteo#getTemp()
	 * @see msn.weather_app.model.Temp#getMax()
	 */
	
	private void buildLogic(Object param) {
		try {
			Range range = (Range) param;
			logic = rm -> range.contains(rm.getTemp().getMax());
		}
		catch(ClassCastException e) {
			System.out.println("TempMax filter error: invalide cast\n" + e);
		}
		catch(LinkageError e) {
			System.out.println("TempMax filter error: linkage error\n" + e);
		}
	}
	
}
