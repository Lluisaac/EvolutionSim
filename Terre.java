import java.util.ArrayList;

public class Terre extends Case{
	
	public static final int FERTILITE_MAX = 5;
	public static final int FERTILITE_MIN = 0;
	
	public static final int INDICE_ACTIVATION_FERTILITE = 60;
	public static final int INDICE_ACTIVATION_APPARITION = 1000000;
	
	private int fertilite;
	
	private Plante plante;
	
	public Terre(Coordonnee emplacement) {
		super(emplacement);
		this.fertilite = 3;
	}
	
	@Override
	public String toString() {
		String str = "";
		
		if (contiensUnePlante()) {
			str += plante;
		} else {
			str += " ";
		}
		
		return str;
	}
	
	public boolean contiensUnePlante() {
		return plante != null;
	}
	
	public void fairePousserUneNouvellePlante() {
		Plante maNouvellePlante = PlanteFactory.getInstance(this);
		ajouterPlante(maNouvellePlante);
	}
	
	public void ajouterPlante(Plante plante) {
		boolean planteEstPlantable = !contiensUnePlante();
		if (planteEstPlantable) {
			this.plante = plante;
		}
	}
	
	public void remplacerPlante(Plante plante) {
		this.plante = plante;
	}
	
	public void supprimerPlante() {
		plante = null;
	}
	
	public int getFertilite() {
		return fertilite;
	}
	
	public boolean isFertiliteMaximale() {
		return fertilite == FERTILITE_MAX;
	}
	
	public void setFertilite(int fertiliteVoulue) {
		if (fertiliteVoulue >= FERTILITE_MIN && fertiliteVoulue <= FERTILITE_MAX) {
			this.fertilite = fertiliteVoulue;
		}
	}
	
	public void augmenterFertilite() {
		setFertilite(fertilite + 1);
	}
	
	public void augmenterFertilite(int val) {
		setFertilite(fertilite + val);
	}
	
	public void diminuerFertilite() {
		setFertilite(fertilite - 1);
	}
	
	public void diminuerFertilite(int val) {
		setFertilite(fertilite - val);
	}
	
	public static boolean isTerreValide(Coordonnee emplacement) {
		boolean emplacementEstDansLePlateau = emplacement.isNotOutOfBounds();
		if (emplacementEstDansLePlateau) {
			Case caseDeLEmplacement = Plateau.getInstance().getCase(emplacement);
			boolean isEmplacementTerre = caseDeLEmplacement.getClass().getSimpleName().equals("Terre");
			return isEmplacementTerre;
		} else {
			return false;
		}
	}
	
	@Override
	protected void faireLeComportementDeLaCase() {
		augmenterAleatoirementLaFertilite();
		if (contiensUnePlante()) {
			plante.faireComportement();
		} else {
			fairePousserAleatoirementUneNouvellePlante();
		}
	}
	
	private void augmenterAleatoirementLaFertilite() {
		boolean augmenterLaFertilite = Aleatoire.testerProbabiliteSur(INDICE_ACTIVATION_FERTILITE);
		if (augmenterLaFertilite) {
			augmenterFertilite();
		}
	}
	
	private void fairePousserAleatoirementUneNouvellePlante() {
		boolean fairePousserPlante = Aleatoire.testerProbabiliteSur(INDICE_ACTIVATION_APPARITION);
		if (fairePousserPlante) {
			fairePousserUneNouvellePlante();
		}
	}
	
	
}