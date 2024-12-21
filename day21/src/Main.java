import java.io.*;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        try {
            // Chemin du fichier d'entrée
            String cheminFichier = "resources/input.txt";

            // Lecture des lignes du fichier
            List<String> codes = Helper.readFileToListString(cheminFichier);
            long complexiteTotale = 0L;
            int nombreRobotsDirectionnels = 2;

            // Partie 1 : Calcul de la complexité
            for (String code : codes) {
                // Premier parsing de la chaîne
                String parseInitial = Day21Util.parseStringNumerical(code);

                // Application des robots directionnels
                for (int i = 0; i < nombreRobotsDirectionnels; i++) {
                    parseInitial = Day21Util.parseStringDirectionnal(parseInitial);
                }

                complexiteTotale += Day21Util.calculateComplexity(code, parseInitial);
            }

            System.out.println("La complexité pour la partie 1 est : " + complexiteTotale);

            // Partie 2 : Calcul avancé de la complexité
            complexiteTotale = 0L;
            codes = Helper.readFileToListString(cheminFichier);

            long[][][] cacheComplexite = new long[25][5][5];
            Day21Util.directionnalKeyboard[] claviersDirectionnels = {
                    Day21Util.directionnalKeyboard.A,
                    Day21Util.directionnalKeyboard.UP,
                    Day21Util.directionnalKeyboard.DOWN,
                    Day21Util.directionnalKeyboard.LEFT,
                    Day21Util.directionnalKeyboard.RIGHT
            };

            // Initialisation du cache de complexité
            for (int j = 0; j < claviersDirectionnels.length; j++) {
                for (int k = 0; k < claviersDirectionnels.length; k++) {
                    cacheComplexite[0][j][k] = claviersDirectionnels[j].sequence(claviersDirectionnels[k]).length() + 1;
                }
            }

            // Remplissage du cache de complexité
            for (int i = 1; i < cacheComplexite.length; i++) {
                for (int j = 0; j < claviersDirectionnels.length; j++) {
                    for (int k = 0; k < claviersDirectionnels.length; k++) {
                        String sequence = claviersDirectionnels[j].sequence(claviersDirectionnels[k]) + 'A';
                        long resultat = 0L;
                        int courant = Day21Util.convertirClavierDirectionnel('A');

                        for (char caractere : sequence.toCharArray()) {
                            int suivant = Day21Util.convertirClavierDirectionnel(caractere);
                            resultat += cacheComplexite[i - 1][courant][suivant];
                            courant = suivant;
                        }
                        cacheComplexite[i][j][k] = resultat;
                    }
                }
            }

            // Calcul de la complexité pour chaque code
            for (String code : codes) {
                Day21Util.numericKeyboard clavierNumeriqueActuel = Day21Util.numericKeyboard.A;
                StringBuilder sequenceComplete = new StringBuilder();

                for (char caractere : code.toCharArray()) {
                    Day21Util.numericKeyboard clavierNumeriqueSuivant = Day21Util.convertirClavierNumerique(caractere);
                    sequenceComplete.append(clavierNumeriqueActuel.sequence(clavierNumeriqueSuivant)).append("A");
                    clavierNumeriqueActuel = clavierNumeriqueSuivant;
                }

                String sequence = sequenceComplete.toString();
                long resultat = 0L;
                int courant = Day21Util.convertirClavierDirectionnel('A');

                for (char caractere : sequence.toCharArray()) {
                    int suivant = Day21Util.convertirClavierDirectionnel(caractere);
                    resultat += cacheComplexite[24][courant][suivant];
                    courant = suivant;
                }

                complexiteTotale += resultat * Long.parseLong(code.split("A")[0]);
            }

            System.out.println("La complexité pour la partie 2 est : " + complexiteTotale);

        } catch (IOException e) {
            System.err.println("Erreur lors de la lecture du fichier : " + e.getMessage());
        }
    }

}
