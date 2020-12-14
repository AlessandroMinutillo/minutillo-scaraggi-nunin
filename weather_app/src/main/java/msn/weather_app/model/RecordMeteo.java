package msn.weather_app.model;

public class RecordMeteo {
	private Temp temp;
	private double press;
	private long epoch;
	private City city;
	
	public RecordMeteo(Temp temp, double press, long epoch, City city) {
		this.temp = temp;
		this.press = press;
		this.epoch = epoch;
		this.city = city;
	}
	
	public Temp getTemp() {
		return temp;
	}
	public void setTemp(Temp temp) {
		this.temp = temp;
	}
	public double getPress() {
		return press;
	}
	public void setPress(double press) {
		this.press = press;
	}
	public long getEpoch() {
		return epoch;
	}
	public void setEpoch(long epoch) {
		this.epoch = epoch;
	}
	public City getCity() {
		return city;
	}
	public void setCity(City city) {
		this.city = city;
	}
	
	
}
