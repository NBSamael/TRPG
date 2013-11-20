package data;

public interface ListenerEvenementJeu {
	public boolean avantJetAttaque(EvenementJeu ej);
	
	public boolean apresJetAttaque(EvenementJeu ej);
	
	public boolean touchecritique(EvenementJeu ej);
	
	public boolean attaqueReussie(EvenementJeu ej);
	
	public boolean attaqueRatee(EvenementJeu ej);
}
