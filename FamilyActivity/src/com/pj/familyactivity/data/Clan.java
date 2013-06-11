package com.pj.familyactivity.data;

import com.google.gson.annotations.Expose;

public class Clan {
	@Expose
	private String priimek;
	@Expose
	private String ime;
	
	public Clan(String ime, String priimek) {
		super();
		this.ime = ime;
		this.priimek = priimek;		
	}
	public String getIme() {
		return ime;
	}
	public String getPriimek() {
		return priimek;
	}
	public void setIme(String ime) {
		this.ime = ime;
	}
	public void setPriimek(String priimek) {
		this.priimek = priimek;
	}
	public String toString() {
		return "Clan [priimek=" + priimek + ", ime=" + ime +  "]";
	}
}
