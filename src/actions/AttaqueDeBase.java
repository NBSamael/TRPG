package actions;

import personnages.Personnage;

public class AttaqueDeBase extends Attaque {

	public AttaqueDeBase(Personnage owner) {
		super(owner, 2, "Attaque de base", "Le personnage attaque une cible situ�e dans un rayon maximum �gal � sa port�e d'attaque", 0, 1, 0, 0, 0);
	}

}
