package data;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import actions.Action;
import personnages.Personnage;

public class InstancePartie implements ListenerEvenementJeu {

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
		this.cptTour = 0;
	}

	public void initialisePartie() {

	}

	public void lancePartie() {
		etat = InstancePartie.ETAT_EN_COURS;
		while (etat == InstancePartie.ETAT_EN_COURS) {
			cptTour++;
			System.out.println("************************************************************************");
			System.out.println("				TOUR " + cptTour);
			System.out.println("************************************************************************");
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
		for (Equipe e : equipes)
			e.reinitialisationPersonnages();
	}

	private void regenerationPointsActions() {
		if (cptTour != 1) {
			for (Equipe e : equipes)
				e.regenerationPointsActions();
		}
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
		for (Equipe e : equipes)
			e.paieCoutsEntretien();
	}

	private void calculeInitiative() {

	}

	private void phaseAction() {
		ArrayList<Personnage> persosTries = calculeOrdreAction();
		BouclePersonnages:
		for (Personnage p : persosTries) {
			System.out.println("------------------------------------------------------------------------");
			while (true) {
				System.out.println(plateau);
				for(int i = 1; i <= p.getNbPAActuels(); i++)
					System.out.print("0");
				for(int i = p.getNbPAActuels(); i < p.nbPAMax; i++)
					System.out.print("O");
				System.out.println("\t" + p.nom);
				if (p.getActionsPossibles().size() > 0) {
					int numAction = ihm.selectionnerAction(p);
					if (numAction == -1
							|| numAction >= p.getActionsPossibles().size())
						continue BouclePersonnages;
					Action a = p.getActionsPossibles().get(numAction);
					a.payeCout();
					a.getParameters();
					a.execute();
				}
				else {
					continue BouclePersonnages;
				}
			}
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

	@Override
	public boolean avantJetAttaque(EvenementJeu ej) {
		for (Equipe e : this.equipes)
			e.avantJetAttaque(ej);
		return false;
	}

	@Override
	public boolean apresJetAttaque(EvenementJeu ej) {
		for (Equipe e : this.equipes)
			e.apresJetAttaque(ej);
		return false;
	}

	@Override
	public boolean touchecritique(EvenementJeu ej) {
		for (Equipe e : this.equipes)
			e.touchecritique(ej);
		return false;
	}

	@Override
	public boolean attaqueReussie(EvenementJeu ej) {
		for (Equipe e : this.equipes)
			e.attaqueReussie(ej);
		return false;
	}

	@Override
	public boolean attaqueRatee(EvenementJeu ej) {
		for (Equipe e : this.equipes)
			e.attaqueRatee(ej);
		return false;
	}

}
