package com.pj.familyactivity.data;

import java.util.ArrayList;
import com.google.gson.annotations.Expose;
import com.pj.familyactivity.data.Clan;

public class Druzina {
	String name;
	@Expose
	ArrayList<Clan> seznam;	
	
	public Druzina(String name) {
		super();
		this.name = name;
		seznam = new ArrayList<Clan>();
	}
	public ArrayList<Clan> getSeznam(){
		return seznam;
	}
	public void dodaj(Clan c){
		seznam.add(c);
	}
}
