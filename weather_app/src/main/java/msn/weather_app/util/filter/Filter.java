package msn.weather_app.util.filter;

import java.util.function.Predicate;

public class Filter<T>{
	
	protected Predicate <T> logic;
	
	public Filter() {}
	
	public Filter(Predicate <T> logic) {
		this.logic = logic;
	}
	
	public Predicate <T> getLogic(){
		return logic;
	}
	
}