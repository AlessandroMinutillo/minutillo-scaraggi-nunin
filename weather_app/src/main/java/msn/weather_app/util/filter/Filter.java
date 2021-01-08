package msn.weather_app.util.filter;

import java.util.function.Predicate;

/**
 * Implementazione della classe Filter
 * Classe generica che implementa un filtro avente come unico attributo un oggetto di tipo Predicate
 *  
 * @author Alessandro Minutillo 
 * @author Vito Scaraggi
 * @author Davide Nunin
 */

public class Filter<T>{
	/**
	 * Predicato
	 */
	protected Predicate <T> logic;
	/**
	 * Costruttore
	 */
	public Filter() {
		buildLogic();
	}
	/**
	 * @return il predicato
	 */
	public Predicate <T> getLogic(){
		return logic;
	}
	/**
	 * Imposta il predicato
	 * @param logic rappresenta il predicato
	 */
	public void setLogic(Predicate <T> logic){
		this.logic = logic;
	}
	/**
	 * Concatena due filtri in AND
	 * @param filter rappresenta il secondo filtro
	 */
	
	public void andCat(Filter<T> filter) {
		this.logic = this.logic.and(filter.logic);
	}
	
	/**
	 * Concatena due filtri in OR
	 * @param filter rappresenta il secondo filtro
	 */
	
	public void orCat(Filter<T> filter) {
		this.logic = this.logic.or(filter.logic);
	}
	
	/**
	 * Costruisce l'oggetto di tipo Predicate
	*/
	private void buildLogic() {
		logic = rm->true;
	}
	
}