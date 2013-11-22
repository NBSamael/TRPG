package attitudes;

public class Berserk extends Attitude {

	public Berserk(int niveau) {
		super("Berserk", "un personnage affecté par l’Attitude Berserk reçoit immédiatement +1 en Attaque et +1 en Dégâts. Toutefois, lors de son activation, le personnage doit obligatoirement Charger (si ses points d’Action le lui permettent) contre le personnage ennemi le plus proche et utiliser toutes ses Actions en Attaque. Il ne peut utiliser aucune action de Défense", niveau);
	}

}
