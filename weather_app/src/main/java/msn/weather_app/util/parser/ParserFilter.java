package msn.weather_app.util.parser;

import org.json.JSONException;
import org.json.JSONObject;

import msn.weather_app.model.Coord;
import msn.weather_app.model.Range;
import msn.weather_app.model.RecordMeteo;
import msn.weather_app.util.filter.*;

public class ParserFilter {

	
	public static Filter<RecordMeteo> getFilter(JSONObject top){
		
		Filter<RecordMeteo> ComposedFilter = new Filter<RecordMeteo>();
		
		try {
			
			if(top.has("name")) {
				String name = top.getString("name");
				ComposedFilter.andCat(new FilterSubstrRM(name));
			}
			
			if(top.has("coord")) {
				JSONObject obj = top.getJSONObject("coord");
				double lat = obj.getDouble("lat");
				double lon = obj.getDouble("lon");
				Coord coord = new Coord(lat,lon);
				ComposedFilter.andCat(new FilterCoord(coord));
			}
			
			if(top.has("period")) {
				String period = top.getString("period");
				ComposedFilter.andCat(new FilterPeriod(period));
			}
			
			if(top.has("temp")) {
				JSONObject temp = top.getJSONObject("temp");
				
				if(temp.has("cur")) {
					JSONObject cur = temp.getJSONObject("cur");
					double from = cur.getDouble("from");
					double to = cur.getDouble("to");
					Range range = new Range(from,to);
					ComposedFilter.andCat(new FilterTempCur(range));
				}
				
				if(temp.has("min")) {
					JSONObject min = temp.getJSONObject("min");
					double from = min.getDouble("from");
					double to = min.getDouble("to");
					Range range = new Range(from,to);
					ComposedFilter.andCat(new FilterTempMin(range));
				}
				
				if(temp.has("max")) {
					JSONObject max = temp.getJSONObject("cur");
					double from = max.getDouble("from");
					double to = max.getDouble("to");
					Range range = new Range(from,to);
					ComposedFilter.andCat(new FilterTempMax(range));
				}
				
				if(temp.has("felt")) {
					JSONObject felt = temp.getJSONObject("felt");
					double from = felt.getDouble("from");
					double to = felt.getDouble("to");
					Range range = new Range(from,to);
					ComposedFilter.andCat(new FilterTempFelt(range));
				}
			}
			
			if(top.has("press")) {
				JSONObject obj = top.getJSONObject("press");
				double from = obj.getDouble("from");
				double to = obj.getDouble("to");
				Range range = new Range(from,to);
				ComposedFilter.andCat(new FilterPress(range));
			}
		}
		catch(JSONException e) {
			System.out.println("Malformed JSONObject: unpredictable filter created\n" + e);
		}
		return ComposedFilter;
	}
}
