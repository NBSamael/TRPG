package attitudes;

public class Condamne extends Attitude {

	public Condamne(int niveau) {
		super("Condamné", "à chaque fois que vous retirez un marqueur Destin pendant la phase d’entretien, lancez un dé. Sur un résultat de 8, 9 ou 10, le personnage perd immédiatement tous ses PV restants", niveau);
	}

}
