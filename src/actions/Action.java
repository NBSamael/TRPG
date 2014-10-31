package actions;

import java.util.ArrayList;

import personnages.Personnage;
import data.Demande;

public abstract class Action extends ActionGenerique {

	public Action(Personnage owner, int coutPA) {
		super(owner, coutPA);
		// TODO Auto-generated constructor stub
	}

	public boolean isLegal() {
		return verifieCoutAction();
	}

	public abstract void getParameters();

	public void setParameters(ArrayList<Demande> listeDemandes) {

	}

	public abstract void execute();

}
