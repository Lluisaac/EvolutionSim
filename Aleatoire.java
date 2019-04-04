import java.util.Random;

public class Aleatoire {
	private static Random rand = new Random();
	
	public static boolean testerProbabiliteSur(int proba) {
		return rand.nextInt(proba) == 0;
	}
}