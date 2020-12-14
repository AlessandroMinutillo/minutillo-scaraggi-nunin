package msn.weather_app.database;
import java.util.ArrayList;
import msn.weather_app.model.Metadata;
import msn.weather_app.model.RecordMeteo;
import msn.weather_app.model.City;

public class DatabaseClass {

	private static ArrayList<Metadata> metadata = new ArrayList<Metadata>();
	private static ArrayList<City> cities = new ArrayList<City>();
	private static ArrayList<RecordMeteo> meteo = new ArrayList<RecordMeteo>();
	
	public static ArrayList<Metadata> getMeta(){
		metadata.add(new Metadata("Temp","temperatura","String"));
		metadata.add(new Metadata("Press","pressione","String"));
		return metadata;
	}
}
