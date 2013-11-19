package data;
import java.util.ArrayList;
import java.util.List;


public class Equipe extends ArrayList<Joueur> implements ListenerEvenementJeu {
	private String nom;
	public List<Joueur> joueurs;
	public InstancePartie partie;

	public Equipe(String nom) {
		super();
		this.nom = nom;
		joueurs = new ArrayList<Joueur>();
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}
	
	public void reinitialisationPersonnages() {
		for(Joueur j : joueurs)
			j.reinitialisationPersonnages();
	}

	public void regenerationPointsActions() {
		for(Joueur j : joueurs)
			j.regenerationPointsActions();
	}

	public void recuperationPointsGnose() {

	}

	public void retraitMarqueursEtat() {

	}

	public void gestionInvocations() {

	}

	public void paieCoutsEntretien() {
		for(Joueur j : joueurs)
			j.paieCoutsEntretien();
	}

	public void calculeInitiative() {

	}

	@Override
	public void avantJetAttaque(EvenementJeu ej) {
		for (Joueur j : this.joueurs)
			j.avantJetAttaque(ej);
	}

	@Override
	public void apresJetAttaque(EvenementJeu ej) {
		for (Joueur j : this.joueurs)
			j.apresJetAttaque(ej);
	}
}
