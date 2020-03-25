package cst438.domain;

public class Reservation {

	private String cityName;
	private String level;
	private String email;
	
	public Reservation() {
		cityName = null;
		level = null;
		email = null;
	}
	
	public Reservation(String cityName, String level, String email) {
		super();
		this.cityName = cityName;
		this.level = level;
		this.email = email;
	}
	
	public String getCityName() {
		return cityName;
	}
	
	public String getLevel() {
		return level;
	}
	
	public String getEmail() {
		return email;
	}
	
	void setCityname(String cityName) {
		this.cityName = cityName;
	}
	
	void setLevel(String level) {
		this.level = level;
	}
	
	void setEmail(String email) {
		this.email = email;
	}
}
