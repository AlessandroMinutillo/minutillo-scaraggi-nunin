package msn.weather_app.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.json.JSONException;
import org.json.JSONObject;

import msn.weather_app.model.EpochValue;
import msn.weather_app.model.TimeData;
import msn.weather_app.util.filter.Filter;

/**
 * Implementazione della classe FilterService
 *  
 * Classe generica che applica un filtro a un ArrayList
 *
 * @author Alessandro Minutillo 
 * @author Vito Scaraggi
 * @author Davide Nunin
 */

public class FilterService<T> {
	
	/**
	 * Applica un filtro a un ArrayList
	 * @param filter rappresenta l'oggetto passato di tipo Filter
	 * @param list rappresenta l'oggetto passato di tipo ArrayList
	 * @return ArrayList del tipo T
	 */
	
	public ArrayList<T> applyFilter(Filter<T> filter, ArrayList<T> list){
		List<T> result = list.stream()
							.filter(filter.getLogic())
							.collect(Collectors.toList()); 
		return new ArrayList<T>(result);
	}
	
	/**
	 * Restituisce la stringa corrispondente alla frequenza richiesta
	 * @param top JSONObject
	 * @return freq
	 */
	
	public static String setFreq(JSONObject top) {
		
		String freq = "total";
		
		try {
			if(top.has("freq")) {
				freq = top.getString("freq");
				switch(freq) {
					case "total" : break;
					case "hour" : break;
					case "day" : break;
					case "week" : break;
					case "month" : break;
					default:{
						char c = freq.charAt(0);
						Long q = Long.parseLong(freq.substring(1));
						switch(c) {
							case 'h': break;
							case 'd': break;
							case 'w': break;
							case 'm': break;
							default: 
								freq = "total";
						}
					}
				}
			}
		}
		catch(EnumConstantNotPresentException | IndexOutOfBoundsException | NumberFormatException e) {
			System.out.println("Period filter error: invalid period\n" + e);
			freq = "total";
		}
		catch(JSONException e) {
			System.out.println("Malformed JSONObject: unpredictable frequency filter created\n" + e);
		}
		return freq;
	}
	
	/**
	 * Restituisce il numero di secondi corrispondente alla frequenza richiesta
	 * @param freq stringa che indica la frequenza
	 * @return step
	 */
	
	public static long setStep(String freq) {
		
		long step = 0;
		
		switch(freq) {
			case "total" : step = TimeData.NOW - TimeData.LOW_LIMIT;
			break;
			case "hour" : step = EpochValue.HOUR.value;
			break;
			case "day" : step = EpochValue.DAY.value;
			break;
			case "week" : step = EpochValue.WEEK.value;
			break;
			case "month" : step = EpochValue.MONTH.value;
			break;
			default:{
				char c = freq.charAt(0);
				Long q = Long.parseLong(freq.substring(1));
				switch(c) {
					case 'h': step = q * EpochValue.HOUR.value;
					break;
					case 'd': step = q * EpochValue.DAY.value;
					break;
					case 'w': step = q * EpochValue.WEEK.value;
					break;
					case 'm': step = q * EpochValue.MONTH.value;
					break;
				}
			}
		}
			
		return step;
	}
	
	/**
	 * Restituisce l'estremo inferiore del periodo di interesse in secondi
	 * 
	 * @param top JSONObject
	 * @return from
	 */
	
	public static long setFrom(JSONObject top) {
		
		long from = TimeData.LOW_LIMIT;
		
		try {
			if(top.has("period")) {
				String period = top.getString("period");
				
				switch(period) {
					case "hour": from = TimeData.NOW - EpochValue.HOUR.value;
					break;
					case "day": from = TimeData.NOW - EpochValue.DAY.value;
					break;
					case "week": from = TimeData.NOW - EpochValue.WEEK.value;
					break;
					case "month": from = TimeData.NOW - EpochValue.MONTH.value;
					break;
					default:{
						char c = period.charAt(0);
						Long q = Long.parseLong(period.substring(1));
						switch(c) {
							case 'h': from = TimeData.NOW - q * EpochValue.HOUR.value;
							break;
							case 'd': from = TimeData.NOW - q * EpochValue.DAY.value;
							break;
							case 'w': from = TimeData.NOW - q * EpochValue.WEEK.value;
							break;
							case 'm': from = TimeData.NOW - q * EpochValue.MONTH.value;
							break;
							default: from = TimeData.LOW_LIMIT;
						}
					}
				}
			}
		}
		catch(EnumConstantNotPresentException | IndexOutOfBoundsException | NumberFormatException e) {
			System.out.println("Period filter error: invalid period\n" + e);
		}
		catch(JSONException e) {
			System.out.println("Malformed JSONObject: unpredictable frequency filter created\n" + e);
		}
		return from;
		
	}
	
}
