package actions;

import personnages.Personnage;

public class RechercheDeBase extends Recherche {

	public RechercheDeBase(Personnage owner) {
		super(owner, 1, "Recherche", "Tente de r�v�ler les personnages dissimul�s dans la zone de cont�le", 0, 0);
	}

}
