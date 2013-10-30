package Data;
import java.util.ArrayList;
import java.util.List;


public class Equipe extends ArrayList<Joueur> {
	private String nom;

	public Equipe(String nom) {
		super();
		this.nom = nom;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}
	
	
}
