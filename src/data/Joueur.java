package data;
import java.util.ArrayList;
import java.util.List;

import actions.Action;
import actions.Reaction;
import personnages.Personnage;

public class Joueur implements ListenerEvenementJeu {
	public String nom;
	public List<Personnage> persos;
	public Equipe equipe;
	
	public Joueur(String nom) {
		super();
		this.nom = nom;
		this.persos = new ArrayList<Personnage>();
	}
	
	public void reinitialisationPersonnages() {
		for(Personnage p : persos) {
			p.reinitialisation();
		}
	}

	public void regenerationPointsActions() {
		for(Personnage p : persos) {
			p.regenererPointsAction();
			for(int i = 1; i <= p.getNbPAActuels(); i++)
				System.out.print("0");
			for(int i = p.getNbPAActuels(); i < p.nbPAMax; i++)
				System.out.print("O");
			System.out.println("\t" + p.nom);
		}
	}
		

	public void recuperationPointsGnose() {

	}

	public void retraitMarqueursEtat() {

	}

	public void gestionInvocations() {

	}

	public void paieCoutsEntretien() {
		for(Personnage p : persos)
			p.payerCoutsEntretien();
	}

	public void calculeInitiative() {

	}
	
	public boolean aPerdu() {
		boolean statut = false;
		for (Personnage p : this.persos) {
			statut = statut || p.isVivant();
		}
		return !statut;
	}

	@Override
	public boolean avantJetAttaque(EvenementJeu ej) {
		ArrayList<Reaction> tmp = new ArrayList<Reaction>();
		
		for(Personnage p : this.persos)
			for (Reaction r : p.reactions)
				if (r.avantJetAttaque(ej))
					tmp.add(r);
		
		if (tmp.size() > 0) {
			int numAction = equipe.partie.ihm.selectionnerReaction(tmp, this);
			if (numAction == -1
					|| numAction >= tmp.size())
				return false;
			Reaction r = tmp.get(numAction);
			r.payeCout();
			r.getParameters(ej);
			r.execute(ej);
		}
		
		return false;
	}

	@Override
	public boolean apresJetAttaque(EvenementJeu ej) {
		ArrayList<Reaction> tmp = new ArrayList<Reaction>();
		
		for(Personnage p : this.persos)
			for (Reaction r : p.reactions)
				if (r.apresJetAttaque(ej))
					tmp.add(r);
		
		if (tmp.size() > 0) {
			int numAction = equipe.partie.ihm.selectionnerReaction(tmp, this);
			if (numAction == -1
					|| numAction >= tmp.size())
				return false;
			Reaction r = tmp.get(numAction);
			r.payeCout();
			r.getParameters(ej);
			r.execute(ej);
		}
		
		return false;
	}

	@Override
	public boolean touchecritique(EvenementJeu ej) {
		ArrayList<Reaction> tmp = new ArrayList<Reaction>();
		
		for(Personnage p : this.persos)
			for (Reaction r : p.reactions)
				if (r.touchecritique(ej))
					tmp.add(r);
		
		if (tmp.size() > 0) {
			int numAction = equipe.partie.ihm.selectionnerReaction(tmp, this);
			if (numAction == -1
					|| numAction >= tmp.size())
				return false;
			Reaction r = tmp.get(numAction);
			r.payeCout();
			r.getParameters(ej);
			r.execute(ej);
		}
		
		return false;
	}

	@Override
	public boolean attaqueReussie(EvenementJeu ej) {
		ArrayList<Reaction> tmp = new ArrayList<Reaction>();
		
		for(Personnage p : this.persos)
			for (Reaction r : p.reactions)
				if (r.attaqueReussie(ej))
					tmp.add(r);
		
		if (tmp.size() > 0) {
			int numAction = equipe.partie.ihm.selectionnerReaction(tmp, this);
			if (numAction == -1
					|| numAction >= tmp.size())
				return false;
			Reaction r = tmp.get(numAction);
			r.payeCout();
			r.getParameters(ej);
			r.execute(ej);
		}
		
		return false;
	}

	@Override
	public boolean attaqueRatee(EvenementJeu ej) {
		ArrayList<Reaction> tmp = new ArrayList<Reaction>();
		
		for(Personnage p : this.persos)
			for (Reaction r : p.reactions)
				if (r.attaqueRatee(ej))
					tmp.add(r);
		
		if (tmp.size() > 0) {
			int numAction = equipe.partie.ihm.selectionnerReaction(tmp, this);
			if (numAction == -1
					|| numAction >= tmp.size())
				return false;
			Reaction r = tmp.get(numAction);
			r.payeCout();
			r.getParameters(ej);
			r.execute(ej);
		}
		
		return false;
	}
	
}
