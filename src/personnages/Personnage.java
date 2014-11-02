package personnages;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import actions.Action;
import actions.EffetPersistant;
import actions.Reaction;
import attitudes.Attitude;
import capacites.AttaqueADistance;
import capacites.Capacite;
import data.BonusMalus;
import data.EvenementJeu;
import data.InstancePartie;
import data.Joueur;
import data.ListenerEvenementJeu;
import data.XY;

public abstract class Personnage implements ListenerEvenementJeu {
	public static int CATEGORIE_COMBATTANT = 0;
	public static int CATEGORIE_MYSTIQUE = 1;
	public static int CATEGORIE_RODEUR = 2;

	public static int FACTION_LUMIERE = 0;
	public static int FACTION_OBSCURITE = 1;
	public static int FACTION_NEUTRE = 2;

	public InstancePartie partie;
	public Joueur owner;

	public String nom;
	public XY position;
	protected boolean dissimule;
	protected boolean vivant;

	protected int attaque;
	public List<BonusMalus> modAttaque;

	protected int degats;
	public List<BonusMalus> modDegats;

	protected int defense;
	public List<BonusMalus> modDefense;

	protected int armure;
	public List<BonusMalus> modArmure;

	public int nbPVMax;
	public int nbPVActuel;

	protected int resistance;
	public List<BonusMalus> modResistance;

	public int vitesseMarche;
	public int vitesseCourse;

	public int nbPAMax;
	public int nbPARegen;
	public int nbPAActuels;
	public List<BonusMalus> modNbPARegen;

	protected int categorie;
	// protected Organisation organisation; -- A Implementer
	protected int faction;

	protected int niveau;

	public List<Capacite> capacites;
	public List<Action> actions;
	public List<Reaction> reactions;
	public List<EffetPersistant> effetsActifs;
	public List<Attitude> attitudes;

	protected int initBase;
	protected int initiativeTour;
	protected boolean aDejaBougeDansLeTour;

	protected int tailleZoneControle;

	public boolean bloque;
	public boolean paralyse;
	public boolean berserk;

	public String sprite;

	public Personnage(Joueur owner) {
		super();
		this.owner = owner;
		this.modAttaque = new ArrayList<BonusMalus>();
		this.modDefense = new ArrayList<BonusMalus>();
		this.modDegats = new ArrayList<BonusMalus>();
		this.modArmure = new ArrayList<BonusMalus>();
		this.modResistance = new ArrayList<BonusMalus>();
		this.modNbPARegen = new ArrayList<BonusMalus>();
		this.actions = new ArrayList<Action>();
		this.reactions = new ArrayList<Reaction>();
		this.capacites = new ArrayList<Capacite>();
		this.effetsActifs = new ArrayList<EffetPersistant>();
		this.attitudes = new ArrayList<Attitude>();
		this.position = null;
		this.partie = null;
		this.dissimule = false;
		this.aDejaBougeDansLeTour = false;
		this.tailleZoneControle = 8;
		this.vivant = true;
		this.bloque = false;
		this.paralyse = false;
		this.berserk = false;
	}

	public XY getPosition() {
		return position;
	}

	public void setPosition(XY position) {
		this.position = position;
	}

	public int getPointsAction() {
		return nbPAActuels;
	}

	public void setPointsAction(int pointsAction) {
		this.nbPAActuels = pointsAction;
	}

	public int getInitBase() {
		return this.initBase;
	}

	public int calculInitiative() {
		return initiativeTour = initBase + 10;
	}

	public int getInitiative() {
		return initiativeTour;
	}

	public int getAttaque() {
		return attaque;
	}

	public void setAttaque(int attaque) {
		this.attaque = attaque;
	}

	public int getDegats() {
		return degats;
	}

	public void setDegats(int degats) {
		this.degats = degats;
	}

	public int getDefense() {
		return defense;
	}

	public void setDefense(int defense) {
		this.defense = defense;
	}

	public int getArmure() {
		return armure;
	}

	public int getResistance() {
		return resistance;
	}

	public void setResistance(int resistance) {
		this.resistance = resistance;
	}

	public int getNbPAActuels() {
		return nbPAActuels;
	}

	public void setNbPAActuels(int nbPAActuels) {
		this.nbPAActuels = nbPAActuels;
	}

	public void ajoutAttitude(Attitude ajout) {
		for (Attitude a : attitudes) {
			if (a.equals(ajout)) {
				if (a.getNiveau() < ajout.getNiveau())
					a.setNiveau(ajout.getNiveau());
				return;
			}
		}
		attitudes.add(ajout);
		ajout.start();
	}

	public void gestionMarqueursAttitude() {
		Set<Attitude> ensembleAttitudes = new HashSet<Attitude>(attitudes);
		for (Attitude a : ensembleAttitudes) {
			a.setNiveau(a.getNiveau() - 1);
			if (a.getNiveau() == 0) {
				attitudes.remove(a);
			}
		}
	}

	public boolean isDissimule() {
		return dissimule;
	}

	public void setDissimule(boolean dissimule) {
		this.dissimule = dissimule;
	}

	public boolean isVivant() {
		return vivant;
	}

	public void setVivant(boolean vivant) {
		this.vivant = vivant;
	}

	public void recoitBlessure(int valeurBlessure) {
		nbPVActuel = nbPVActuel - valeurBlessure;
		if (nbPVActuel < 0) {
			nbPVActuel = 0;
			setVivant(false);
			this.owner.rendreInactif(this);
		}
	}

	public void guerit(int valeurGuerison) {
		nbPVActuel = Math.min(nbPVActuel + valeurGuerison, nbPVMax);
	}

	public int getTailleZoneControle() {
		return tailleZoneControle;
	}

	public int getCategorie() {
		return categorie;
	}

	public void setCategorie(int categorie) {
		this.categorie = categorie;
	}

	public void setTailleZoneControle(int tailleZoneControle) {
		this.tailleZoneControle = tailleZoneControle;
	}

	public int getPortee() {
		for (Capacite c : capacites) {
			if (c.getType() == "AD") {
				return ((AttaqueADistance) c).portee;
			}
		}
		return 1;
	}

	public String getSprite() {
		return sprite;
	}

	public ArrayList<Action> getActionsPossibles() {
		ArrayList<Action> resultat = new ArrayList<Action>();
		for (Action a : actions) {
			if (a.isLegal())
				resultat.add(a);
		}
		return resultat;
	}

	public void reinitialisation() {
		setADejaBougeDansLeTour(false);
	}

	public void regenererPointsAction() {
		nbPAActuels = Math.min(nbPAActuels + nbPARegen, nbPAMax);
	}

	public void payerCoutsEntretien() {
		HashSet<EffetPersistant> tmp = new HashSet<EffetPersistant>(
				this.effetsActifs);
		for (EffetPersistant pe : tmp) {
			if (pe.getCoutEntretien() <= this.getPointsAction()) {
				if (!this.partie.ihm
						.repondreOuiNon("Payer le coût d'entretien de l'effet "
								+ pe.getNom() + " ?")) {
					pe.stopEffet();
					this.effetsActifs.remove(pe);
				}
			}
		}
	}

	public boolean getADejaBougeDansLeTour() {
		return aDejaBougeDansLeTour;
	}

	public void setADejaBougeDansLeTour(boolean aDejaBougeDansLeTour) {
		this.aDejaBougeDansLeTour = aDejaBougeDansLeTour;
	}

	@Override
	public boolean avantJetAttaque(EvenementJeu ej) {
		return false;
	}

	@Override
	public boolean apresJetAttaque(EvenementJeu ej) {
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
