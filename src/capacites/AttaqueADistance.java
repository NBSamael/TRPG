package capacites;

import personnages.Personnage;

public class AttaqueADistance extends Capacite {
	public int portee;

	public AttaqueADistance(Personnage owner, int portee) {
		super(owner);
		this.type = "AD";
		this.nom = "Attaque � distance";
		this.description = "Une unit� disposant...";
		this.portee = portee;
	}
}
