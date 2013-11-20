package actions;

import personnages.Personnage;
import data.InstancePartie;

public abstract class Mouvement extends Action {

	public Mouvement(Personnage owner, int coutPA) {
		super(owner, coutPA);
		this.typeAction = ActionGenerique.TYPE_MOUVEMENT;
	}
	
	@Override
	public boolean isLegal() {
		return (verifieCoutAction() && !owner.getADejaBougeDansLeTour());
	}

}
