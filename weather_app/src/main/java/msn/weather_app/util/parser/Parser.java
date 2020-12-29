package msn.weather_app.util.parser;

import java.util.ArrayList;
/**
 * interfaccia contenente il metodo astratto load()
 * @author Alessandro Minutillo 
 * @author Vito Scaraggi
 * @author Davide Nunin
 * @param <E>
 */
public interface Parser<E>{
	public ArrayList<E> load();
}
