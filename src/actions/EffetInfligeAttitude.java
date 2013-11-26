package actions;

import java.lang.reflect.InvocationTargetException;

import data.Des;
import data.GrilleLigneDeVue;
import attitudes.Attitude;
import personnages.Personnage;

public class EffetInfligeAttitude extends Effet {
	public Personnage cible;
	protected Class<?> attitude;
	protected int niveauAttitude;
	protected int seuilResistance;
	protected int porteeEffet;
	
	public EffetInfligeAttitude(Personnage owner, int coutPA, String nom,
			String description, Class<?> attitude, int niveauAttitude, int seuilResistance, int porteeEffet) {
		super(owner, coutPA);
		this.nom = nom;
		this.description = description;
		this.attitude = attitude;
		this.niveauAttitude = niveauAttitude;
		this.seuilResistance = seuilResistance;
		this.porteeEffet = porteeEffet;
	}
	
	@Override
	public boolean isLegal() {
		int porteeRec = 1;
		if (this.porteeEffet > 0)
			porteeRec = this.porteeEffet;
		else
			porteeRec = owner.getTailleZoneControle();

		GrilleLigneDeVue possibilités = owner.partie.plateau
				.calculeGrilleLigneDeVue(owner.getPosition(), porteeRec, true, false);
		
		return (super.isLegal() && !possibilités.getEnnemisDansZone(owner).isEmpty());
	}

	@Override
	public void getParameters() {
		int porteeRec = 1;
		if (this.porteeEffet > 0)
			porteeRec = this.porteeEffet;
		else
			porteeRec = owner.getTailleZoneControle();

		GrilleLigneDeVue possibilités = owner.partie.plateau
				.calculeGrilleLigneDeVue(owner.getPosition(), porteeRec, true, false);
		
		do {
			cible = owner.partie.ihm.selectionnerPersonnage(possibilités.getEnnemisDansZone(owner));
		} while (cible == null
				|| cible.isDissimule());
	}

	@Override
	public void execute() {
		Attitude a = null;
		Class[] parameterTypes =  { int.class, Personnage.class };
		Object[] initargs = { niveauAttitude, cible };
		try {
			a = (Attitude) attitude.getConstructor(parameterTypes).newInstance(initargs);
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println(owner.nom + " utilise " + this.nom + " contre "
				+ cible.nom);
		
		int valeurResistancePersonnage = cible.getResistance();
		int valeurResistanceDes = Des.lanceDe(Des.D10);
		int valeurResistance = valeurResistancePersonnage + valeurResistanceDes;
		
		System.out.println("Jet de résistance : "
				+ valeurResistance + " ("
				+ valeurResistancePersonnage + "+" + valeurResistanceDes + ")");
		
		if (valeurResistance > seuilResistance) {
			System.out.println("Test de résistance réussi : " + cible.nom + " n'est pas affecté(e)");
		}
		else {
			System.out.println("Test de résistance raté : " + cible.nom + " subit une attitude " + a.getNom() + " de niveau " + a.getNiveau());
			cible.ajoutAttitude(a);
		}
	}

}
