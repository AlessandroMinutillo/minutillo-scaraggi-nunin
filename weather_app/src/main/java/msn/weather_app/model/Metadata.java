package msn.weather_app.model;
/**
 * Implementazione della classe Metadata
 * 
 * Rappresenta la struttura dei metadata del progetto
 * 
 * @author Alessandro Minutillo 
 * @author Vito Scaraggi
 * @author Davide Nunin
 */
public class Metadata {

	String alias;
	String sourceField;
	String type;
	
	/**
	 * @param alias
	 * @param sourceField
	 * @param type
	 */
	public Metadata(String alias, String sourceField, String type) {
		this.alias = alias;
		this.sourceField = sourceField;
		this.type = type;
	}
	/**
	 * @return alias
	 */
	public String getAlias() {
		return alias;
	}

	/**
	 * Imposta l'alias
	 * @param alias
	 */
	public void setAlias(String alias) {
		this.alias = alias;
	}

	/**
	 * @return sourceField
	 */
	public String getSourceField() {
		return sourceField;
	}

	/**
	 * Imposta il sourceField
	 * @param sourceField
	 */
	public void setSourceField(String sourceField) {
		this.sourceField = sourceField;
	}

	/**
	 * @return type
	 */
	public String getType() {
		return type;
	}

	/**
	 * Imposta il type
	 * @param type
	 */
	public void setType(String type) {
		this.type = type;
	}
	
}

