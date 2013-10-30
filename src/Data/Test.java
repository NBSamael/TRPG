package Data;
import java.util.ArrayList;

import Actions.*;


public class Test {

	public static void main(String[] args) {
		//test1();
		test2();
	}
	
	private static void test1() {
		/* Cr�� un plateau puis demande une position � l'utilisateur.
		 * Retourne le chemin du point (4,2) jusqu'au point saisi.
		 */
		Interface interf = new Interface();
		Terrain t = new Terrain(10,10);
		t.chargeTerrain();
		System.out.println(t);
		
		XY origine = new XY(4,2);
		XY destination = interf.selectionneCase("Selectionner une destination");
		
		TerrainDistance resultat = t.calculeTerrainDistance(origine);
		//System.out.println(resultat);
		//System.out.println();
		System.out.println("Distance entre " + origine + " et " + destination + " : " + resultat.getDistance(destination));
		
		ArrayList<XY> chemin  = resultat.getTrajet(destination);
		System.out.println();
		System.out.println(chemin);		
	}
	
	private static void test2() {
		/* Cr�� un plateau puis demande une position � l'utilisateur.
		 * Retourne le chemin du point (4,2) jusqu'au point saisi.
		 */
		Interface interf = new Interface();
		Terrain t = new Terrain(10,10);
		t.chargeTerrain();
		Personnage p = new Personnage("John Doe", new XY(4,2), 3, 3);
		p.actions.add(new Walk(p,t));
		t.get(new XY(4,2)).setOccupant(p);
		System.out.println(t);

	}

}
