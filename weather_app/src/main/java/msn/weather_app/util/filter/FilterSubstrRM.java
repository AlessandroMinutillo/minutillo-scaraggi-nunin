package msn.weather_app.util.filter;
import msn.weather_app.model.RecordMeteo;

public class FilterSubstrRM extends Filter<RecordMeteo>{
	
	public FilterSubstrRM(Object param) {
		super();
		buildLogic(param);
	}
	
	private void buildLogic(Object param) {
		String string = (String) param;
		String arr[] = string.split(";");
		
		for(String s: arr)
			logic = logic.or(rm -> rm.getCity().getName().contains(s));
	}
}
