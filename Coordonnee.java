

public class Coordonnee {
	
	public int x;
	public int y;
	
	public Coordonnee(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public Coordonnee plus(Coordonnee c2) {
		return new Coordonnee(this.x + c2.x, this.y + c2.y);
	}
	
	@Override
	public String toString() {
		return "(x: " + x + "; y:" + y + ")"; 
	}
	
	public boolean isNotOutOfBounds() {
		boolean beforeZeroTop = x < 0;
		boolean beforeZeroLeft = y < 0;
		boolean afterMaxBottom = x >= Plateau.TAILLE_PLATEAU;
		boolean afterMaxRight = y >= Plateau.TAILLE_PLATEAU;
		
		return !beforeZeroTop && !beforeZeroLeft && !afterMaxBottom && !afterMaxRight;
	}
}