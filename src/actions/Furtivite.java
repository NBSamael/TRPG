package actions;

import personnages.Personnage;

public class Furtivite extends EffetPersistant {

	public Furtivite(Personnage owner) {
		super(owner, 2);
		this.coutEntretien = 1;
		this.nom = "Furtivité";
		this.description = "Le personnage devient dissimulé";
	}

	@Override
	public void start() {
		owner.setDissimule(true);
	}

	@Override
	public void stop() {
		owner.setDissimule(false);
	}

	@Override
	public boolean isLegal() {
		return (verifieCoutAction() && (!owner.isDissimule() || !owner.effetsActifs.contains(this)));
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
