package actions;

import personnages.Personnage;
import data.InstancePartie;

public abstract class Attack extends Action {

	public Attack(Personnage owner, int coutPA) {
		super(owner, coutPA);
		this.typeAction = Action.TYPE_ATTAQUE;
	}
}
