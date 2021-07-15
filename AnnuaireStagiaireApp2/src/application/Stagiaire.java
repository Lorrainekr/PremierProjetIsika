package application;

public class Stagiaire implements Comparable<Stagiaire> {
	public String nom;
	public String prenom;
	public int departement;
	public String promo;
	public int annee;
	public int Id;
	public int refGauche;
	public int refDroit;
	public int ref;
	

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public Stagiaire(String nom, String prenom, int departement, String promo, int annee) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.departement = departement;
		this.promo = promo;
		this.annee = annee;
		
	}

	public Stagiaire() {
	}


	public Stagiaire(int ref, String nom2, String prenom2, int departement2, String promo2, int annee2) {
		// TODO Auto-generated constructor stub
		this.nom = nom2;
		this.prenom = prenom2;
		this.departement = departement2;
		this.promo = promo2;
		this.annee = annee2;
		this.ref = ref;
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


	public int getDepartement() {
		return departement;
	}


	public void setDepartement(int departement) {
		this.departement = departement;
	}


	public String getPromo() {
		return promo;
	}


	public void setPromo(String promo) {
		this.promo = promo;
	}


	public int getAnnee() {
		return annee;
	}


	public void setAnnee(int annee) {
		this.annee = annee;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Stagiaire [nom=");
		builder.append(nom);
		builder.append(", prenom=");
		builder.append(prenom);
		builder.append(", departement=");
		builder.append(departement);
		builder.append(", promo=");
		builder.append(promo);
		builder.append(", annee=");
		builder.append(annee);
		builder.append("]");
		return builder.toString();
	}

	@Override
	public int compareTo(Stagiaire o) {
		return this.nom.compareTo(o.nom);
	}

}
