package model;

public class Karta {
	private String id;
	private String projekcija;
	private String sediste;
	private String datum;
	private String korisnik;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getProjekcija() {
		return projekcija;
	}
	public void setProjekcija(String projekcija) {
		this.projekcija = projekcija;
	}
	public String getSediste() {
		return sediste;
	}
	public void setSediste(String sediste) {
		this.sediste = sediste;
	}
	public String getDatum() {
		return datum;
	}
	public void setDatum(String datum) {
		this.datum = datum;
	}
	public String getKorisnik() {
		return korisnik;
	}
	public void setKorisnik(String korisnik) {
		this.korisnik = korisnik;
	}
	public Karta(String id, String projekcija, String sediste, String datum, String korisnik) {
		super();
		this.id = id;
		this.projekcija = projekcija;
		this.sediste = sediste;
		this.datum = datum;
		this.korisnik = korisnik;
	}
	
}
