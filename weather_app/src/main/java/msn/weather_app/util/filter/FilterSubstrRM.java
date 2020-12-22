package msn.weather_app.util.filter;

/**
 * Implementazione della classe FilterSubstrRM

 *  
 * Classe che implementa un filtro applicabile a ArrayList<RecordMeteo>:
 * seleziona tutti i RecordMeteo in cui il nome della città contiene la
 * sottostringa o le sottostringhe inserite come parametri di ricerca
 *
 * @author Alessandro Minutillo 
 * @author Vito Scaraggi
 * @author Davide Nunin
 */

import msn.weather_app.model.RecordMeteo;

public class FilterSubstrRM extends Filter<RecordMeteo>{
	
	/**
	 * Costruttore
	 * @param indica l'oggetto passato
	 * @see msn.weather_app.util.filter.FilterSubstrRM#buildLogic(Object)
	 */
	public FilterSubstrRM(Object param) {
		super();
		buildLogic(param);
	}
	
	/**
	 * Costruisce l'oggetto di tipo Predicate
	 * @param indica l'oggetto passato
	 * @see msn.weather_app.model.RecordMeteo#getCity()
	 * @see msn.weather_app.model.City#getName()
	 */
	
	private void buildLogic(Object param) {
		try {
			String string = (String) param;
			String arr[] = string.split(";");
			
			logic = rm -> false;
			
			for(String s: arr) {
				Filter <RecordMeteo> filter = new Filter<RecordMeteo>();
				filter.logic = rm -> rm.getCity().getName().contains(s);
				this.orCat(filter);
			}
		}
		catch(ClassCastException e) {
			System.out.println("Coord filter error: invalid cast\n" + e);
			logic = rm -> true;
		}
		catch(LinkageError e) {
			System.out.println("Coord filter error: linkage error\n" + e);
			logic = rm -> true;
		}
	}
}
