package msn.weather_app.util.filter;
import msn.weather_app.model.RecordMeteo;


/* Classe che implementa un filtro applicabile a ArrayList<RecordMeteo>:
 * seleziona tutti i RecordMeteo in cui il nome della città contiene la
 * sottrostringa o le sottostringhe inserite come parametri di ricerca
 */

public class FilterSubstrRM extends Filter<RecordMeteo>{
	
	public FilterSubstrRM(Object param) {
		super();
		buildLogic(param);
	}
	
	/* costruisce l'oggetto di tipo Predicate
	 * @param param
	 */
	
	private void buildLogic(Object param) {
		String string = (String) param;
		String arr[] = string.split(";");
		
		logic = rm -> false;
		for(String s: arr)
			logic = logic.or(rm -> rm.getCity().getName().contains(s));
	}
}
