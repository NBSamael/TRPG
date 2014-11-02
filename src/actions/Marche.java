package actions;

import java.util.ArrayList;

import personnages.Personnage;
import data.Demande;
import data.Demande.Filtre;
import data.Demande.Type;
import data.GrilleDeplacements;
import data.XY;

public class Marche extends Mouvement {
	private ArrayList<XY> trajet;
	private GrilleDeplacements possibilites;

	public Marche(Personnage owner) {
		super(owner, 1);
		this.nom = "Marche";
		this.description = "Le personnage se déplace d'un nombre de cases égal à sa vitesse de marche";
	}

	@Override
	public void getParameters() {
		/*
		 * Calcul le rayon de deplacement du personnage puis demande à
		 * l'utilisateur de saisir une position de destination dans ce rayon
		 */
		XY destSelec = null;
		possibilites = owner.partie.plateau.calculeGrilleDeplacements(
				owner.getPosition(), owner.vitesseMarche);
		// System.out.println(possibilites);
		listeDemandes.add(new Demande(this, Type.CASE, Filtre.DEPL,
				possibilites, null));
		owner.partie.ihm.addSelect(listeDemandes);
	}

	@Override
	public void setParameter(Demande reponseUtilisateur) {
		trajet = possibilites.getTrajet(reponseUtilisateur.SelectedCase);
		listeDemandes.remove(reponseUtilisateur);
		this.execute();
	}

	public void execute() {
		ExecutionAction exec = new ExecutionAction();
		exec.start();
	}

	class ExecutionAction extends Thread {
		@Override
		public void run() {
			owner.partie.ihm.afficheTexte(
					owner.nom + " marche de " + owner.getPosition() + " à ",
					false);
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
			owner.partie.ihm.afficheTexte(owner.getPosition());
			owner.setADejaBougeDansLeTour(true);
		}
	}
}
