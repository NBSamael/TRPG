package attitudes;

import data.BonusMalus;
import personnages.Personnage;

public class Protecteur extends Attitude {
	BonusMalus bonusArmure;

	public Protecteur(int niveau, Personnage cible) {
		super("Protecteur", "l’Armure du personnage est augmentée de 4", niveau, cible);
	}

	@Override
	public void start() {
		bonusArmure = new BonusMalus(4, this);
		cible.modArmure.add(bonusArmure);
	}

	@Override
	public void stop() {
		cible.modArmure.remove(bonusArmure);
		bonusArmure = null;
	}

}
