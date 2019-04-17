/* Commandes:
DOSKEY javac=del /f *.class $T ..\jdk\bin\javac *.java
DOSKEY java=..\jdk\bin\java Main
DOSKEY javal=del /f *.class $T ..\jdk\bin\javac *.java $T echo. ^&^& ..\jdk\bin\java Main
DOSKEY javar=del /f *.class
*/

public class Main {
	public static void main(String[] args) {
		Aleatoire.insererSeed(1544L);
		Plateau plateau = Plateau.getInstance();
		plateau.faireSimulation();
	}
}