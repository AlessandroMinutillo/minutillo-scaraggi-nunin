package msn.weather_app.util.filter;

/**
 * Implementazione della classe FilterPeriod
 *  
 * Classe che implementa un filtro applicabile a ArrayList<RecordMeteo>:
 * seleziona tutti i RecordMeteo che corrispondono al periodo inserito come parametro di ricerca
 *  
 * @author Alessandro Minutillo 
 * @author Vito Scaraggi
 * @author Davide Nunin
 */
import msn.weather_app.exception.FilterException;
import msn.weather_app.model.RecordMeteo;


public class FilterPeriod extends Filter<RecordMeteo>{
	
	/**
	 * Enum che associa a ciascuna unità di tempo il corrispondente valore in secondi
	 */
	
	private enum EpochValue {
		HOUR(3600.d),
		DAY(24.d * HOUR.value),
		WEEK(7.d * DAY.value),
		MONTH(30.d * DAY.value);
		
		private double value;
		private EpochValue(double value) {
			this.value = value;
		}
	}
	/**
	 * Costruttore
	 * @param rappresenta l'oggetto passato
	 * @see msn.weather_app.util.filter.FilterPeriod#buildLogic(Object)
	 */
	public FilterPeriod(Object param){
		super();
		buildLogic(param);
	}
	
	/**
	 * costruisce l'oggetto di tipo Predicate
	 * @param rappresenta l'oggetto passato
	 * @see msn.weather_app.model.RecordMeteo#getEpoch()
	 * @see msn.weather_app.exception.FilterException#FilterException()
	 */
	
	private void buildLogic(Object param) {
		String string = (String) param;
		long now = System.currentTimeMillis()/1000; // epoch now
		switch(string) {
			case "hour":{
				logic = rm -> rm.getEpoch() >= now - EpochValue.HOUR.value;
			}
			break;
			case "day":{
				logic = rm -> rm.getEpoch() >= now - EpochValue.DAY.value;
			}
			break;
			case "week":{
				logic = rm -> rm.getEpoch() >= now - EpochValue.WEEK.value;
			}
			break;
			case "month":{
				logic = rm -> rm.getEpoch() >= now - EpochValue.MONTH.value;
			}
			break;
			default:{
				try {
					char c = string.charAt(0);
					Long q = Long.parseLong(string.substring(1));
					switch(c) {
						case 'h': {
							logic = rm-> rm.getEpoch() >= now - q * EpochValue.HOUR.value;
						}
						break;
						case 'd': {
							logic = rm-> rm.getEpoch() >= now - q * EpochValue.DAY.value;
						}
						break;
						case 'w': {
							logic = rm-> rm.getEpoch() >= now - q * EpochValue.WEEK.value;
						}
						break;
						case 'm': {
							logic = rm-> rm.getEpoch() >= now - q * EpochValue.MONTH.value;
						}
						break;
						default:{
							throw new FilterException();
						}
					}
				}
				catch(IndexOutOfBoundsException | NumberFormatException | FilterException e) {
					System.out.println("Period filter error: invalid period\n" + e);
					logic = rm -> true;
				}
			}
		}
	}
	
}
