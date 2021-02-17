package application.gestion_vente;

import java.time.LocalDate;

public class VenteContainer {
	
	private long code;
	private String nom;
	private double total;
	private LocalDate date;
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
	
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	public VenteContainer(long code, String nom, double total, LocalDate date) {
		
		this.code = code;
		this.nom = nom;
		this.total = total;
		this.date = date;
	}
	@Override
	public String toString() {
		return "VenteContainer [code=" + code + ", nom=" + nom + ", total=" + total + ", date=" + date + "]";
	}
	
	
	
	

}
