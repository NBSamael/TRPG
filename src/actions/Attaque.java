package actions;

import personnages.Personnage;
import data.Demande;
import data.Demande.Filtre;
import data.Demande.Type;
import data.GrilleLigneDeVue;
import data.XY;

public class Attaque extends Action {
	public Personnage cible;
	protected int bonusAttaque = 0;
	protected int nbDesLancesAttaque = 1;
	protected int bonusDegats = 0;
	protected int malusArmure = 0;
	protected int porteeAttaque = 0;

	public Attaque(Personnage owner, int coutPA, String nom,
			String description, int bonusAttaque, int nbDesLances,
			int bonusDegats, int malusArmure, int porteeAttaque) {
		super(owner, coutPA);
		this.nom = nom;
		this.description = description;
		this.bonusAttaque = bonusAttaque;
		this.nbDesLancesAttaque = nbDesLances;
		this.bonusDegats = bonusDegats;
		this.malusArmure = malusArmure;
		this.porteeAttaque = porteeAttaque;
		this.typeAction = ActionGenerique.TYPE_ATTAQUE;
	}

	@Override
	public void getParameters() {
		/*
		 * Calcul le rayon d'attaque du personnage puis demande à l'utilisateur
		 * de saisir une cible dans ce rayon
		 */
		XY caseCible = null;

		// Calcul de la portée de l'attaque : Si une portée est définie dans
		// l'attaque, on prend celle là. Sinon on prend la portée du personnage.
		int porteeAtt = 1;
		if (this.porteeAttaque > 0)
			porteeAtt = this.porteeAttaque;
		else
			porteeAtt = owner.getPortee();

		GrilleLigneDeVue possibilites = owner.partie.plateau
				.calculeGrilleLigneDeVue(owner.getPosition(), porteeAtt, true,
						false);
		// do {
		// caseCible = owner.partie.ihm
		// .selectionnerCase("Sélectionner une cible");
		// } while (owner.partie.plateau.get(caseCible) == null
		// || !possibilites.contains(caseCible)
		// || owner.partie.plateau.get(caseCible).getOccupant() == null
		// || owner.partie.plateau.get(caseCible).getOccupant()
		// .isDissimule());
		// cible = owner.partie.plateau.get(caseCible).getOccupant();

		listeDemandes.add(new Demande(this, Type.PERSO, Filtre.ATT, null,
				possibilites));
		owner.partie.ihm.addSelect(listeDemandes);
	}

	@Override
	public void setParameter(Demande reponseUtilsateur) {
		cible = reponseUtilsateur.SelectedPerso;
		listeDemandes.remove(reponseUtilsateur);
		this.execute();
	}

	public void execute() {
		ExecutionAction exec = new ExecutionAction();
		exec.start();
	}

	class ExecutionAction extends Thread {
		@Override
		public void run() {
			attaque(cible, bonusAttaque, nbDesLancesAttaque, bonusDegats);
		}
	}
}
