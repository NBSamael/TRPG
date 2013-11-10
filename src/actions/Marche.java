package actions;

import java.util.ArrayList;

import personnages.Personnage;
import data.GrilleDeplacements;
import data.XY;

public class Marche extends Mouvement {
	private ArrayList<XY> trajet;

	public Marche(Personnage owner) {
		super(owner, 1);
		this.nom = "Marche";
		this.description = "Le personnage se déplace d'un nombre de cases égal à sa vitesse de marche";
	}
	

	@Override
	public boolean isLegal() {
		// TODO Implémenter le système d'engagement au combat
		return true;
	}


	@Override
	public void getParameters() {
		/*
		 * Calcul le rayon de deplacement du personnage puis demande à
		 * l'utilisateur de saisir une position de destination dans ce rayon
		 */
		XY destSelec = null;
		GrilleDeplacements possibilités = owner.partie.plateau.calculeGrilleDeplacements(
				owner.getPosition(), owner.vitesseMarche);
		System.out.println(possibilités);
		do {
			destSelec = owner.partie.ihm
					.selectionnerCase("Sélectionner une destination");
		} while (owner.partie.plateau.get(destSelec) == null
				|| possibilités.get(destSelec) == null);
		trajet = possibilités.getTrajet(destSelec);
	}

	@Override
	public void execute() {
		// Deplace le personnage case par case
		System.out.print(owner.nom + " marche de " + owner.getPosition() + " à ");
		for (XY etape : trajet) {
			owner.partie.plateau.deplacePersonnage(owner, owner.getPosition(), etape);
			owner.setPositionX(etape);
		}
		System.out.println(owner.getPosition());
		owner.setADejaBougeDansLeTour(true);
	}

}
