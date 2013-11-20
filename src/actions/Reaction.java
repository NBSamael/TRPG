package actions;

import data.EvenementJeu;
import data.ListenerEvenementJeu;
import personnages.Personnage;

public abstract class Reaction extends ActionGenerique implements ListenerEvenementJeu {

	public Reaction(Personnage owner, int coutPA) {
		super(owner, coutPA);
	}
	
	public boolean isLegal(EvenementJeu ej) {
		return verifieCoutAction();
	}

	public void getParameters(EvenementJeu ej) {
		// TODO Auto-generated method stub

	}

	public void execute(EvenementJeu ej) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean avantJetAttaque(EvenementJeu ej) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean apresJetAttaque(EvenementJeu ej) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchecritique(EvenementJeu ej) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean attaqueReussie(EvenementJeu ej) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean attaqueRatee(EvenementJeu ej) {
		// TODO Auto-generated method stub
		return false;
	}
}
