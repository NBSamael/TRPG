package attitudes;

public class Empoisonne extends Attitude {

	public Empoisonne(int niveau) {
		super("Empoisonn�", "le personnage perd 1 PV � chaque Action qu�il effectue. Ainsi, quand un personnage Empoisonn� attaque, il perd 2 PV. Les PV sont perdus d�s que l�Action est termin�e", niveau);
	}

}
