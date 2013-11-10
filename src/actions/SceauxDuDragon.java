package actions;

import personnages.Personnage;

public class SceauxDuDragon extends EffetPersistant {

	public SceauxDuDragon(Personnage owner) {
		super(owner, 2);
		this.coutEntretien = 1;
		this.nom = "Sceaux Du Dragon";
		this.description = "Le personnage gagne Attaque, Défense et Dégâts +2 à toutes les capacités";
	}

	@Override
	public void start() {
		owner.setAttaque(owner.getAttaque() + 2);
		owner.setDefense(owner.getDefense() + 2);
		owner.setDegats(owner.getDegats() + 2);
	}

	@Override
	public void stop() {
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
	}

	@Override
	public void execute() {
		this.start();
		owner.effetsActifs.add(this);
	}

}
