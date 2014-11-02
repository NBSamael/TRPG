package data;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import personnages.Personnage;
import actions.Action;

public class InstancePartie implements ListenerEvenementJeu {

	public static int ETAT_PREPARATION = 0;
	public static int ETAT_EN_COURS = 1;
	public static int TERMINE = 2;

	public Terrain plateau;
	public List<Equipe> equipes;
	ArrayList<Joueur> joueursTriesTour;
	public Interface ihm;
	public int etat;
	public int cptTour;

	public InstancePartie(Terrain plateau, List<Equipe> equipes, Interface ihm) {
		super();
		this.plateau = plateau;
		this.equipes = equipes;
		joueursTriesTour = new ArrayList<Joueur>();
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
			System.out
					.println("************************************************************************");
			System.out.println("				TOUR " + cptTour);
			System.out
					.println("************************************************************************");
			System.out.println("> Phase de Récupération");
			this.avantPhaseRecuperation(new EvenementJeu(null));
			phaseRecuperation();
			this.apresPhaseRecuperation(new EvenementJeu(null));
			System.out.println("> Phase d'Entretien");
			this.avantPhaseEntretien(new EvenementJeu(null));
			phaseEntretien();
			this.apresPhaseEntretien(new EvenementJeu(null));
			System.out.println("> Phase d'Action");
			phaseAction();
		}

