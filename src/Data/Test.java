package Data;
import java.util.ArrayList;

import Actions.*;


public class Test {

	public static void main(String[] args) {
		//test1();
		test2();
	}
	
	private static void test1() {
		/* Créé un plateau puis demande une position à l'utilisateur.
		 * Retourne le chemin du point (4,2) jusqu'au point saisi.
		 */
		Interface interf = new Interface();
		Terrain t = new Terrain(10,10);
		t.chargeTerrain();
		System.out.println(t);
		
		XY origine = new XY(4,2);
		XY destination = interf.selectionnerCase("Selectionner une destination");
		
		GrilleDeplacements resultat = t.calculeGrilleDeplacements(origine);
		//System.out.println(resultat);
		//System.out.println();
		System.out.println("Distance entre " + origine + " et " + destination + " : " + resultat.getDistance(destination));
		
		ArrayList<XY> chemin  = resultat.getTrajet(destination);
		System.out.println();
		System.out.println(chemin);		
	}
	
	private static void test2() {
		Interface interf = new Interface();
		
		Terrain t = new Terrain(10,10);
		t.chargeTerrain();
		
		Personnage p1 = new Personnage("John Doe", new XY(4,2), 3, 3, 9);
		t.get(new XY(4,2)).setOccupant(p1);
		t.personnages.add(p1);
		Personnage p2 = new Personnage("Xena", new XY(8,8), 3, 3, 4);
		t.get(new XY(8,8)).setOccupant(p2);
		t.personnages.add(p2);
		
		Joueur j1 = new Joueur("NBS");
		Joueur j2 = new Joueur("Mirth");
		j1.persos.add(p1);
		j2.persos.add(p2);
		
		Equipe e1 = new Equipe("Equipe 1");
		Equipe e2 = new Equipe("Equipe 2");
		e1.joueurs.add(j1);
		e1.joueurs.add(j2);
		
		ArrayList<Equipe> listeEquipes = new ArrayList<Equipe>();
		listeEquipes.add(e1);
		listeEquipes.add(e2);
		
		InstancePartie jeu = new InstancePartie(t, listeEquipes, interf);
		p1.actions.add(new Walk(p1, jeu));
		p2.actions.add(new Walk(p2, jeu));
		jeu.initialisePartie();
		jeu.lancePartie();
		
	}

}
