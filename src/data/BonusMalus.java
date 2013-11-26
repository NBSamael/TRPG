package data;

public class BonusMalus {
	int valeur; // Positif si bonus, négatif si malus
	Persistant origine;

	public BonusMalus(int valeur, Persistant origine) {
		this.valeur = valeur;
		this.origine = origine;
	}

	public int getValeur() {
		return valeur;
	}

	public Persistant getOrigine() {
		return origine;
	}
}
