package msn.weather_app.model;

public class City {
	private String name;
	private Coord coords;
	
	public City(String name, Coord coords) {
		this.name = name;
		this.coords = coords;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Coord getCoords() {
		return coords;
	}
	public void setCoords(Coord coords) {
		this.coords = coords;
	}
	
}
