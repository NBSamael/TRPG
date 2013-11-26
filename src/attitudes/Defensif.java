package attitudes;

import data.BonusMalus;
import personnages.Personnage;

public class Defensif extends Attitude {
	private BonusMalus bonusResistance;

	public Defensif(int niveau, Personnage cible) {
		super("Défensif", "la Résistance du personnage est augmentée de 4", niveau, cible);
	}

	@Override
	public void start() {
		bonusResistance = new BonusMalus(4, this);
		cible.modResistance.add(bonusResistance);
	}

	@Override
	public void stop() {
		cible.modResistance.remove(bonusResistance);
		bonusResistance = null;
	}

}
