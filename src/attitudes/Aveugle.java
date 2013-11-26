package attitudes;

import data.BonusMalus;
import personnages.Personnage;

public class Aveugle extends Attitude {
	private BonusMalus malusAttaque;

	public Aveugle(int niveau, Personnage cible) {
		super("Aveuglé", "le personnage subit un malus de 4 à l’attaque", niveau, cible);
	}

	@Override
	public void start() {
		malusAttaque = new BonusMalus(-4, this);
		cible.modAttaque.add(malusAttaque);
	}

	@Override
	public void stop() {
		cible.modAttaque.remove(malusAttaque);
		malusAttaque = null;
	}
}
