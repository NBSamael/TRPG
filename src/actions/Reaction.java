package actions;

import data.EvenementJeu;
import data.ListenerEvenementJeu;
import personnages.Personnage;

public class Reaction extends Action implements ListenerEvenementJeu {
	protected EvenementJeu ej;

	public Reaction(Personnage owner, int coutPA) {
		super(owner, coutPA);
		this.ej = null;
	}

	@Override
	public void avantJetAttaque(EvenementJeu ej) {
		// TODO Auto-generated method stub

	}

	@Override
	public void apresJetAttaque(EvenementJeu ej) {
		// TODO Auto-generated method stub

	}

	@Override
	public void getParameters() {
		// TODO Auto-generated method stub

	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub

	}

}
