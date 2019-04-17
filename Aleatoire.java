import java.util.Random;

public class Aleatoire extends Random {
	private static Aleatoire rand;
	
	private Aleatoire(){}
	
	public static Aleatoire getInstance() {
		if (rand == null) {
			rand = new Aleatoire();
		}
		return rand;
	}
	
	public static void insererSeed(long seed) {
		getInstance().setSeed(seed);
	}
	
	public static boolean testerProbabiliteSur(int proba) {
		return rand.nextInt(proba) == 0;
	}
}