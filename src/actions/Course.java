package actions;

import java.util.ArrayList;

import data.GrilleDeplacements;
import data.GrilleLigneDeVue;
import data.XY;
import personnages.Personnage;

public class Course extends Mouvement {
	private ArrayList<XY> trajet;

	public Course(Personnage owner) {
		super(owner, 2);
		this.nom = "Course";
		this.description = "Le personnage se déplace en ligne droite d'un nombre de cases égal à sa vitesse de course";
	}

	@Override
	public boolean isLegal() {
		return true;
	}

	@Override
	public void getParameters() {
		/*
		 * Calcul le rayon de deplacement du personnage puis demande à
		 * l'utilisateur de saisir une position de destination dans ce rayon
		 */
		XY destSelec = null;
		GrilleLigneDeVue possibilités = owner.partie.plateau.calculeGrilleLigneDeVue(owner.getPosition(), owner.vitesseCourse, false, true);
		System.out.println(possibilités);
		do {
			destSelec = owner.partie.ihm
					.selectionnerCase("Sélectionner une destination");
		} while (owner.partie.plateau.get(destSelec) == null
				|| !possibilités.contains(destSelec)
				|| owner.partie.plateau.get(destSelec).getOccupant() != null);
		trajet = GrilleLigneDeVue.calculerLdV(owner.partie.plateau, owner.getPosition(), destSelec);
	}

	@Override
	public void execute() {
		// Deplace le personnage case par case
		System.out.print(owner.nom + " court de " + owner.getPosition() + " à ");
		for (XY etape : trajet) {
			owner.partie.plateau.deplacePersonnage(owner, owner.getPosition(), etape);
			owner.setPositionX(etape);
		}
		System.out.println(owner.getPosition());
		owner.setADejaBougeDansLeTour(true);
	}

}
