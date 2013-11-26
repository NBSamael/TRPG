package attitudes;

import personnages.Personnage;

public class Bloque extends Attitude {

	public Bloque(int niveau, Personnage cible) {
		super("Bloqu�", "le personnage ne peut plus utiliser de Capacit�s Sp�ciales", niveau, cible);
	}

	@Override
	public void start() {
		cible.bloque = true;
	}

	@Override
	public void stop() {
		cible.bloque = false;
	}
	
}
