package msn.weather_app.util.filter;

import msn.weather_app.model.Coord;
import msn.weather_app.model.RecordMeteo;

public class FilterCoord extends Filter<RecordMeteo>{
	
	public FilterCoord(Object param) {
		super();
		buildLogic(param);
	}
	
	private void buildLogic(Object param) {
		Coord coord = (Coord) param;
		logic = rm -> rm.getCity().getCoords().getLat() == coord.getLat() &&
					rm.getCity().getCoords().getLon() == coord.getLon();
	}
}
