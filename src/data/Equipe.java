package data;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import personnages.Personnage;


public class Equipe extends ArrayList<Joueur> implements ListenerEvenementJeu {
	private String nom;
	public List<Joueur> joueursActifs;
	public List<Joueur> joueursInactifs;
	public InstancePartie partie;

	public Equipe(String nom) {
		super();
		this.nom = nom;
		joueursActifs = new ArrayList<Joueur>();
		joueursInactifs = new ArrayList<Joueur>();
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}
	
	public void reinitialisationPersonnages() {
		for(Joueur j : joueursActifs)
			j.reinitialisationPersonnages();
	}

	public void regenerationPointsActions() {
		for(Joueur j : joueursActifs)
			j.regenerationPointsActions();
	}

	public void recuperationPointsGnose() {

	}

	public void gestionMarqueursAttitude() {
		for(Joueur j : joueursActifs)
			j.gestionMarqueursAttitude();
	}

	public void gestionInvocations() {

	}

	public void paieCoutsEntretien() {
		for(Joueur j : joueursActifs)
			j.paieCoutsEntretien();
	}

	public void calculeInitiative() {

	}
	
	public boolean aPerdu() {
		return this.joueursActifs.isEmpty();
	}
	
	public void rendreInactif(Joueur j) {
		this.joueursActifs.remove(j);
		this.joueursInactifs.add(j);
	}
	
	public boolean resteEncoreUnPersonnageAJouer() {
		boolean valeurRetour = false;
		for (Joueur j : this.joueursActifs) {
			valeurRetour = valeurRetour || j.resteEncoreUnPersonnageAJouer();
		}
		return valeurRetour;
	}
	
	private boolean gereEvenement(EvenementJeu ej) {
		try {
			String methodStr = new Exception().getStackTrace()[1].toString();
			methodStr = methodStr.substring(0, methodStr.indexOf('('));
			methodStr = methodStr.split("\\.")[methodStr.split("\\.").length - 1];
			Class<EvenementJeu> parameterTypes = EvenementJeu.class;
			
			for (Joueur j : this.joueursActifs)
				j.getClass().getMethod(methodStr, parameterTypes).invoke(j, ej);
			
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
