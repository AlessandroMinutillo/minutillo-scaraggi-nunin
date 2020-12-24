package msn.weather_app;
/**
 * Implementazione della classe TestCorrectLoad

 *
 * Si verifica mediante test la correttezza di eventuali coordinate inserite dall'utente.
 * Ci occupiamo dunque di verificare che il nostro codice gestisca correttamente
 * l'eccezione CoordException, da noi opportunamente creata
 * 
 * @author Alessandro Minutillo 
 * @author Vito Scaraggi
 * @author Davide Nunin
 */
import static org.junit.jupiter.api.Assertions.*;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import msn.weather_app.model.Coord;
import msn.weather_app.exception.CoordException;

class TestCoord {
	private Coord coord;
	/**
	 * Costruisco un oggetto Coord passandogli delle coordinate non corrette
	 * @throws Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		coord = new Coord (2000,2000); //esempio di coordinate sbagliate
	}

	@AfterEach
	void tearDown() throws Exception {
	}
	/**
	 * Mediante assertThrows vado a vedere se l'eccezione CoordException Ã¨ stata opportunamente lanciata
	 * e successivamente mediante assertEquals confronto se i due messaggi di errore coincidono
	 * @see msn.weather_app.model.Coord#validate() throws CoordException
	 */
	@Test
	void test() {
		CoordException exception = assertThrows(CoordException.class, () -> {
            coord.validate();
        });
		assertEquals("Wrong Coordinates", exception.getMessage());
	}

}
