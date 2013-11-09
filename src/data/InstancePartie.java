package data;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import personnages.Personnage;

public class InstancePartie {

	public static int ETAT_PREPARATION = 0;
	public static int ETAT_EN_COURS = 1;
	public static int TERMINE = 2;

	public Terrain plateau;
	public List<Equipe> equipes;
	public Interface ihm;
	public int etat;
	public int cptTour;

	public InstancePartie(Terrain plateau, List<Equipe> equipes, Interface ihm) {
		super();
		this.plateau = plateau;
		this.equipes = equipes;
		this.ihm = ihm;
		this.etat = InstancePartie.ETAT_PREPARATION;
	}

	public void initialisePartie() {

	}

	public void lancePartie() {
		etat = InstancePartie.ETAT_EN_COURS;
		while (etat == InstancePartie.ETAT_EN_COURS) {
			phaseRecuperation();
			phaseEntretien();
			phaseAction();
		}

	}

	private void phaseRecuperation() {
		reinitialisationPersonnages();
		regenerationPointsActions();
		recuperationPointsGnose();
	}

	private void reinitialisationPersonnages() {

	}

	private void regenerationPointsActions() {
		for(Equipe e : equipes)
			e.regenerationPointsActions();
	}

	private void recuperationPointsGnose() {

	}

	private void phaseEntretien() {
		retraitMarqueursEtat();
		gestionInvocations();
		paieCoutsEntretien();
		calculeInitiative();
	}

	private void retraitMarqueursEtat() {

	}

	private void gestionInvocations() {

	}

	private void paieCoutsEntretien() {
		for(Equipe e : equipes)
			e.paieCoutsEntretien();
	}

	private void calculeInitiative() {

	}

	private void phaseAction() {
		ArrayList<Personnage> persosTries = calculeOrdreAction();
		for (Personnage p : persosTries) {
			System.out.println(plateau);
			int numAction = ihm.selectionnerAction(p);
			p.actions.get(numAction).payeCout();
			p.actions.get(numAction).getParameters();
			p.actions.get(numAction).execute();
		}
	}

	private ArrayList<Personnage> calculeOrdreAction() {
		ArrayList<Personnage> collectionTriee = new ArrayList<Personnage>();
		Set<Personnage> collectionNonTriee = new HashSet<Personnage>();
		collectionNonTriee.addAll(plateau.personnages);
		revue: for (Personnage p : collectionNonTriee) {
			int initp = p.calculInitiative();
			for (int i = 0; i < collectionTriee.size(); i++) {
				if (initp <= collectionTriee.get(i).getInitiative()) {
					collectionTriee.add(i, p);
					continue revue;
				}
			}
			collectionTriee.add(p);
		}
		return collectionTriee;
	}

}
