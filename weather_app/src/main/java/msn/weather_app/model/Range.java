package msn.weather_app.model;

public class Range{
	double from, to;
	
	public Range(double from, double to) {
		this.from = from;
		this.to = to;
	}
	
	public boolean contains(double d) {
		return d >= from && d<=to;
	}
}
