package msn.weather_app.util.stats;

import java.util.ArrayList;

import msn.weather_app.exception.SampleException;
import msn.weather_app.model.RecordMeteo;

/** Classe che implementa il metodo calc per il calcolo delle statistiche
 * 
 * @author Alessandro Minutillo 
 * @author Vito Scaraggi
 * @author Davide Nunin
 * 
 * 
 */

public class StatsCalculator {
	
	
	/**
	 * Metodo statico che calcola le statistiche sulla temperatura relative al sample
	 * 
	 * @param sample ArrayList filtrato
	 * @return statistiche
	 */
	
	public static Stats calcTemp(ArrayList<RecordMeteo> sample) {
		
		Stats stat = new Stats();
		
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
			
			stat.addField("tempMin",tempMin);
			stat.addField("tempMax",tempMax);
			stat.addField("tempCurAvg", tempCurAvg);
			stat.addField("tempFeltAvg", tempFeltAvg);
			stat.addField("tempCurVar", tempCurVar);
			stat.addField("tempFeltVar", tempFeltVar);
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
	 * @return statistiche
	 */
	
	public static Stats calcPress(ArrayList<RecordMeteo> sample) {
		
		Stats stat = new Stats();
		
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
			
			stat.addField("pressMin",pressMin);
			stat.addField("pressMax",pressMax);
			stat.addField("pressAvg", pressAvg);
			stat.addField("pressVar", pressVar);
		}
		catch(SampleException e) {
			System.out.println("Sample is empty\n" + e);
		}
		return stat;
	}
}
