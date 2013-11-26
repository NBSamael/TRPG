package attitudes;

import data.BonusMalus;
import personnages.Personnage;

public class Rapide extends Attitude {
	BonusMalus bonusPARegen;

	public Rapide(int niveau, Personnage cible) {
		super("Rapide", "le personnage récupère 1 Action supplémentaire pendant la phase de Récupération", niveau, cible);
	}

	@Override
	public void start() {
		bonusPARegen = new BonusMalus(1, this);
		cible.modNbPARegen.add(bonusPARegen);
	}

	@Override
	public void stop() {
		cible.modNbPARegen.remove(bonusPARegen);
		bonusPARegen = null;
	}

}
