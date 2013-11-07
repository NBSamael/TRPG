package data;
import java.util.ArrayList;
import java.util.Scanner;

import personnages.Personnage;
import actions.Action;



public class Interface {
	Scanner sc;
	
	
	
	public Interface() {
		sc = new Scanner(System.in);
	}
	
	public int selectionnerAction(Personnage p) {
		System.out.println("Personnage : " + p.nom);
		System.out.println("Liste des actions :");
		ArrayList<Action> tmp = p.getActionsPossibles();
		for (int i = 0; i < tmp.size(); i++) {
			System.out.println(i + " > " + p.actions.get(i).getNom());
		}
		System.out.print("\nSaisissez le numéro de l'action voulue : ");
		String str = sc.nextLine();		
		int n = -1;
		try {
			n = new Integer(str).intValue();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return -1;
		}
		return n;
	}


	public XY selectionnerCase(String prompt) {
		System.out.print(prompt + " (format (X,Y)) : ");
		String str = sc.nextLine();
		str = str.substring(1, str.length()-1);
		try {
			String[] coord = str.split(",");
			int x = new Integer(coord[0]).intValue();
			int y = new Integer(coord[1]).intValue();
			return new XY(x,y);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return null;
		}
	}
}
