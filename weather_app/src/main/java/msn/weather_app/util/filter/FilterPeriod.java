package msn.weather_app.util.filter;

import msn.weather_app.model.RecordMeteo;

public class FilterPeriod extends Filter<RecordMeteo>{
	
	private enum EpochValue {
		DAY(86400.d),
		WEEK(604800.d),
		MONTH(18144000.d);
		
		private double value;
		private EpochValue(double value) {
			this.value = value;
		}
	}
	
	public FilterPeriod(Object param) {
		super();
		buildLogic(param);
	}
	
	private void buildLogic(Object param) {
		String string = (String) param;
		long now = 0; // epoch in questo momento
		switch(string) {
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
				Long d = Long.parseLong(string);
				logic = rm-> rm.getEpoch() >= now - d * EpochValue.DAY.value;
			}
		}
	}
	
}
