package capacites;

import personnages.Personnage;

public class AttaqueADistance extends Capacite {
	public int portee;

	public AttaqueADistance(Personnage owner, int portee) {
		super(owner);
		this.type = "AD";
		this.nom = "Attaque à distance";
		this.description = "Une unité disposant...";
		this.portee = portee;
	}
}
