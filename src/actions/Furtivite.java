package actions;

import personnages.Personnage;
import data.Demande;

public class Furtivite extends EffetPersistant {

	public Furtivite(Personnage owner) {
		super(owner, 2);
		this.coutEntretien = 1;
		this.nom = "Furtivit�";
		this.description = "Le personnage devient dissimul�";
	}

	@Override
	public void startEffet() {
		owner.setDissimule(true);
	}

	@Override
	public void stopEffet() {
		owner.setDissimule(false);
	}

	@Override
	public boolean isLegal() {
		return (verifieCoutAction() && (!owner.isDissimule() || !owner.effetsActifs
				.contains(this)));
	}

	@Override
	public void getParameters() {
		// Pas de parametre � d�finir
		execute();
	}

	@Override
	public void setParameter(Demande reponseUtilsateur) {
		// Pas de parametre � d�finir
	}

	public void execute() {
		ExecutionAction exec = new ExecutionAction();
		exec.start();
	}

	class ExecutionAction extends Thread {
		@Override
		public void run() {
			startEffet();
			owner.effetsActifs.add(actions.Furtivite.this);
		}
	}
}
