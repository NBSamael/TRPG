package attitudes;

import personnages.Personnage;
import data.Persistant;

public abstract class Attitude implements Persistant {
	protected String nom;
	protected String description;
	protected int niveau;
	protected Personnage cible;
	
	public Attitude(String nom, String description, int niveau, Personnage cible) {
		super();
		this.nom = nom;
		this.description = description;
		this.niveau = niveau;
		this.cible = cible;
	}

	public String getNom() {
		return nom;
	}

	public String getDescription() {
		return description;
	}

	public int getNiveau() {
		return niveau;
	}

	public void setNiveau(int niveau) {
		this.niveau = niveau;
	}
	
	@Override
	public boolean equals(Object obj) {
		return (this.nom == ((Attitude)obj).getNom());
	}

	@Override
	public String toString() {
		return getNom();
	}
}
