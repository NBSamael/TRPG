package Data;
import java.util.ArrayList;
import java.util.List;

public class Joueur {
	public String nom;
	public List<Personnage> persos;
	
	public Joueur(String nom) {
		super();
		this.nom = nom;
		this.persos = new ArrayList<Personnage>();
	}
	
	
}