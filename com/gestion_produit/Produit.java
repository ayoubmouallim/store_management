package com.gestion_produit;

import java.time.LocalDate;

public class Produit {

	private long code;
	private String designation;
	private double prix;
	private int qte;
	private double total; //caluculable
	private LocalDate date;
	
	
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	public int getQte() {
		return qte;
	}
	public void setQte(int qte) {
		this.qte = qte;
		total=qte*prix;
	}
	public long getCode() {
		return code;
	}
	public void setCode(long code) {
		this.code = code;
	}
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	public double getPrix() {
		return prix;
	}
	public void setPrix(double prix) {
		this.prix = prix;
	}
	
	
	public double getTotal() {
		return total;
	}
	public Produit(long code, String designation, double prix,int qte,LocalDate date) {
	
		this.code = code;
		this.designation = designation;
		this.prix = prix;
		this.qte=qte;
		this.date=date;
		this.total = qte * prix;
	}
	
	public Produit(String designation, double prix)
	{
		this.designation = designation;
		this.prix = prix;
	}
	
	@Override
	public String toString() {
		return "code=" + code + ": " + designation + ", " + prix + " MAD";
	}
	
	
}
