package msn.weather_app.util.filter;
/**
 * Implementazione della classe FilterSubstrC
 *  
 * Classe che implementa un filtro applicabile a ArrayList<City>:
 * seleziona tutti i City in cui il nome della città contiene la
 * sottrostringa come parametro di ricerca
 *  
 * @author Alessandro Minutillo 
 * @author Vito Scaraggi
 * @author Davide Nunin
 */
import msn.weather_app.model.City;

public class FilterSubstrC extends Filter<City>{
	
	/**
	 * Costruttore
	 * @param indica l'oggetto passato
	 * @see msn.weather_app.util.filter.FilterSubstrC#buildLogic(Object)
	 */
	public FilterSubstrC(Object param) {
		super();
		buildLogic(param);
	}
	
	/**
	 * Costruisce l'oggetto di tipo Predicate
	 * @param indica l'oggetto passato
	 * @see msn.weather_app.model.City#getName()
	 */
	
	private void buildLogic(Object param) {
		try {
			String string = (String) param;
			logic = c -> c.getName().contains(string);
		}
		catch(ClassCastException e) {
			System.out.println("SubstrC filter error: invalide cast\n" + e);
			logic = rm -> true;
		}
		catch(LinkageError e) {
			System.out.println("SubstrC filter error: linkage error\n" + e);
			logic = rm -> true;
		}
	}
}
