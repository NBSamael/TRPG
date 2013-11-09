package actions;

import java.util.ArrayList;

import personnages.Personnage;
import data.GrilleDeplacements;
import data.XY;

public class Walk extends Move {
	private ArrayList<XY> trajet;

	public Walk(Personnage owner) {
		super(owner, 1);
		this.nom = "Deplacement";
		this.description = "Le personnage se d�place d'un nombre de cases �gal � sa vitesse de d�placement";
		// TODO Auto-generated constructor stub
	}
	

	@Override
	public boolean isLegal() {
		// TODO Impl�menter le syst�me d'engagement au combat
		return true;
	}


	@Override
	public void getParameters() {
		/*
		 * Calcul le rayon de deplacement du personnage puis demande �
		 * l'utilisateur de saisir une position de destination dans ce rayon
		 */
		XY destSelec = null;
		GrilleDeplacements possibilit�s = owner.partie.plateau.calculeGrilleDeplacements(
				owner.getPosition(), owner.vitesseMarche);
		do {
			destSelec = owner.partie.ihm
					.selectionnerCase("S�lectionner une destination");
		} while (owner.partie.plateau.get(destSelec) == null
				|| possibilit�s.get(destSelec) == null);
		trajet = possibilit�s.getTrajet(destSelec);
	}

	@Override
	public void execute() {
		// Deplace le personnage case par case
		System.out.print(owner.nom + " se d�place de " + owner.getPosition() + " � ");
		for (XY etape : trajet) {
			owner.partie.plateau.deplacePersonnage(owner, owner.getPosition(), etape);
			owner.setPositionX(etape);
		}
		System.out.println(owner.getPosition());
	}

}
