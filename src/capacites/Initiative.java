package capacites;

import personnages.Personnage;

public class Initiative extends Capacite {

	public Initiative(Personnage owner) {
		super(owner);
		this.type = "Init";
		this.nom = "Initiative";
		this.description = "Une unité disposant...";
	}

}
