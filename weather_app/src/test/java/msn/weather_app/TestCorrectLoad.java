package msn.weather_app;
/**
 * Implementazione della classe TestCorrectLoad

 *
 * Si verifica mediante test la corretta creazione dell'arraylist CityList,
 * utilizzando il load dal file city.list.min.json.
 * In particolar modo si verifica che i singoli elementi dell'arraylist CityList corrispondano agli elementi del file 
 * city.list.min.json, opportunamente trattati mediante la procedura di parsing 
 * 
 * @author Alessandro Minutillo 
 * @author Vito Scaraggi
 * @author Davide Nunin
 */
import static org.junit.jupiter.api.Assertions.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import msn.weather_app.database.DatabaseClass;
import msn.weather_app.model.Coord;

class TestCorrectLoad {
	/**
	 * Setup iniziale
	 * @throws Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		DatabaseClass.loadCityList();
	}

	@AfterEach
	void tearDown() throws Exception {
	}
	/**
	 * Si controlla iterativamente che i singoli oggetti che compongono il mio JSONArray
	 * siano stati creati correttamente mediante l'utilizzo di assertEquals
	 * @see msn.weather_app.database.DatabaseClass#getCityList()
	 * @see msn.weather_app.model.City#getName()
	 * @see msn.weather_app.model.City#getCoords()
	 * @see msn.weather_app.model.Coord#getLat()
	 * @see msn.weather_app.model.Coord#getLon()
	 */
	@Test
	void testCorrectLoad() {
		try {
			BufferedReader fin = new BufferedReader(new FileReader("config/city.list.min.json"));
			String data = fin.readLine();
			fin.close();
			
			JSONArray v = new JSONArray(data);
			
			for(int i=0;i<v.length();i++) {
				JSONObject top = (JSONObject) v.get(i);
				JSONObject obj = top.getJSONObject("coord");
				Coord coord = new Coord(obj.getDouble("lat"), obj.getDouble("lon"));
				String name = top.getString("name");
				assertEquals(name, DatabaseClass.getCityList().get(i).getName());
				assertEquals(coord.getLat(), DatabaseClass.getCityList().get(i).getCoord().getLat());
				assertEquals(coord.getLon(), DatabaseClass.getCityList().get(i).getCoord().getLon());
			}
		}
		catch(IOException | JSONException e) {
			e.printStackTrace();
		}
		
	}

}
