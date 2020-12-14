package msn.weather_app.model;

public class Temp {
	private double cur;
	private double min;
	private double max;
	
	public Temp(double cur, double min, double max) {
		this.cur = cur;
		this.min = min;
		this.max = max;
	}
	
	public double getCur() {
		return cur;
	}
	public void setCur(double cur) {
		this.cur = cur;
	}
	
	public double getMin() {
		return min;
	}
	public void setMin(double min) {
		this.min = min;
	}
	
	public double getMax() {
		return max;
	}
	public void setMax(double max) {
		this.max = max;
	}
	
	
}
