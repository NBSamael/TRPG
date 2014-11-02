package actions;

import java.util.ArrayList;

import personnages.Personnage;
import data.Demande;
import data.Demande.Filtre;
import data.Demande.Type;
import data.GrilleLigneDeVue;
import data.XY;

public class Course extends Mouvement {
	protected ArrayList<XY> trajet;

	public Course(Personnage owner) {
		super(owner, 2);
		this.nom = "Course";
		this.description = "Le personnage se d�place en ligne droite d'un nombre de cases �gal � sa vitesse de course";
	}

	@Override
	public void getParameters() {
		/*
		 * Calcul le rayon de deplacement du personnage puis demande �
		 * l'utilisateur de saisir une position de destination dans ce rayon
		 */
		XY destSelec = null;
		GrilleLigneDeVue possibilites = owner.partie.plateau
				.calculeGrilleLigneDeVue(owner.getPosition(),
						owner.vitesseCourse, false, true);
		// System.out.println(possibilit�s);
		// do {
		// destSelec = owner.partie.ihm
		// .selectionnerCase("S�lectionner une destination");
		// } while (owner.partie.plateau.get(destSelec) == null
		// || !possibilit�s.contains(destSelec)
		// || owner.partie.plateau.get(destSelec).getOccupant() != null);
		// trajet = GrilleLigneDeVue.calculerLdV(owner.partie.plateau,
		// owner.getPosition(), destSelec);

		listeDemandes.add(new Demande(this, Type.CASE, Filtre.DEPL, null,
				possibilites));
		owner.partie.ihm.addSelect(listeDemandes);
	}

	@Override
	public void setParameter(Demande reponseUtilsateur) {
		trajet = GrilleLigneDeVue.calculerLdV(owner.partie.plateau,
				owner.getPosition(), reponseUtilsateur.SelectedCase);
		this.execute();
	}

	public void execute() {
		ExecutionAction exec = new ExecutionAction();
		exec.start();
	}

	class ExecutionAction extends Thread {
		@Override
		public void run() {
			System.out.print(owner.nom + " court de " + owner.getPosition()
					+ " � ");
			for (XY etape : trajet) {
				owner.partie.deplacePersonnage(owner, owner.getPosition(),
						etape);
				owner.setPosition(etape);
				try {
					Thread.sleep(300);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			System.out.println(owner.getPosition());
			owner.setADejaBougeDansLeTour(true);
		}
	}
}
