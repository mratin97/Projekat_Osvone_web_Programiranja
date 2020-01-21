package model;

public class Film {
	
	private String id;
	private String naziv;
	private String reziser;
	private String glumci;
	private String zanrovi;
	private int trajanje;
	private String distribuer;
	private String ZemljaPorekla;
	private int Godina;
	private String opis;
	

	public Film(String id, String naziv, String reziser, String glumci, String zanrovi, int trajanje, String distribuer,
			String zemljaPorekla, int godina, String opis) {
		super();
		this.id = id;
		this.naziv = naziv;
		this.reziser = reziser;
		this.glumci = glumci;
		this.zanrovi = zanrovi;
		this.trajanje = trajanje;
		this.distribuer = distribuer;
		this.ZemljaPorekla = zemljaPorekla;
		this.Godina = godina;
		this.opis = opis;
	}
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
	public String getReziser() {
		return reziser;
	}
	public void setReziser(String reziser) {
		this.reziser = reziser;
	}
	public String getGlumci() {
		return glumci;
	}
	public void setGlumci(String glumci) {
		this.glumci = glumci;
	}
	public String getZanrovi() {
		return zanrovi;
	}
	public void setZanrovi(String zanrovi) {
		this.zanrovi = zanrovi;
	}
	public int getTrajanje() {
		return trajanje;
	}
	public void setTrajanje(int trajanje) {
		this.trajanje = trajanje;
	}
	public String getDistribuer() {
		return distribuer;
	}
	public void setDistribuer(String distribuer) {
		this.distribuer = distribuer;
	}
	public String getZemljaPorekla() {
		return ZemljaPorekla;
	}
	public void setZemljaPorekla(String zemljaPorekla) {
		ZemljaPorekla = zemljaPorekla;
	}
	public int getGodina() {
		return Godina;
	}
	public void setGodina(int godina) {
		Godina = godina;
	}
	public String getOpis() {
		return opis;
	}
	public void setOpis(String opis) {
		this.opis = opis;
	}
	
	
	
	
	
	
}
