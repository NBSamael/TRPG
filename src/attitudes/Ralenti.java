package attitudes;

import data.BonusMalus;
import personnages.Personnage;

public class Ralenti extends Attitude {
	BonusMalus malusPARegen;

	public Ralenti(int niveau, Personnage cible) {
		super("Ralenti", "le personnage récupère 1 Action de moins lors de la phase de Récupération", niveau, cible);
	}

	@Override
	public void start() {
		malusPARegen = new BonusMalus(-1, this);
		cible.modNbPARegen.add(malusPARegen);
	}

	@Override
	public void stop() {
		cible.modNbPARegen.remove(malusPARegen);
		malusPARegen = null;
	}
	
}
