package attitudes;

import personnages.Personnage;

public class Paralyse extends Attitude {

	public Paralyse(int niveau, Personnage cible) {
		super("Paralysé", "le personnage est immobilisé et ne peut effectuer aucune Action pendant le tour", niveau, cible);
	}

	@Override
	public void start() {
		cible.paralyse = true;
	}

	@Override
	public void stop() {
		cible.paralyse = false;
	}
	
}