		for (Equipe e : equipes)
			if (!e.aPerdu()) {
				System.out.println(e.getNom() + " gagne la partie");
			} else {
				System.out.println(e.getNom() + " perd la partie");
			}
	}

	private void phaseRecuperation() {
		System.out.println("\t> Réinitialisation des Personnages");
		this.avantReinitialisationPersonnages(new EvenementJeu(null));
		reinitialisationPersonnages();
		this.apresReinitialisationPersonnages(new EvenementJeu(null));

		System.out.println("\t> Récupération des Points d'Actions");
		this.avantRegenerationPointsActions(new EvenementJeu(null));
		regenerationPointsActions();
		this.apresRegenerationPointsActions(new EvenementJeu(null));

		System.out.println("\t> Récupération des Points de Gnose");
		this.avantRecuperationPointsGnose(new EvenementJeu(null));
		recuperationPointsGnose();
		this.apresRecuperationPointsGnose(new EvenementJeu(null));
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
		System.out.println("\t> Gestion des Marqueurs Attitude");
		this.avantGestionMarqueursAttitude(new EvenementJeu(null));
		gestionMarqueursAttitude();
		this.apresGestionMarqueursAttitude(new EvenementJeu(null));

		System.out.println("\t> Gestion des Invocations");
		this.avantGestionInvocations(new EvenementJeu(null));
		gestionInvocations();
		this.apresGestionInvocations(new EvenementJeu(null));

		System.out.println("\t> Paiement des Coûts d'Entretien");
		this.avantPaieCoutsEntretien(new EvenementJeu(null));
		paieCoutsEntretien();
		this.apresPaieCoutsEntretien(new EvenementJeu(null));

		System.out.println("\t> Calcul de l'Initiative");
		this.avantCalculeInitiative(new EvenementJeu(null));
		calculeInitiative();
		this.apresCalculeInitiative(new EvenementJeu(null));
	}

	private void gestionMarqueursAttitude() {
		for (Equipe e : equipes)
			e.gestionMarqueursAttitude();
	}

	private void gestionInvocations() {

	}

	private void paieCoutsEntretien() {
		for (Equipe e : equipes)
			e.paieCoutsEntretien();
	}

	private void calculeInitiative() {
		joueursTriesTour = new ArrayList<Joueur>();
		for (Equipe e : this.equipes) {
			revue: for (Joueur j : e.joueursActifs) {
				int initp = j.calculInitiative();
				for (int i = 0; i < joueursTriesTour.size(); i++) {
					if (initp > joueursTriesTour.get(i).getInitiative()) {
						joueursTriesTour.add(i, j);
						continue revue;
					}
				}
				joueursTriesTour.add(j);
			}
		}
	}

	public boolean resteEncoreUnPersonnageAJouer() {
		boolean valeurRetour = false;
		for (Equipe e : this.equipes) {
			valeurRetour = valeurRetour || e.resteEncoreUnPersonnageAJouer();
		}
		return valeurRetour;
	}

	private void phaseAction() {
		while (resteEncoreUnPersonnageAJouer()) {
			BoucleJoueurs: for (Joueur j : joueursTriesTour) {
				if (j.resteEncoreUnPersonnageAJouer()) {
					System.out
							.println("------------------------------------------------------------------------");
					System.out.println("Joueur : " + j.nom);
					Personnage p = null;
					while ((p = ihm.selectionnerPersonnage(j
							.getPersonnagesRestantsAJouer())) == null) {
						System.out
								.println("Vous devez sélectionner un personnage!");
					}

					while (true) {
						System.out.println(plateau);
						for (int i = 1; i <= p.getNbPAActuels(); i++)
							System.out.print("0");
						for (int i = p.getNbPAActuels(); i < p.nbPAMax; i++)
							System.out.print("O");
						System.out.println("\t" + p.nom);
						if (p.getActionsPossibles().size() > 0) {
							int numAction = ihm.selectionnerAction(p);
							if (numAction == -1
									|| numAction >= p.getActionsPossibles()
											.size()) {
								p.setADejaBougeDansLeTour(true);
								continue BoucleJoueurs;
							}
							Action a = p.getActionsPossibles().get(numAction);
							a.payeCout();
							a.getParameters();
							a.execute();

							for (Equipe e : equipes) {
								if (e.aPerdu()) {
									etat = InstancePartie.TERMINE;
									return;
								}
							}
						} else {
							p.setADejaBougeDansLeTour(true);
							continue BoucleJoueurs;
						}
					}
				}
			}
		}

		/*
		 * ArrayList<Personnage> persosTries = calculeOrdreAction();
		 * BouclePersonnages: for (Personnage p : persosTries) {
		 * System.out.println(
		 * "------------------------------------------------------------------------"
		 * ); while (true) { System.out.println(plateau); for(int i = 1; i <=
		 * p.getNbPAActuels(); i++) System.out.print("0"); for(int i =
		 * p.getNbPAActuels(); i < p.nbPAMax; i++) System.out.print("O");
		 * System.out.println("\t" + p.nom); if (p.getActionsPossibles().size()
		 * > 0) { int numAction = ihm.selectionnerAction(p); if (numAction == -1
		 * || numAction >= p.getActionsPossibles().size()) continue
		 * BouclePersonnages; Action a = p.getActionsPossibles().get(numAction);
		 * a.payeCout(); a.getParameters(); a.execute();
		 * 
		 * for(Equipe e : equipes) { if (e.aPerdu()) { etat =
		 * InstancePartie.TERMINE; return; } } } else { continue
		 * BouclePersonnages; } } }
		 */
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

	public void deplacePersonnage(Personnage p, XY anciennePosition,
			XY nouvellePosition) {
		this.plateau.get(anciennePosition).setOccupant(null);
		this.plateau.get(nouvellePosition).setOccupant(p);
		ihm.refreshPlateau();
	}

	private boolean gereEvenement(EvenementJeu ej) {
		try {
			String methodStr = new Exception().getStackTrace()[1].toString();
			methodStr = methodStr.substring(0, methodStr.indexOf('('));
			methodStr = methodStr.split("\\.")[methodStr.split("\\.").length - 1];
			Class<EvenementJeu> parameterTypes = EvenementJeu.class;

			for (Equipe e : this.equipes)
				e.getClass().getMethod(methodStr, parameterTypes).invoke(e, ej);

		} catch (IllegalAccessException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IllegalArgumentException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (InvocationTargetException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (NoSuchMethodException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (SecurityException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		return false;
	}

	@Override
	public boolean avantJetAttaque(EvenementJeu ej) {
		return this.gereEvenement(ej);
	}

	@Override
	public boolean apresJetAttaque(EvenementJeu ej) {
		return this.gereEvenement(ej);
	}

	@Override
	public boolean touchecritique(EvenementJeu ej) {
		return this.gereEvenement(ej);
	}

	@Override
	public boolean attaqueReussie(EvenementJeu ej) {
		return this.gereEvenement(ej);
	}

	@Override
	public boolean attaqueRatee(EvenementJeu ej) {
		return this.gereEvenement(ej);
	}

	@Override
	public boolean avantPhaseRecuperation(EvenementJeu ej) {
		return this.gereEvenement(ej);
	}

	@Override
	public boolean apresPhaseRecuperation(EvenementJeu ej) {
		return this.gereEvenement(ej);
	}

	@Override
	public boolean avantReinitialisationPersonnages(EvenementJeu ej) {
		return this.gereEvenement(ej);
	}

	@Override
	public boolean apresReinitialisationPersonnages(EvenementJeu ej) {
		return this.gereEvenement(ej);
	}

	@Override
	public boolean avantRegenerationPointsActions(EvenementJeu ej) {
		return this.gereEvenement(ej);
	}

	@Override
	public boolean apresRegenerationPointsActions(EvenementJeu ej) {
		return this.gereEvenement(ej);
	}

	@Override
	public boolean avantRecuperationPointsGnose(EvenementJeu ej) {
		return this.gereEvenement(ej);
	}

	@Override
	public boolean apresRecuperationPointsGnose(EvenementJeu ej) {
		return this.gereEvenement(ej);
	}

	@Override
	public boolean avantPhaseEntretien(EvenementJeu ej) {
		return this.gereEvenement(ej);
	}

	@Override
	public boolean apresPhaseEntretien(EvenementJeu ej) {
		return this.gereEvenement(ej);
	}

	@Override
	public boolean avantGestionMarqueursAttitude(EvenementJeu ej) {
		return this.gereEvenement(ej);
	}

	@Override
	public boolean apresGestionMarqueursAttitude(EvenementJeu ej) {
		return this.gereEvenement(ej);
	}

	@Override
	public boolean avantGestionInvocations(EvenementJeu ej) {
		return this.gereEvenement(ej);
	}

	@Override
	public boolean apresGestionInvocations(EvenementJeu ej) {
		return this.gereEvenement(ej);
	}

	@Override
	public boolean avantPaieCoutsEntretien(EvenementJeu ej) {
		return this.gereEvenement(ej);
	}

	@Override
	public boolean apresPaieCoutsEntretien(EvenementJeu ej) {
		return this.gereEvenement(ej);
	}

	@Override
	public boolean avantCalculeInitiative(EvenementJeu ej) {
		return this.gereEvenement(ej);
	}

	@Override
	public boolean apresCalculeInitiative(EvenementJeu ej) {
		return this.gereEvenement(ej);
	}

}
