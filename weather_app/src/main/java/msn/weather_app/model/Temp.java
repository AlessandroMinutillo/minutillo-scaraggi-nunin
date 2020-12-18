package msn.weather_app.model;

public class Temp {
	private double cur;
	private double min;
	private double max;
	private double felt;
	
	public Temp(double cur, double min, double max, double felt) {
		this.cur = cur;
		this.min = min;
		this.max = max;
		this.felt = felt;
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

	public double getFelt() {
		return felt;
	}

	public void setFelt(double felt) {
		this.felt = felt;
	}
	
	
}
