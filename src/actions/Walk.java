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
		this.description = "Le personnage se déplace d'un nombre de cases égal à sa vitesse de déplacement";
		// TODO Auto-generated constructor stub
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
		System.out.print(owner.nom + " se déplace de " + owner.getPosition() + " à ");
		for (XY etape : trajet) {
			owner.partie.plateau.deplacePersonnage(owner, owner.getPosition(), etape);
			owner.setPositionX(etape);
		}
		System.out.println(owner.getPosition());
	}

}
