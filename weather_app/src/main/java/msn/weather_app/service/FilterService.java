package msn.weather_app.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import msn.weather_app.util.filter.Filter;

/* Classe generica che applica un filtro a un ArrayList
 * 
 */

public class FilterService<T> {
	
	/* applica un filtro a un ArrayList
	 * @param filter
	 * @param list
	 */
	
	public ArrayList<T> applyFilter(Filter<T> filter, ArrayList<T> list){
		List<T> result = list.stream()
							.filter(filter.getLogic())
							.collect(Collectors.toList()); 
		return new ArrayList<T>(result);
	}
	
}
