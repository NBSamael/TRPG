package attitudes;

import personnages.Personnage;

public class Berserk extends Attitude {

	public Berserk(int niveau, Personnage cible) {
		super("Berserk", "un personnage affecté par l’Attitude Berserk reçoit immédiatement +1 en Attaque et +1 en Dégâts. Toutefois, lors de son activation, le personnage doit obligatoirement Charger (si ses points d’Action le lui permettent) contre le personnage ennemi le plus proche et utiliser toutes ses Actions en Attaque. Il ne peut utiliser aucune action de Défense", niveau, cible);
	}

	@Override
	public void start() {
		cible.berserk = true;
	}

	@Override
	public void stop() {
		cible.berserk = false;
	}

}
