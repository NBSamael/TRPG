package actions;

import data.Des;
import data.EvenementJeu;
import personnages.Personnage;

public class Esquive extends Reaction {
	protected int bonusEsquive = 0;
	protected int nbDesLances = 1;

	public Esquive(Personnage owner, int coutPA, String nom,
			String description, int bonusEsquive, int nbDesLances) {
		super(owner, coutPA);
		this.nom = nom;
		this.description = description;
		this.bonusEsquive = bonusEsquive;
		this.nbDesLances = nbDesLances;
	}

	@Override
	public boolean isLegal(EvenementJeu ej) {
		return (super.isLegal(ej) && ((ej.actionOrigine.typeAction == ActionGenerique.TYPE_ATTAQUE && ((Attaque) ej.actionOrigine).cible == owner) || (ej.actionOrigine.typeAction == ActionGenerique.TYPE_CHARGE && ((Charge) ej.actionOrigine).cible == owner)));
	}

	@Override
	public boolean avantJetAttaque(EvenementJeu ej) {
		return isLegal(ej);
	}

	@Override
	public void execute(EvenementJeu ej) {
		ej.esquive = true;
		ej.valeurDefenseBonus = bonusEsquive;
		ej.valeurDefenseDes = Des.lanceDes(Des.D10, nbDesLances, Des.MAX);
		System.out.println(owner.nom + " tente d'equiver "
				+ ej.actionOrigine.nom);
	}

}
