package attitudes;

public class Empoisonne extends Attitude {

	public Empoisonne(int niveau) {
		super("Empoisonné", "le personnage perd 1 PV à chaque Action qu’il effectue. Ainsi, quand un personnage Empoisonné attaque, il perd 2 PV. Les PV sont perdus dès que l’Action est terminée", niveau);
	}

}
