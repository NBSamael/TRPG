package actions;

import personnages.Personnage;

public class SniperSombre extends Attaque {
	// TODO : Implémenter la restriction sur la maoneuvre Protection

	public SniperSombre(Personnage owner) {
		super(owner, 3, "Sniper Sombre", "Portée de l'Attaque (24), Attaque +5 / Armure -4. Cette attaque ne peut pas être interceptée par la manoeuvre Protection. Cette capacité n'est utilisable que si le personnage est caché.", 5, 1, 0, 4,
				24);
	}

	@Override
	public boolean isLegal() {
		return owner.isDissimule();
	}

	
}
