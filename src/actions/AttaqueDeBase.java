package actions;

import personnages.Personnage;

public class AttaqueDeBase extends Attaque {

	public AttaqueDeBase(Personnage owner) {
		super(owner, 2, "Attaque de base", "Le personnage attaque une cible située dans un rayon maximum égal à sa portée d'attaque", 0, 1, 0, 0, 0);
	}

}
