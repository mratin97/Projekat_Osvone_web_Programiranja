package model;

public class Korisnik {
	private String id;
	private String pass;
	private String datum;
	private boolean role;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	public String getDatum() {
		return datum;
	}
	public void setDatum(String datum) {
		this.datum = datum;
	}
	public boolean isRole() {
		return role;
	}
	public void setRole(boolean role) {
		this.role = role;
	}
	public Korisnik(String id, String pass, String datum, boolean role) {
		super();
		this.id = id;
		this.pass = pass;
		this.datum = datum;
		this.role = role;
	}
	
}
