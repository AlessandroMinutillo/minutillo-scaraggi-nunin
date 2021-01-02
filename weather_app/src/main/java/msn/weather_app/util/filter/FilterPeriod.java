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
import msn.weather_app.model.TimeData;
import msn.weather_app.model.EpochValue;
import msn.weather_app.model.RecordMeteo;


public class FilterPeriod extends Filter<RecordMeteo>{
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
		switch(string) {
			case "hour":{
				logic = rm -> rm.getEpoch() >= TimeData.NOW - EpochValue.HOUR.value;
			}
			break;
			case "day":{
				logic = rm -> rm.getEpoch() >= TimeData.NOW - EpochValue.DAY.value;
			}
			break;
			case "week":{
				logic = rm -> rm.getEpoch() >= TimeData.NOW - EpochValue.WEEK.value;
			}
			break;
			case "month":{
				logic = rm -> rm.getEpoch() >= TimeData.NOW - EpochValue.MONTH.value;
			}
			break;
			default:{
				try {
					char c = string.charAt(0);
					Long q = Long.parseLong(string.substring(1));
					switch(c) {
						case 'h': {
							logic = rm-> rm.getEpoch() >= TimeData.NOW - q * EpochValue.HOUR.value;
						}
						break;
						case 'd': {
							logic = rm-> rm.getEpoch() >= TimeData.NOW - q * EpochValue.DAY.value;
						}
						break;
						case 'w': {
							logic = rm-> rm.getEpoch() >= TimeData.NOW - q * EpochValue.WEEK.value;
						}
						break;
						case 'm': {
							logic = rm-> rm.getEpoch() >= TimeData.NOW - q * EpochValue.MONTH.value;
						}
						break;
						default:{
							throw new FilterException();
						}
					}
				}
				catch(EnumConstantNotPresentException | IndexOutOfBoundsException | NumberFormatException | FilterException e) {
					System.out.println("Period filter error: invalid period\n" + e);
				}
				catch(LinkageError e) {
					System.out.println("Period filter error: linkage error\n" + e);
				}
			}
		}
	}
	
}
