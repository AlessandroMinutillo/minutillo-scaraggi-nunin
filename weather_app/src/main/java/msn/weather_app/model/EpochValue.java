package msn.weather_app.model;

/**
 * Enum che associa a ciascuna unità di tempo il corrispondente valore in secondi
 */

public enum EpochValue {
	HOUR(3600),
	DAY(24 * HOUR.value),
	WEEK(7 * DAY.value),
	MONTH(30 * DAY.value);
	
	public long value;
	private EpochValue(long value) {
		this.value = value;
	}
}
