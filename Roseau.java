
public class Roseau extends Plante {
	
	private static final int RATIO_DIMINUTION_FERTILITE = 2;
	
	public Roseau(Terre endroitDeLaPlante) {
		super(endroitDeLaPlante);
	}
	
	@Override
	public void faireComportement() {
		essayerDeFaireMourirLaPlante();
		if (!estMorte()) {
			seReproduire();
			faireLaDiminutionDeFertilite();
		}
	}
	
	@Override
	public String toString() {
		return "X";
	}
	
	@Override
	public String getType() {
		return "Roseau";
	}
	
	@Override
	protected void essayerDeFaireMourirLaPlante() {
		int fertilite = getTerre().getFertilite();
		if (fertilite <= 1) {
			tuerLaPlante();
		}
	}
	
	@Override
	protected void seReproduire() {		
		Coordonnee emplacement = getCoordonneeDeLaPlante().plus(trouverNouvelEmplacement());
		
		boolean conditionsSontValides = Terre.isTerreValide(emplacement);
		
		if (conditionsSontValides) {
			poserPlante(emplacement);
		}
	}
	
	@Override
	protected Coordonnee trouverNouvelEmplacement() {
		Aleatoire rand = Aleatoire.getInstance();
		int x = 0;
		int y = 0;
		while (x == 0 && y == 0) {
			x = rand.nextInt(5) - 2;
			y = rand.nextInt(5) - 2;
		}
		return new Coordonnee(x, y);
	}
	
	private void poserPlante(Coordonnee emplacement) {
		Terre maTerreOuPoserLaPlante = (Terre) Plateau.getInstance().getCase(emplacement);
		
		Plante maNouvellePlante = PlanteFactory.getInstance(maTerreOuPoserLaPlante, this);
		
		if (maTerreOuPoserLaPlante.isFertiliteMaximale()) {
			maTerreOuPoserLaPlante.remplacerPlante(maNouvellePlante);
		} else {
			maTerreOuPoserLaPlante.ajouterPlante(maNouvellePlante);
		}
	}
	
	private void faireLaDiminutionDeFertilite() {
		boolean ilFautDiminuerLaFertilite = Aleatoire.testerProbabiliteSur(RATIO_DIMINUTION_FERTILITE);
		if (ilFautDiminuerLaFertilite) {
			getTerre().diminuerFertilite();
		}
	}
}