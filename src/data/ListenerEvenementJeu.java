package data;

public interface ListenerEvenementJeu {
	
	// Evenements Phase récupération
	public boolean avantPhaseRecuperation(EvenementJeu ej);
	public boolean apresPhaseRecuperation(EvenementJeu ej);
	
	public boolean avantReinitialisationPersonnages(EvenementJeu ej);
	public boolean apresReinitialisationPersonnages(EvenementJeu ej);
	
	public boolean avantRegenerationPointsActions(EvenementJeu ej);
	public boolean apresRegenerationPointsActions(EvenementJeu ej);
	
	public boolean avantRecuperationPointsGnose(EvenementJeu ej);
	public boolean apresRecuperationPointsGnose(EvenementJeu ej);
	
	// Evenements Phase entretien
	public boolean avantPhaseEntretien(EvenementJeu ej);
	public boolean apresPhaseEntretien(EvenementJeu ej);
	
	public boolean avantGestionMarqueursAttitude(EvenementJeu ej);
	public boolean apresGestionMarqueursAttitude(EvenementJeu ej);
	
	public boolean avantGestionInvocations(EvenementJeu ej);
	public boolean apresGestionInvocations(EvenementJeu ej);
	
	public boolean avantPaieCoutsEntretien(EvenementJeu ej);
	public boolean apresPaieCoutsEntretien(EvenementJeu ej);
	
	public boolean avantCalculeInitiative(EvenementJeu ej);
	public boolean apresCalculeInitiative(EvenementJeu ej);
	
	// Evenements Attaque
	public boolean avantJetAttaque(EvenementJeu ej);
	public boolean apresJetAttaque(EvenementJeu ej);
	
	public boolean touchecritique(EvenementJeu ej);
	
	public boolean attaqueReussie(EvenementJeu ej);
	public boolean attaqueRatee(EvenementJeu ej);
}
