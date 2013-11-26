package attitudes;

import data.EvenementJeu;
import data.ListenerEvenementJeu;
import personnages.Personnage;

public class Regenerant extends Attitude implements ListenerEvenementJeu {
	private boolean actif;

	public Regenerant(int niveau, Personnage cible) {
		super("Régénérant", "le personnage récupère 2 PV au début du tour", niveau, cible);
	}

	@Override
	public void start() {
		this.actif = true;
	}

	@Override
	public void stop() {
		this.actif = false;
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

	@Override
	public boolean avantPhaseRecuperation(EvenementJeu ej) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean apresPhaseRecuperation(EvenementJeu ej) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean avantReinitialisationPersonnages(EvenementJeu ej) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean apresReinitialisationPersonnages(EvenementJeu ej) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean avantRegenerationPointsActions(EvenementJeu ej) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean apresRegenerationPointsActions(EvenementJeu ej) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean avantRecuperationPointsGnose(EvenementJeu ej) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean apresRecuperationPointsGnose(EvenementJeu ej) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean avantPhaseEntretien(EvenementJeu ej) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean apresPhaseEntretien(EvenementJeu ej) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean avantGestionMarqueursAttitude(EvenementJeu ej) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean apresGestionMarqueursAttitude(EvenementJeu ej) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean avantGestionInvocations(EvenementJeu ej) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean apresGestionInvocations(EvenementJeu ej) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean avantPaieCoutsEntretien(EvenementJeu ej) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean apresPaieCoutsEntretien(EvenementJeu ej) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean avantCalculeInitiative(EvenementJeu ej) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean apresCalculeInitiative(EvenementJeu ej) {
		// TODO Auto-generated method stub
		return false;
	}

}
