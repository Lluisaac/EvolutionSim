

public class Coordonnee {
	
	private static int TAILLE_MAX_X;
	private static int TAILLE_MAX_Y;
	
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
		boolean afterMaxBottom = x >= TAILLE_MAX_X;
		boolean afterMaxRight = y >= TAILLE_MAX_Y;
		
		return !beforeZeroTop && !beforeZeroLeft && !afterMaxBottom && !afterMaxRight;
	}
	
	public static void setTailleMaximum(int x, int y) {
		TAILLE_MAX_X = x;
		TAILLE_MAX_Y = y;
	}
}