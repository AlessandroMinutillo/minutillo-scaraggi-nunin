package msn.weather_app.util.filter;

import java.util.function.Predicate;

/* Classe generica che implementa un filtro avente come unico attributo un oggetto di tipo Predicate
 * 
 */

public class Filter<T>{
	
	protected Predicate <T> logic;
	
	public Filter() {}
	
	public Predicate <T> getLogic(){
		return logic;
	}
	
	public void setLogic(Predicate <T> logic){
		this.logic = logic;
	}
	
}