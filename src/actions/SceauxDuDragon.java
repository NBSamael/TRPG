package actions;

import personnages.Personnage;
import data.Demande;

public class SceauxDuDragon extends EffetPersistant {

	public SceauxDuDragon(Personnage owner) {
		super(owner, 2);
		this.coutEntretien = 1;
		this.nom = "Sceaux Du Dragon";
		this.description = "Le personnage gagne Attaque, Défense et Dégâts +2 à toutes les capacités";
	}

	@Override
	public void startEffet() {
		owner.setAttaque(owner.getAttaque() + 2);
		owner.setDefense(owner.getDefense() + 2);
		owner.setDegats(owner.getDegats() + 2);
	}

	@Override
	public void stopEffet() {
		owner.setAttaque(owner.getAttaque() - 2);
		owner.setDefense(owner.getDefense() - 2);
		owner.setDegats(owner.getDegats() - 2);
	}

	@Override
	public boolean isLegal() {
		return !owner.effetsActifs.contains(this);
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
			owner.effetsActifs.add(actions.SceauxDuDragon.this);
		}
	}
}
