package actions;

import personnages.Personnage;

public class EsquiveDeBase extends Esquive {

	public EsquiveDeBase(Personnage owner) {
		super(owner, 1, "Esquive", "Permet au personnage d'ajouter 1D10 � sa d�fense", 0, 1);
	}

}
