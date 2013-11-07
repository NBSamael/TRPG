package data;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import personnages.Personnage;

public class InstancePartie {
	public Terrain plateau;
	public List<Equipe> equipes;
	public Interface ihm;
	public int cptTour;
	
	public InstancePartie(Terrain plateau, List<Equipe> equipes, Interface ihm) {
		super();
		this.plateau = plateau;
		this.equipes = equipes;
		this.ihm = ihm;
	}
	
	public void initialisePartie() {
		
	}
	
	public void lancePartie() {
		while(true) {
			ArrayList<Personnage> persosTries = calculeOrdreAction();
			for(Personnage p : persosTries) {
				System.out.println(plateau);
				int numAction = ihm.selectionnerAction(p);
				p.actions.get(numAction).getParameters();
				p.actions.get(numAction).execute();
			}
		}
		
	}
	
	private ArrayList<Personnage> calculeOrdreAction() {
		ArrayList<Personnage> collectionTriee = new ArrayList<Personnage>();
		Set<Personnage> collectionNonTriee = new HashSet<Personnage>();
		collectionNonTriee.addAll(plateau.personnages);
		revue :
		for(Personnage p : collectionNonTriee) {
			int initp = p.calculInitiative();
			for(int i=0; i < collectionTriee.size(); i++) {
				if (initp <= collectionTriee.get(i).getInitiative()) {
					collectionTriee.add(i, p);
					continue revue;
				}
			}
			collectionTriee.add(p);
		}
		return collectionTriee;
	}
	
}
