package application.gestion_produit;

public class LigneContainer {
	
	long code;
	String designation;
    double prix;
    double total;
    int qte;
    
 
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



	public void setTotal(double total) {
		this.total = total;
	}



	public int getQte() {
		return qte;
	}



	public void setQte(int qte) {
		this.qte = qte;
	}



	public LigneContainer( long code,String designation, double prix,int qte, double total) {
		
		this.code = code;
		this.designation = designation;
		this.prix = prix;
		this.total = total;
		this.qte = qte;
	}
}
