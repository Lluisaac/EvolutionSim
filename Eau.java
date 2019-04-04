import java.util.ArrayList;

public class Eau extends Case {
	
	public Eau(Coordonnee position) {
		super(position);
	}
	
	@Override
	public String toString() {
		return "~";
	}
	
	@Override
	protected void faireLeComportementDeLaCase() {
		ArrayList<Terre> terreQuiContourne = recupererTerreQuiContourne();
		for (Terre terreActuelle : terreQuiContourne) {
			terreActuelle.augmenterFertilite();
		}
	}
	
	private ArrayList<Terre> recupererTerreQuiContourne() {
		ArrayList<Terre> terreQuiContourne = new ArrayList<>();
		for (int x = -1; x < 2; x++) {
			for (int y = -1; y < 2; y++) {
				Coordonnee contour = getCoordonnee().plus(new Coordonnee(x, y));
				ajouterTerreSiValide(contour, terreQuiContourne);
			}
		}
		return terreQuiContourne;
	}
	
	private void ajouterTerreSiValide(Coordonnee positionDeLaTerreAVerifier, ArrayList<Terre> terreQuiContourne) {
		boolean isTerreValide = Terre.isTerreValide(positionDeLaTerreAVerifier);
		if (isTerreValide) {
			Terre terreContournantValide = (Terre) Plateau.getInstance().getCase(positionDeLaTerreAVerifier);
			terreQuiContourne.add(terreContournantValide);
		}
	}
}