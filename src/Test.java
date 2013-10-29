import java.util.ArrayList;


public class Test {

	public static void main(String[] args) {
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

}
