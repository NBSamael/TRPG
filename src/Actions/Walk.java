package Actions;

import java.util.ArrayList;

import Data.InstancePartie;
import Data.Personnage;
import Data.TerrainDistance;
import Data.XY;

public class Walk extends Move {
	private ArrayList<XY> trajet;

	public Walk(Personnage owner, InstancePartie partie) {
		super(owner, partie);
		this.nom = "Deplacement";
		this.description = "Le personnage se d�place d'un nombre de cases �gal � sa vitesse de d�placement";
		// TODO Auto-generated constructor stub
	}

	@Override
	public void getParameters() {
		/*
		 * Calcul le rayon de deplacement du personnage puis demande �
		 * l'utilisateur de saisir une position de destination dans ce rayon
		 */
		XY destSelec = null;
		TerrainDistance possibilit�s = partie.plateau.calculeTerrainDistance(
				owner.getPosition(), owner.vitesseDeplacement);
		do {
			destSelec = partie.ihm
					.selectionnerCase("S�lectionner une destination");
		} while (partie.plateau.get(destSelec) == null
				|| possibilit�s.get(destSelec) == null);
		trajet = possibilit�s.getTrajet(destSelec);
	}

	@Override
	public void execute() {
		// Deplace le personnage case par case
		for (XY etape : trajet) {
			partie.plateau.deplacePersonnage(owner, owner.getPosition(), etape);
			owner.setPositionX(etape);
		}
	}

}
