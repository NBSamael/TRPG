package capacites;

import personnages.Personnage;

public abstract class Capacite {
	protected Personnage owner;
	protected String nom;
	protected String description;
	protected String type;
	
	public Capacite(Personnage owner) {
		super();
		this.owner = owner;
	}

	public String getType() {
		return type;
	}	
}
