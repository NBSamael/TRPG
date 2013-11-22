package attitudes;

public abstract class Attitude {
	protected String nom;
	protected String description;
	protected int niveau;
	
	public Attitude(String nom, String description, int niveau) {
		super();
		this.nom = nom;
		this.description = description;
		this.niveau = niveau;
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
