package actions;

import java.util.ArrayList;

import personnages.Personnage;
import data.Demande;

public abstract class Action extends ActionGenerique {
	ArrayList<Demande> listeDemandes;

	public Action(Personnage owner, int coutPA) {
		super(owner, coutPA);
		this.listeDemandes = new ArrayList<Demande>();
	}

	public boolean isLegal() {
		return verifieCoutAction();
	}

	public abstract void getParameters();

	public abstract void setParameter(Demande reponseUtilsateur);

	public abstract void execute();

	// public void execute() {
	// ExecutionAction exec = new ExecutionAction();
	// exec.start();
	// }
	//
	// abstract class ExecutionAction extends Thread {
	// public abstract void run();
	// }

}
