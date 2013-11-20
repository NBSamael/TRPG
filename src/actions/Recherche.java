package actions;

import java.util.HashSet;

import data.Des;
import data.GrilleLigneDeVue;
import data.XY;
import personnages.Personnage;

public class Recherche extends Action {
	protected int porteeRecherche;
	protected int bonusRecherche;

	public Recherche(Personnage owner, int coutPA, String nom,
			String description, int porteeRecherche, int bonusRecherche) {
		super(owner, coutPA);
		this.nom = nom;
		this.description = description;
		this.porteeRecherche = porteeRecherche;
		this.bonusRecherche = bonusRecherche;
		this.typeAction = ActionGenerique.TYPE_RECHERCHE;
	}

	@Override
	public void getParameters() {
		// Pas de paramètre
	}

	@Override
	public void execute() {
		int porteeRec = 1;
		if (this.porteeRecherche > 0)
			porteeRec = this.porteeRecherche;
		else
			porteeRec = owner.getTailleZoneControle();

		GrilleLigneDeVue possibilités = owner.partie.plateau
				.calculeGrilleLigneDeVue(owner.getPosition(), porteeRec, true, false);
		
		int valeurRechercheDes = Des.lanceDe(Des.D10);
		
		int valeurBonusRodeur = 0;
		if (owner.getCategorie() == Personnage.CATEGORIE_RODEUR)
			valeurBonusRodeur = 1;
		
		for(XY tmp : possibilités) {
			Personnage p = owner.partie.plateau.get(tmp).getOccupant();
			if (p != null && p.isDissimule()) {
				
				int valeurMalusCouvert = 0; // A implémenter
				
				if (valeurRechercheDes >= (7 - bonusRecherche - valeurBonusRodeur + valeurMalusCouvert)) {
					for (EffetPersistant ep : new HashSet<EffetPersistant>(p.effetsActifs)) {
						if (ep.nom == "Furtivité") {
							p.effetsActifs.remove(ep);
							break;
						}
					}
					p.setDissimule(false);
					System.out.println(p.nom + " a été révélé");
				}
			}
		}
	}

}
