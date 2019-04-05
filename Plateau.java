import java.util.ArrayList;
import java.util.Random;

public class Plateau {
	
	public static final int TAILLE_PLATEAU = 46;
	
	private static final int TEMPS_ATTENTE_ENTRE_TOURS = 50;
	
	private static Plateau singleton;
	
	private Case[][] damier;
	
	private int tour;
	
	private Plateau() {
		this.damier = new Case[TAILLE_PLATEAU][TAILLE_PLATEAU];
		remplirLeDamier();
		this.tour = 0;
	}
	
	public static Plateau getInstance() {
		if (singleton == null) {
			singleton = new Plateau();
		}
		return singleton;
	}
	
	public void faireSimulation() {
		boolean termine = false;
		while(!termine) {
			tour++;
			attendre();
			afficherLePlateau();
			faireComportementDeChaqueCase();
		}
		
		afficherLePlateau();
	}
	
	public void afficherLePlateau() {
		System.out.println(this);
	}
	
	public void faireComportementDeChaqueCase() {
		ArrayList<Coordonnee> ordreAlea = genererOrdreAleatoirePourComportements();
		for (Coordonnee emplacementChoisi : ordreAlea) {
			damier[emplacementChoisi.x][emplacementChoisi.y].faireComportements();
		}
	}
	
	@Override
	public String toString() {
		String str = ligneDetoiles() + "\n";
		
		for (Case[] ligne : damier) {
			str += "* ";
			for (Case maCase : ligne) {
				str += maCase + " "; 
			}
			str += "*\n";
		}
		
		str += ligneDetoiles() + "\n" + montrerStats();
		return str;
	}
	
	public Case getCase(Coordonnee coord) {
		return damier[coord.x][coord.y];
	}
	
	private void remplirLeDamier() {
		for (int i = 0; i < TAILLE_PLATEAU; i++) {
			for (int j = 0; j < TAILLE_PLATEAU; j++) {
				Coordonnee emplacementChoisi = new Coordonnee(i, j);
				mettreBonTypeDeCase(emplacementChoisi);
			}
		}
	}
	
	private void mettreBonTypeDeCase(Coordonnee coord) {
		int x = coord.x;
		int y = coord.y;
		if ((x == 35 && y == 20) || (x == 35 && y == 19) || (x == 36 && y == 20)) {
			damier[x][y] = new Eau(coord);
		} else {
			damier[x][y] = new Terre(coord);
		}
	}
	
	private void attendre() {
		try {
			Thread.sleep(TEMPS_ATTENTE_ENTRE_TOURS) ;
		}  catch (InterruptedException e) {}
	}
	
	private ArrayList<Coordonnee> genererOrdreAleatoirePourComportements() {
		ArrayList<Coordonnee> ordreAlea = new ArrayList<>();
		
		for (int i = 0; i < TAILLE_PLATEAU; i++) {
			for (int j = 0; j < TAILLE_PLATEAU; j++) {
				Coordonnee coord = new Coordonnee(i, j);
				placerCoordonneeAleatoirementDansLaSequence(coord, ordreAlea);
			}
		}
		
		return ordreAlea;
	}
	
	private void placerCoordonneeAleatoirementDansLaSequence(Coordonnee coord, ArrayList<Coordonnee> ordreAlea) {
		Random rand = new Random();
		int placement = rand.nextInt(ordreAlea.size() + 1);
		ordreAlea.add(placement, coord);
	}
	
	private String ligneDetoiles() {
		String str = "";
		for (int i = 0; i < TAILLE_PLATEAU + 1; i++) {
			str += "* ";
		}
		return str + "*";
	}
	
	private String montrerStats() {
		String str = "tour: " + tour;
		return str;
	}
	
	private int nbDePlantes() {
		int compteur = 0;
		for (Case[] ligne : damier) {
			for (Case maCase : ligne) {
				if (laCaseContiensUnePlante(maCase)) {
					compteur++;
				}
			}
		}
		return compteur;
	}
	
	private boolean laCaseContiensUnePlante(Case maCase) {
		boolean laCaseEstDeLaTerre = Terre.isTerreValide(maCase.getCoordonnee());
		if (laCaseEstDeLaTerre) {
			boolean contiensUnePlante = ((Terre) maCase).contiensUnePlante();
			return contiensUnePlante;
		} else {
			return false;
		}
	}
}