package model;

public class Sala {

	public String id;
	public String naziv;
	public String tip;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getNaziv() {
		return naziv;
	}
	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}
	public String getTip() {
		return tip;
	}
	public void setTip(String tip) {
		this.tip = tip;
	}
	public Sala(String id, String naziv, String tip) {
		super();
		this.id = id;
		this.naziv = naziv;
		this.tip = tip;
	}
	
}
