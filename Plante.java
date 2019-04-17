

public abstract class Plante implements Entite {
	
	private Terre endroitDeLaPlante;
	
	private boolean estMorte;
	
	public Plante(Terre endroitDeLaPlante) {
		this.endroitDeLaPlante = endroitDeLaPlante;
		this.estMorte = false;
	}
	
	@Override
	public void faireComportement() {
		essayerDeFaireMourirLaPlante();
		if (!estMorte) {
			seReproduire();
		}
	}
	
	@Override
	public abstract String toString();
	
	public abstract String getType();
	
	public final Terre getTerre() {
		return endroitDeLaPlante;
	}
	
	public final Coordonnee getCoordonneeDeLaPlante() {
		return endroitDeLaPlante.getCoordonnee();
	}
	
	public final boolean estMorte() {
		return estMorte;
	}
	
	public final void setMorte(boolean estMorte) {
		this.estMorte = estMorte;
	}
	
	public void tuerLaPlante() {
		endroitDeLaPlante.supprimerPlante();
		estMorte = true;
	}
	
	protected void essayerDeFaireMourirLaPlante() {
		int fertilite = endroitDeLaPlante.getFertilite();
		if (fertilite == 0) {
			tuerLaPlante();
		}
	}
	
	protected void seReproduire() {		
		Coordonnee emplacement = getCoordonneeDeLaPlante().plus(trouverNouvelEmplacement());
		
		boolean conditionsValides = Terre.isTerreValide(emplacement);
		
		if (conditionsValides) {
			Terre maTerre = (Terre) Plateau.getInstance().getCase(emplacement);
			maTerre.ajouterPlante(PlanteFactory.getInstance(maTerre, this));
		}
	}
	
	protected Coordonnee trouverNouvelEmplacement() {
		Aleatoire rand = Aleatoire.getInstance();
		int x = 0;
		int y = 0;
		while (x == 0 && y == 0) {
			x = rand.nextInt(3) - 1;
			y = rand.nextInt(3) - 1;
		}
		return new Coordonnee(x, y);
	}
}