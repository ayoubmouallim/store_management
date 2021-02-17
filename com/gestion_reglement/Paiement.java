package com.gestion_reglement;

import java.time.LocalDate;

import com.gestion_Vente.Vente;

public class Paiement {

	private long id;
	private double total;
	private String type;  //ESPECE    CHEQUE
	private Vente vente;
	private LocalDate date;
	
	
	
	
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Vente getVente() {
		return vente;
	}
	public void setVente(Vente vente) {
		this.vente = vente;
	}
	@Override
	public String toString() {
		return "Paiement [id=" + id + ", total=" + total + ", type=" + type + ", vente=" + vente + "]";
	}
	public Paiement(long id, double total, String type, Vente vente,LocalDate date) {
		this.id = id;
		this.total = total;
		this.type = type;
		this.vente = vente;
		this.date = date;
	}
	
	
	
}
