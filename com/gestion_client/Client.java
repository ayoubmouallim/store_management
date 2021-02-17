package com.gestion_client;

import java.util.ArrayList;
import java.util.List;

import com.gestion_Vente.Vente;

public class Client {

	private long code;
	private String nom;
	private String prenom;
	private String tel;
	private String email;
	private String adresse;
	private List<Vente> ventes = new ArrayList<>();
	
	public void addVente(Vente v)
	{
		ventes.add(v);
	}
	
	public Client(long code, String nom, String prenom, String tel, String email, String adresse) {
		
		this.code = code;
		this.nom = nom;
		this.prenom = prenom;
		this.tel = tel;
		this.email = email;
		this.adresse = adresse;
	}
	public long getCode() {
		return code;
	}
	public void setCode(long code) {
		this.code = code;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAdresse() {
		return adresse;
	}
	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}
	@Override
	public String toString() {
		return "Client [code=" + code + ", nom=" + nom + ", prenom=" + prenom + ", tel=" + tel + ", email=" + email
				+ ", adresse=" + adresse + "]";
	}
	
	
	
	
}
