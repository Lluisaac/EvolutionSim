

public abstract class PlanteFactory {
	
	public static Plante getInstance(Terre endroit) {
		Aleatoire rand = Aleatoire.getInstance();
		int choix = rand.nextInt(2);
		
		switch(choix) {
			case 1:
				return new Roseau(endroit);
			default:
				return new Buisson(endroit);
		}
	}
	
	public static Plante getInstance(Terre endroit, Plante plante) {
		String choix = plante.getType();
		
		switch(choix) {
			case "Roseau":
				return new Roseau(endroit);
			default:
				return new Buisson(endroit);
		}
	}
}