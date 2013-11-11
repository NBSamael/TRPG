package actions;

import personnages.Personnage;

public class ChargeDeBase extends Charge {

	public ChargeDeBase(Personnage owner) {
		super(owner, 3, "Charge", "Le personnage effectue une charge sur l'ennemi", 2, 2, 0,
				0, 0);
	}

}
