package data;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import capacites.Capacite;
import actions.Action;
import actions.Reaction;
import attitudes.Attitude;
import personnages.Personnage;

public class Joueur implements ListenerEvenementJeu {
	public String nom;
	public List<Personnage> persosActifs;
	public List<Personnage> persosInactifs;
	public Equipe equipe;
	protected int initiativeTour;

	public Joueur(String nom) {
		super();
		this.nom = nom;
		this.persosActifs = new ArrayList<Personnage>();
		this.persosInactifs = new ArrayList<Personnage>();
	}

	public void reinitialisationPersonnages() {
		for (Personnage p : persosActifs) {
			p.reinitialisation();
		}
	}

	public void regenerationPointsActions() {
		for (Personnage p : persosActifs) {
			p.regenererPointsAction();
			for (int i = 1; i <= p.getNbPAActuels(); i++)
				System.out.print("0");
			for (int i = p.getNbPAActuels(); i < p.nbPAMax; i++)
				System.out.print("O");
			System.out.println("\t" + p.nom);
		}
	}

	public void recuperationPointsGnose() {

	}

	public void gestionMarqueursAttitude() {
		for (Personnage p : persosActifs)
			p.gestionMarqueursAttitude();
	}

	public void gestionInvocations() {

	}

	public void paieCoutsEntretien() {
		for (Personnage p : persosActifs)
			p.payerCoutsEntretien();
	}

	public int calculInitiative() {
		int bonusInit = 0;
		
		for (Personnage p : this.persosActifs)
			for (Capacite c : p.capacites)
				if (c.getType() == "Init") {
					bonusInit++;
					break;
				}
		System.out.print("\t\t");
		int valeurDes = Des.lanceDe(Des.D10);
		System.out.println("\t\tInitiative de " + this.nom + " : " + (valeurDes + bonusInit) + " (" + valeurDes + " + " + bonusInit + ")");
		return initiativeTour =valeurDes + bonusInit;
	}
	
	public int getInitiative() {
		return initiativeTour;
	}

	public boolean aPerdu() {
		boolean statut = false;
		for (Personnage p : this.persosActifs) {
			statut = statut || p.isVivant();
		}
		return !statut;
	}
	
	public void rendreInactif(Personnage p) {
		this.persosActifs.remove(p);
		this.persosInactifs.add(p);
		if (persosActifs.isEmpty())
			this.equipe.rendreInactif(this);
	}
	
	public boolean resteEncoreUnPersonnageAJouer() {
		boolean valeurRetour = false;
		for (Personnage p : this.persosActifs) {
			valeurRetour = valeurRetour || (!p.getADejaBougeDansLeTour() && !p.paralyse);
		}
		return valeurRetour;
	}
	
	public ArrayList<Personnage> getPersonnagesRestantsAJouer() {
		ArrayList<Personnage> valeurRetour = new ArrayList<Personnage>();
		for (Personnage p : this.persosActifs) {
			if (!p.getADejaBougeDansLeTour() && !p.paralyse)
				valeurRetour.add(p);
		}
		return valeurRetour;
		
	}

	private boolean gereEvenement(EvenementJeu ej) {
		try {
			String methodStr = new Exception().getStackTrace()[1].toString();
			methodStr = methodStr.substring(0, methodStr.indexOf('('));
			methodStr = methodStr.split("\\.")[methodStr.split("\\.").length - 1];
			Class<EvenementJeu> parameterTypes = EvenementJeu.class;
			ArrayList<Reaction> tmp = new ArrayList<Reaction>();

			for (Personnage p : this.persosActifs) {
				p.getClass().getMethod(methodStr, parameterTypes).invoke(p, ej);

				for (Attitude a : p.attitudes)
					if (a instanceof ListenerEvenementJeu) {
							((ListenerEvenementJeu) a).getClass()
									.getMethod(methodStr, parameterTypes)
									.invoke(a, ej);
					}

				if (!p.paralyse) {
					for (Reaction r : p.reactions)
						if ((boolean) r.getClass()
								.getMethod(methodStr, parameterTypes)
								.invoke(r, ej))
							tmp.add(r);
				}
			}

			if (tmp.size() > 0) {
				int numAction = equipe.partie.ihm.selectionnerReaction(tmp,
						this);
				if (numAction == -1 || numAction >= tmp.size())
					return false;
				Reaction r = tmp.get(numAction);
				r.payeCout();
				r.getParameters(ej);
				r.execute(ej);
			}
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
