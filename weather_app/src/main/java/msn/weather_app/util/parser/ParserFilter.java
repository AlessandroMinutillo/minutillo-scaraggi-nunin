package msn.weather_app.util.parser;

import java.util.function.Predicate;

import org.json.JSONObject;

import msn.weather_app.model.Coord;
import msn.weather_app.model.Range;
import msn.weather_app.model.RecordMeteo;
import msn.weather_app.util.filter.*;

public class ParserFilter {
	public static Filter<RecordMeteo> getFilter(JSONObject top){
	
		Predicate<RecordMeteo> concat = rm -> true;
	
		if(top.has("name")) {
			String name = top.getString("name");
			Filter<RecordMeteo> f = new FilterSubstrRM(name);
			concat = concat.and(f.getLogic());
		}
		
		if(top.has("coord")) {
			JSONObject obj = top.getJSONObject("coord");
			double lat = obj.getDouble("lat");
			double lon = obj.getDouble("lon");
			Coord coord = new Coord(lat,lon);
			Filter<RecordMeteo> f = new FilterCoord(coord);
			concat = concat.and(f.getLogic());
		}
		
		if(top.has("period")) {
			String period = top.getString("period");
			Filter<RecordMeteo> f = new FilterPeriod(period);
			concat = concat.and(f.getLogic());
		}
		
		if(top.has("temp")) {
			JSONObject temp = top.getJSONObject("temp");
			
			if(temp.has("cur")) {
				JSONObject cur = temp.getJSONObject("cur");
				double from = cur.getDouble("from");
				double to = cur.getDouble("to");
				Range range = new Range(from,to);
				Filter<RecordMeteo> f = new FilterTempCur(range);
				concat = concat.and(f.getLogic());
			}
			
			if(temp.has("min")) {
				JSONObject min = temp.getJSONObject("min");
				double from = min.getDouble("from");
				double to = min.getDouble("to");
				Range range = new Range(from,to);
				Filter<RecordMeteo> f = new FilterTempMin(range);
				concat = concat.and(f.getLogic());
			}
			
			if(temp.has("max")) {
				JSONObject max = temp.getJSONObject("cur");
				double from = max.getDouble("from");
				double to = max.getDouble("to");
				Range range = new Range(from,to);
				Filter<RecordMeteo> f = new FilterTempMax(range);
				concat = concat.and(f.getLogic());
			}
		}
		
		if(top.has("press")) {
			JSONObject obj = top.getJSONObject("press");
			double from = obj.getDouble("from");
			double to = obj.getDouble("to");
			Range range = new Range(from,to);
			Filter<RecordMeteo> f = new FilterPress(range);
			concat = concat.and(f.getLogic());
		}
		
		Filter<RecordMeteo> ComposedFilter = new Filter<RecordMeteo>(concat);
		return ComposedFilter;
	}
}
