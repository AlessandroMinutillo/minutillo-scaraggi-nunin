package msn.weather_app.util.filter;

import msn.weather_app.model.Range;
import msn.weather_app.model.RecordMeteo;

public class FilterPress extends Filter<RecordMeteo>{
	public FilterPress(Object param) {
		super();
		buildLogic(param);
	}
	
	private void buildLogic(Object param) {
		Range range = (Range) param;
		logic = rm -> range.contains(rm.getPress());
	}
}
