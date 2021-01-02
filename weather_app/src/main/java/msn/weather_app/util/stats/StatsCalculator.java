package msn.weather_app.util.stats;

import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONObject;

import msn.weather_app.exception.SampleException;
import msn.weather_app.model.Range;
import msn.weather_app.model.RecordMeteo;
import msn.weather_app.model.TimeData;
import msn.weather_app.service.FilterService;
import msn.weather_app.util.filter.Filter;
import msn.weather_app.util.filter.FilterFreq;

/** Classe che implementa metodi per il calcolo delle statistiche
 * 
 * @author Alessandro Minutillo 
 * @author Vito Scaraggi
 * @author Davide Nunin
 * 
 * 
 */

public class StatsCalculator {
	/**
	 * Restituisce le statistiche relative al periodo selezionato tramite filtro,
	 * partizionandole in base alla frequenza richiesta dall'utente
	 * 
	 * 
	 * @param sample ArrayList<RecordMeteo>
	 * @param type indica se le statistiche riguardano temperatura o pressione
	 * @param top JSONObject
	 * @return ret HashMap<String, HashMap<String,Double> >
	 */
	
	public static HashMap<String, HashMap<String,Double> > calc(ArrayList<RecordMeteo> sample, String type, JSONObject top){
		
		HashMap<String, HashMap<String,Double> > ret = new HashMap<String, HashMap<String,Double> >();
			
		long from, to, step;
		
		String freq = FilterService.setFreq(top);
		step = FilterService.setStep(freq);
		from = FilterService.setFrom(top);
		to = TimeData.NOW;
		
		if(freq.equals("total") ) {
			switch(type) {
				case "temp" : ret.put(freq, calcTemp(sample));
				break;
				case "press" : ret.put(freq, calcPress(sample));
				break;
			}
		}
		else {
			FilterService<RecordMeteo> fs = new FilterService<RecordMeteo>();
			
			for(long cont = 1; from < to; cont++) {
				Range range = new Range(from, Math.min(from+step,to));
				Filter<RecordMeteo> filter = new FilterFreq(range);
				ArrayList<RecordMeteo> array = fs.applyFilter(filter, sample);
				switch(type) {
					case "temp" : ret.put(freq + "_" + cont, calcTemp(array));
					break;
					case "press" : ret.put(freq + "_" + cont, calcPress(array));
					break;
				}
				from += step;
			}
		}
		
		return ret;
	}
	
	
	/**
	 * Metodo statico che calcola le statistiche sulla temperatura relative al sample
	 * 
	 * @param sample ArrayList filtrato
	 * @return stat statistiche sulla temperatura
	 */
	
	public static HashMap<String,Double> calcTemp(ArrayList<RecordMeteo> sample) {
		
		HashMap<String,Double> stat = new HashMap<String,Double>();
		
		try {
			double n = sample.size();
			if(n == 0) throw new SampleException();
			double sumTempCur, sumTempFelt;
			double tempMin, tempMax;
			
			sumTempCur = sumTempFelt = 0.d;
			
			tempMax = -100.d;
			tempMin = 100.d;
			
			for(RecordMeteo rm : sample) {
				sumTempCur += rm.getTemp().getCur();
				sumTempFelt += rm.getTemp().getFelt();
				tempMax = Math.max(tempMax, rm.getTemp().getMax());
				tempMin = Math.min(tempMin, rm.getTemp().getMin());
			}
			
			double tempCurAvg = sumTempCur/n;
			double tempFeltAvg = sumTempFelt/n;
			
			double stDevTempCur, stDevTempFelt;
			
			stDevTempCur = stDevTempFelt = 0.d;
			
			for(RecordMeteo rm : sample) {
				stDevTempCur += Math.pow(rm.getTemp().getCur() - tempCurAvg , 2.d);
				stDevTempFelt += Math.pow(rm.getTemp().getFelt() - tempFeltAvg , 2.d);
			}
			
			double tempCurVar = stDevTempCur/n;
			double tempFeltVar = stDevTempFelt/n;
			
			stat.put("tempMin",tempMin);
			stat.put("tempMax",tempMax);
			stat.put("tempCurAvg", tempCurAvg);
			stat.put("tempFeltAvg", tempFeltAvg);
			stat.put("tempCurVar", tempCurVar);
			stat.put("tempFeltVar", tempFeltVar);
		}
		catch(SampleException e) {
			System.out.println("Sample is empty\n" + e);
		}
		return stat;
	}
	
	/**
	 * Metodo statico che calcola le statistiche sulla pressione relative al sample
	 * 
	 * @param sample ArrayList filtrato
	 * @return stat statistiche sulla pressione
	 */
	
	public static HashMap<String,Double> calcPress(ArrayList<RecordMeteo> sample) {
		
		HashMap<String,Double> stat = new HashMap<String,Double>();
		
		try {
			double n = sample.size();
			if(n == 0) throw new SampleException();
			double sumPress;
			double pressMin, pressMax;
			
			sumPress = 0.d;
			pressMax = 0.d;
			pressMin = 2000.d;
			
			for(RecordMeteo rm : sample) {
				sumPress += rm.getPress();
				pressMax = Math.max(pressMax, rm.getPress());
				pressMin = Math.min(pressMin, rm.getPress());
			}
			
			double pressAvg = sumPress/n;
			
			double stDevPress;
			
			stDevPress = 0.d;
			
			for(RecordMeteo rm : sample)
				stDevPress += Math.pow(rm.getPress() - pressAvg , 2.d);
			
			double pressVar = stDevPress/n;
			
			stat.put("pressMin",pressMin);
			stat.put("pressMax",pressMax);
			stat.put("pressAvg", pressAvg);
			stat.put("pressVar", pressVar);
		}
		catch(SampleException e) {
			System.out.println("Sample is empty\n" + e);
		}
		return stat;
	}
}
