package com.gestion_Vente;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import com.gestion_LigneCom.Ligne;
import com.gestion_client.Client;

public class Vente {
	
	private long code;
	private LocalDate date; 
	private List<Ligne> lignes = new ArrayList<>();
	private Client client;
	private double totale; // calculated
	
	
	public long getCode() {
		return code;
	}
	public void setCode(long code) {
		this.code = code;
	}
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	public List<Ligne> getLignes() {
		return lignes;
	}
	public void setLignes(List<Ligne> lignes) {
		this.lignes = lignes;
		totale=calculerTotal();
	}
	public Client getClient() {
		return client;
	}
	public void setClient(Client client) {
		this.client = client;
	}
	public double getTotale() {
		return totale;
	}
	
	public double calculerTotal()
	{
		double t=0;
		for(Ligne l:lignes)
			t+=l.getStotal();
		return t;
	}
	public Vente(long code, LocalDate date, List<Ligne> lignes, Client client) {
		
		this.code = code;
		this.date = date;
		this.lignes = lignes;
		this.client = client;
		totale = calculerTotal();
	}
	
public Vente(long code, LocalDate date, Client client) {
		
		this.code = code;
		this.date = date;
		this.client = client;
		totale = calculerTotal();
	}
@Override
public String toString() {
	return "Vente [code=" + code + ", date=" + date + ", lignes=" + lignes + ", client=" + client + ", totale=" + totale
			+ "]";
}


	

	
   
}
