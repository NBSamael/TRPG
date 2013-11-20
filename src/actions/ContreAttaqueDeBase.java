package actions;

import personnages.Personnage;

public class ContreAttaqueDeBase extends ContreAttaque {

	public ContreAttaqueDeBase(Personnage owner) {
		super(owner, 3, "Contre Attaque",
				"fait une contre attaque si la défense est réussie", 0, 1, 0,
				1, 0, 0, 0);
	}

}
