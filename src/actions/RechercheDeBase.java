package actions;

import personnages.Personnage;

public class RechercheDeBase extends Recherche {

	public RechercheDeBase(Personnage owner) {
		super(owner, 1, "Recherche", "Tente de révéler les personnages dissimulés dans la zone de contôle", 0, 0);
	}

}
