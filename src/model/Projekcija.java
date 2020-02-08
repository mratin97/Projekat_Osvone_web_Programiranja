package model;

import java.util.Date;

public class Projekcija {
	
	public String id;
	public String idFilma;
	public String tip;
	public String sala;
	public String datum;
	public String vreme;
	public int cena;
	public String getVreme() {
		return vreme;
	}
	public void setVreme(String vreme) {
		this.vreme = vreme;
	}
	public String adminId;
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getIdFilma() {
		return idFilma;
	}
	public void setIdFilma(String idFilma) {
		this.idFilma = idFilma;
	}
	public String getTip() {
		return tip;
	}
	public void setTip(String tip) {
		this.tip = tip;
	}
	public String getSala() {
		return sala;
	}
	public void setSala(String sala) {
		this.sala = sala;
	}
	public String getDatum() {
		return datum;
	}
	public void setDatum(String datum) {
		this.datum = datum;
	}
	public int getCena() {
		return cena;
	}
	public void setCena(int cena) {
		this.cena = cena;
	}
	public String getAdminId() {
		return adminId;
	}
	public void setAdminId(String adminId) {
		this.adminId = adminId;
	}

	public Projekcija(String id, String idFilma, String tip, String sala, String datum, String vreme, int cena,
			String adminId) {
		super();
		this.id = id;
		this.idFilma = idFilma;
		this.tip = tip;
		this.sala = sala;
		this.datum = datum;
		this.vreme = vreme;
		this.cena = cena;
		this.adminId = adminId;
	}
	

}
