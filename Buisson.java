

public class Buisson extends Plante {
	
	public Buisson(Terre endroit) {
		super(endroit);
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
		return "O";
	}
	
	@Override
	public String getType() {
		return "Buisson";
	}
	
	@Override
	protected void seReproduire() {
		Coordonnee emplacement = getCoordonneeDeLaPlante().plus(trouverNouvelEmplacement());
		
		boolean conditionsValides = Terre.isTerreValide(emplacement);
		
		boolean ilFautFaireReproduireLaPlante = Aleatoire.testerProbabiliteSur(3);
		if (ilFautFaireReproduireLaPlante && conditionsValides) {
			poserPlante(emplacement);
		}
	}
	
	private void poserPlante(Coordonnee emplacement) {
		Terre terreOuPoserLaPlante = (Terre) Plateau.getInstance().getCase(emplacement);
		
		Plante nouvellePlante = PlanteFactory.getInstance(terreOuPoserLaPlante, this);
		
		terreOuPoserLaPlante.ajouterPlante(nouvellePlante);
	}
	
	private void faireLaDiminutionDeFertilite() {
		boolean diminuerLaFertilite = Aleatoire.testerProbabiliteSur(8);
		if (diminuerLaFertilite) {
			getTerre().diminuerFertilite();
		}
	}
}