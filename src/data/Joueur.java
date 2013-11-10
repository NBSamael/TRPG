package data;
import java.util.ArrayList;
import java.util.List;

import personnages.Personnage;

public class Joueur {
	public String nom;
	public List<Personnage> persos;
	
	public Joueur(String nom) {
		super();
		this.nom = nom;
		this.persos = new ArrayList<Personnage>();
	}
	
	public void reinitialisationPersonnages() {

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
}
