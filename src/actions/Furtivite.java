package actions;

import personnages.Personnage;
import data.Demande;

public class Furtivite extends EffetPersistant {

	public Furtivite(Personnage owner) {
		super(owner, 2);
		this.coutEntretien = 1;
		this.nom = "Furtivité";
		this.description = "Le personnage devient dissimulé";
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
		// Pas de parametre à définir
		execute();
	}

	@Override
	public void setParameter(Demande reponseUtilsateur) {
		// Pas de parametre à définir
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
