package msn.weather_app.database;
import java.util.ArrayList;
import msn.weather_app.model.Metadata;

public class DatabaseClass {

	private static ArrayList<Metadata> metadata = new ArrayList<Metadata>();
	
	public static ArrayList<Metadata> getMeta(){
		metadata.add(new Metadata("Temp","temperatura","String"));
		metadata.add(new Metadata("Press","pressione","String"));
		return metadata;
	}
}
