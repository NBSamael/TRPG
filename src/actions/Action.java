package actions;

import personnages.Personnage;

public abstract class Action extends ActionGenerique {

	public Action(Personnage owner, int coutPA) {
		super(owner, coutPA);
		// TODO Auto-generated constructor stub
	}

	public boolean isLegal() {
		return verifieCoutAction();
	}
	
	public abstract void getParameters();
	
	public abstract void execute();
	
}
