package actions;

import personnages.Personnage;
import data.InstancePartie;

public abstract class Move extends Action {

	public Move(Personnage owner, int coutPA) {
		super(owner, coutPA);
		this.typeAction = Action.TYPE_MOUVEMENT;
	}

}
