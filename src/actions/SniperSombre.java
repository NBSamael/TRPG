package actions;

import personnages.Personnage;

public class SniperSombre extends Attaque {
	// TODO : Impl�menter la restriction sur la maoneuvre Protection

	public SniperSombre(Personnage owner) {
		super(owner, 3, "Sniper Sombre", "Port�e de l'Attaque (24), Attaque +5 / Armure -4. Cette attaque ne peut pas �tre intercept�e par la manoeuvre Protection. Cette capacit� n'est utilisable que si le personnage est cach�.", 5, 1, 0, 4,
				24);
	}

	@Override
	public boolean isLegal() {
		return owner.isDissimule();
	}

	
}
