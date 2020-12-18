package msn.weather_app.util.filter;

import msn.weather_app.model.Range;
import msn.weather_app.model.RecordMeteo;

public class FilterTempFelt extends Filter<RecordMeteo>{
	public FilterTempFelt(Object param) {
		super();
		buildLogic(param);
	}
	
	private void buildLogic(Object param) {
		Range range = (Range) param;
		logic = rm -> range.contains(rm.getTemp().getFelt());
	}
}
