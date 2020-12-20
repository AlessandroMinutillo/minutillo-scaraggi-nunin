package msn.weather_app.service;
/**
 * Implementazione della classe FilterSubstrRM
 *  
 * Classe generica che applica un filtro a un ArrayList
 *
 * @author Alessandro Minutillo 
 * @author Vito Scaraggi
 * @author Davide Nunin
 */
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import msn.weather_app.util.filter.Filter;

public class FilterService<T> {
	
	/**
	 * Applica un filtro a un ArrayList
	 * @param filter rappresenta l'oggetto passato di tipo Filter
	 * @param list rappresenta l'oggetto passato di tipo ArrayList
	 */
	
	public ArrayList<T> applyFilter(Filter<T> filter, ArrayList<T> list){
		List<T> result = list.stream()
							.filter(filter.getLogic())
							.collect(Collectors.toList()); 
		return new ArrayList<T>(result);
	}
	
}
