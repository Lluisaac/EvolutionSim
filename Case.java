import java.util.ArrayList;

public abstract class Case {
	private ArrayList<Entite> entites;
	private ArrayList<Entite> aSupprimer;
	private boolean leComportementEstIlEnCours;
	
	private Coordonnee endroitDeLaCase;
	
	public Case(Coordonnee endroitDeLaCase) {
		this.endroitDeLaCase = endroitDeLaCase;
		this.entites = new ArrayList<Entite>();
		this.aSupprimer = new ArrayList<Entite>();
		this.leComportementEstIlEnCours = false;
	}
	
	public final void faireComportements() {
		leComportementEstIlEnCours = true;
		declancherComportementsDesEntites();
		faireLeComportementDeLaCase();
		faireLaSuppression();
		leComportementEstIlEnCours = false;
	}
	
	public final boolean add(Entite nouvelleEntite) {
		return entites.add(nouvelleEntite);
	}
	
	public final void remove(Entite ancienneEntite) {
		if (leComportementEstIlEnCours) {
			aSupprimer.add(ancienneEntite);
		} else {
			entites.remove(ancienneEntite);
		}
	}
	
	public ArrayList<Entite> getEntites() {
		return entites;
	}
	
	public void setEntites(ArrayList<Entite> entites) {
		this.entites = entites;
	}
	
	public final Coordonnee getCoordonnee() {
		return endroitDeLaCase;
	}
	
	@Override
	public abstract String toString();
	
	private final void declancherComportementsDesEntites() {
		for (Entite entiteActuelle : entites) {
			entiteActuelle.faireComportement();
		}
	}
	
	protected abstract void faireLeComportementDeLaCase();	
	
	private final void faireLaSuppression() {
		for (Entite entiteActuelle : aSupprimer) {
			entites.remove(entiteActuelle);
		}
		aSupprimer.clear();
	}
}