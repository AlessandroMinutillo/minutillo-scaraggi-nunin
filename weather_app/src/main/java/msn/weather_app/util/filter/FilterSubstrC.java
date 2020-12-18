package msn.weather_app.util.filter;

import msn.weather_app.model.City;

public class FilterSubstrC extends Filter<City>{
	
	public FilterSubstrC(Object param) {
		super();
		buildLogic(param);
	}
	
	private void buildLogic(Object param) {
		String string = (String) param;
		logic = c -> c.getName().contains(string);
	}
}
