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
	ArrayList<Demande> listeDemandes;

	public Marche(Personnage owner) {
		super(owner, 1);
		this.listeDemandes = new ArrayList<Demande>();
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
		System.out.println(possibilites);
		listeDemandes.add(new Demande(this, Type.CASE, Filtre.DEPL,
				possibilites));
		owner.partie.ihm.addSelect(listeDemandes);

		// do {
		// destSelec = owner.partie.ihm
		// .selectionnerCase("Sélectionner une destination");
		// } while (owner.partie.plateau.get(destSelec) == null
		// || possibilites.get(destSelec) == null);
		// trajet = possibilites.getTrajet(destSelec);
	}

	@Override
	public void setParameter(Demande reponseUtilisateur) {
		trajet = possibilites.getTrajet(reponseUtilisateur.SelectedCase);
		listeDemandes.remove(reponseUtilisateur);
		this.execute();
	}

	@Override
	public void execute() {
		ExecutionAction exec = new ExecutionAction();
		exec.start();
	}

	class ExecutionAction extends Thread {
		@Override
		public void run() {
			System.out.print(owner.nom + " marche de " + owner.getPosition()
					+ " à ");
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
