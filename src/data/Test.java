package data;
import java.util.ArrayList;

import capacites.AttaqueADistance;
import personnages.AlessaRaincross;
import personnages.DereckShezard;
import personnages.Personnage;
import actions.*;


public class Test {

	public static void main(String[] args) {
		//testdes();
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
	
	private static void testLdV() {
		/* Créé un plateau puis demande une position à l'utilisateur.
		 * Retourne le chemin du point (4,2) jusqu'au point saisi.
		 */
		//Interface interf = new Interface();
		Terrain t = new Terrain(10,10);
		t.chargeTerrain();
		System.out.println(t);
		
		XY origine = new XY(4,2);
		XY destination = new XY(6,7);
		
		//System.out.println(GrilleLigneDeVue.calculerLdV(t, origine, destination));
		//System.out.println(GrilleLigneDeVue.hasLdV(t, origine, destination));
		
		GrilleLigneDeVue resultat = t.calculeGrilleLigneDeVue(origine, 4, true, false);
		System.out.println(resultat);
	}
	
	
	private static void test2() {
		Interface interf = new Interface();
		
		Terrain t = new Terrain(10,10);
		t.chargeTerrain();
		
		Joueur j1 = new Joueur("NBS");
		Joueur j2 = new Joueur("Mirth");
		
		Personnage p1 = new DereckShezard(j1);
		p1.setPositionX(new XY(4,3));
		t.get(new XY(4,3)).setOccupant(p1);
		t.personnages.add(p1);
		
		Personnage p2 = new AlessaRaincross(j2);
		p2.setPositionX(new XY(7,7));
		t.get(new XY(7,7)).setOccupant(p2);
		t.personnages.add(p2);
		
		j1.persos.add(p1);
		j2.persos.add(p2);
		
		Equipe e1 = new Equipe("Equipe 1");
		Equipe e2 = new Equipe("Equipe 2");
		e1.joueurs.add(j1);
		e2.joueurs.add(j2);
		
		ArrayList<Equipe> listeEquipes = new ArrayList<Equipe>();
		listeEquipes.add(e1);
		listeEquipes.add(e2);
		
		InstancePartie jeu = new InstancePartie(t, listeEquipes, interf);
		
		j1.equipe = e1;
		j2.equipe = e2;
		
		e1.partie = jeu;
		e2.partie = jeu;
		
		p1.partie = jeu;
		p2.partie = jeu;

		jeu.initialisePartie();
		jeu.lancePartie();	
	}
	
	private static void testdes() {
		Des.lanceDe(Des.D20);
		Des.lanceDes(Des.D20, 2, Des.MAX);
		
		Des.lanceDe(Des.D10);
		Des.lanceDes(Des.D10, 12, Des.MAX);
		
		Des.lanceDes(Des.D6, 6, Des.SOMME);
		
		Des.lanceDes(Des.D20, 3, Des.MIN);
	}

}
