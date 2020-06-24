package model;

public class Izvestaj {

	String naziv;
	int brojP;
	int brojK;
	int cena;
	public String getNaziv() {
		return naziv;
	}
	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}
	public int getBrojP() {
		return brojP;
	}
	public void setBrojP(int brojP) {
		this.brojP = brojP;
	}
	public int getBrojK() {
		return brojK;
	}
	public void setBrojK(int brojK) {
		this.brojK = brojK;
	}
	public int getCena() {
		return cena;
	}
	public void setCena(int cena) {
		this.cena = cena;
	}
	public Izvestaj(String naziv, int brojP, int brojK, int cena) {
		super();
		this.naziv = naziv;
		this.brojP = brojP;
		this.brojK = brojK;
		this.cena = cena;
	}
	
	
}
