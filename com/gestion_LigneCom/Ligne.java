package com.gestion_LigneCom;

import com.gestion_produit.Produit;

public class Ligne {
	
	private long code;
	private long vente;
	private int qte;
	private Produit produit;
	private double stotal;
	
	
	
	public long getCode() {
		return code;
	}
	public void setCode(long code) {
		this.code = code;
	}
	public long getVente() {
		return vente;
	}
	public void setVente(long vente) {
		this.vente = vente;
	}
	public int getQte() {
		return qte;
	}
	public void setQte(int qte) {
		this.qte = qte;
		this.stotal=qte * produit.getPrix();
	}
	public Produit getProduit() {
		return produit;
	}
	public void setProduit(Produit produit) {
		this.produit = produit;
		this.stotal=qte * produit.getPrix();
	}
	public Ligne(long vente,int qte, Produit produit) {
	
		this.vente=vente;
		this.qte = qte;
		this.produit = produit;
		this.stotal=qte * produit.getPrix();
		
	}
	public Ligne(long code ,long vente,int qte, Produit produit) {
		this.code=code;
		this.vente=vente;
		this.qte = qte;
		this.produit = produit;
		this.stotal=qte * produit.getPrix();
		
	}
	@Override
	public String toString() {
		return vente + ", " + qte + ", " + produit + " ";
	}
	public double getStotal() {
		// TODO Auto-generated method stub
		return stotal;
	}
	
	
	
	
	
	
}
