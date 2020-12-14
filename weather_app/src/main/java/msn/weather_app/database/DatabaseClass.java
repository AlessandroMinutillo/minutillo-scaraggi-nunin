package msn.weather_app.database;
import java.util.ArrayList;
import msn.weather_app.model.Metadata;

public class DatabaseClass {

	private static ArrayList<Metadata> meta = new ArrayList<Metadata>();
	
	public static ArrayList<Metadata> getMeta(){
		meta.add(new Metadata("temp","temperatura","String"));
		meta.add(new Metadata("press","pressione","String"));
		return meta;
	}
}
