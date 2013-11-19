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
	
	public ArrayList<Reaction> getReactionsPossibles(EvenementJeu ej) {
		ArrayList<Reaction> tmp = new ArrayList<Reaction>();
		
		for (Reaction r : ej.reactionsPossibles)
			if (r.getOwner().owner == this)
				tmp.add(r);
		
		return tmp;
	}

	@Override
	public void avantJetAttaque(EvenementJeu ej) {
		for(Personnage p : this.persos)
			p.avantJetAttaque(ej);
		
		if (getReactionsPossibles(ej).size() > 0) {
			int numAction = equipe.partie.ihm.selectionnerReaction(ej, this);
			if (numAction == -1
					|| numAction >= getReactionsPossibles(ej).size())
				return;
			Reaction r = getReactionsPossibles(ej).get(numAction);
			r.payeCout();
			r.getParameters();
			r.execute();
		}
	}

	@Override
	public void apresJetAttaque(EvenementJeu ej) {
		for(Personnage p : this.persos)
			p.apresJetAttaque(ej);
	}
}
