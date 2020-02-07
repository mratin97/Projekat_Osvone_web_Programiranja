package model;

import java.util.Date;

public class Projekcija {
	
	public String id;
	public String idFilma;
	public TipProjekcije tip;
	public Sala sala;
	public Date datum;
	public int cena;
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
	public TipProjekcije getTip() {
		return tip;
	}
	public void setTip(TipProjekcije tip) {
		this.tip = tip;
	}
	public Sala getSala() {
		return sala;
	}
	public void setSala(Sala sala) {
		this.sala = sala;
	}
	public Date getDatum() {
		return datum;
	}
	public void setDatum(Date datum) {
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

}
