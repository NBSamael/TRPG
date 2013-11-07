package Data;

import java.util.ArrayList;
import java.util.Random;

public abstract class Des {
	public static int SOMME = 0;
	public static int MAX = 1;
	public static int MIN = 2;
	
	public static int D4 = 4;
	public static int D6 = 6;
	public static int D8 = 8;
	public static int D10 = 10;
	public static int D12 = 12;
	public static int D20 = 20;
	public static int D100 = 100;

	public static int lanceDe(int typeDes) {
		Random r = new Random();
		int resultat = 1 + r.nextInt(typeDes);
		
		System.out.println("Lancement de 1D" + typeDes + " -> " + resultat);
		
		return resultat;		
	}
	
	public static int lanceDes(int typeDes, int nbDes, int methodeCalcul) {
		ArrayList<Integer> listeValeurs = new ArrayList<Integer>();
		String methode = null;
		Random r = new Random();
		int resultat = 0;
		
		if (methodeCalcul == Des.MIN)
			resultat = Integer.MAX_VALUE;
		
		for (int i = 1; i <= nbDes; i++) {
			int valeur = 1 + r.nextInt(typeDes);
			listeValeurs.add(valeur);
			if (methodeCalcul == Des.SOMME)
				resultat = resultat + valeur;
			else if (methodeCalcul == Des.MAX)
				resultat = Math.max(resultat, valeur);
			else if (methodeCalcul == Des.MIN)
				resultat = Math.min(resultat, valeur);
		}
		
		if (methodeCalcul == Des.SOMME)
			methode = "Somme";
		else if (methodeCalcul == Des.MAX)
			methode = "Max";
		else if (methodeCalcul == Des.MIN)
			methode = "Min";
		System.out.println("Lancement de " + nbDes + "D" + typeDes + " " + listeValeurs + " -> " + resultat + " (" + methode + ")");
		
		return resultat;
	}

	
}
