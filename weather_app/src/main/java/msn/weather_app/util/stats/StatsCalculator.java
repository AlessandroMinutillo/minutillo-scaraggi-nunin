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
	 * Metodo statico che calcola le statistiche relative al sample
	 * 
	 * @param sample arraylist filtrato
	 * @return statistiche
	 */
	
	public static Stats calc(ArrayList<RecordMeteo> sample) {
		
		Stats stat = new Stats();
		
		try {
			double n = sample.size();
			if(n == 0) throw new SampleException();
			double sumTempCur, sumTempFelt, sumPress;
			double tempMin, tempMax;
			double pressMin, pressMax;
			
			sumTempCur = sumTempFelt = sumPress = 0.d;
			
			tempMax = -100.d;
			tempMin = 100.d;
			pressMax = 0.d;
			pressMin = 2000.d;
			
			for(RecordMeteo rm : sample) {
				sumTempCur += rm.getTemp().getCur();
				sumTempFelt += rm.getTemp().getFelt();
				sumPress += rm.getPress();
				tempMax = Math.max(tempMax, rm.getTemp().getMax());
				tempMin = Math.min(tempMin, rm.getTemp().getMin());
				pressMax = Math.max(pressMax, rm.getPress());
				pressMin = Math.min(pressMin, rm.getPress());
			}
			
			double tempCurAvg = sumTempCur/n;
			double tempFeltAvg = sumTempFelt/n;
			double pressAvg = sumPress/n;
			
			double stDevTempCur, stDevTempFelt, stDevPress;
			
			stDevTempCur = stDevTempFelt = stDevPress = 0.d;
			
			for(RecordMeteo rm : sample) {
				stDevTempCur += Math.pow(rm.getTemp().getCur() - tempCurAvg , 2.d);
				stDevTempFelt += Math.pow(rm.getTemp().getFelt() - tempFeltAvg , 2.d);
				stDevPress += Math.pow(rm.getPress() - pressAvg , 2.d);
			}
			
			double tempCurVar = stDevTempCur/n;
			double tempFeltVar = stDevTempFelt/n;
			double pressVar = stDevPress/n;
			
			stat.addField("tempMin",tempMin);
			stat.addField("tempMax",tempMax);
			stat.addField("pressMin",pressMin);
			stat.addField("pressMax",pressMax);
			stat.addField("tempCurAvg", tempCurAvg);
			stat.addField("tempFeltAvg", tempFeltAvg);
			stat.addField("pressAvg", pressAvg);
			stat.addField("tempCurVar", tempCurVar);
			stat.addField("tempFeltVar", tempFeltVar);
			stat.addField("pressVar", pressVar);
		}
		catch(SampleException e) {
			System.out.println("Sample is empty\n" + e);
		}
		return stat;
	}
}
