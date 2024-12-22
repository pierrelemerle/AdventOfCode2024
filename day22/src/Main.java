import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        try {
            // Chemin du fichier d'entrée
            String filePath = "resources/input.txt";

            // Lecture des lignes du fichier
            List<Integer> buyers = Helper.readFileToListInt(filePath);
            List<int[]> nombres = new ArrayList<>();
            long complexiteTotale = 0L;
            int sequenceSize = 2000;

            // Partie 1
            for (Integer number : buyers) {
                int[] nombre = new int[sequenceSize];
                int newNb = number;
                // Application des robots directionnels
                for (int i = 0; i < sequenceSize; i++) {
                    nombre[i] = Math.abs(newNb % 10);
                    newNb = Day22Util.generateNextNumber(newNb);
                    // System.out.println("Le nombre : " + i + " est " + newNb);
                }
                nombres.add(nombre);
                complexiteTotale += newNb;
            }

            System.out.println("La complexité pour la partie 1 est : " + complexiteTotale);

            // Partie 2
            int maximumBanane = 0, currentBanane = 0;

            for (int a = -9; a <= 9; a++) {
                System.out.println("iteration " + a);
                for (int b = -9; b <= 9; b++) {
                    for (int c = -9; c <= 9; c++) {
                        for (int d = -9; d <= 9; d++) {
                            for (int[] nombre : nombres) {
                                int i = 4;
                                boolean found = false;
                                while (i < 2000 && !found) {
                                    int diffA = nombre[i - 3] - nombre[i - 4];
                                    int diffB = nombre[i - 2] - nombre[i - 3];
                                    int diffC = nombre[i - 1] - nombre[i - 2];
                                    int diffD = nombre[i] - nombre[i - 1];
                                    if (diffA == a && diffB == b && diffC == c && diffD == d) {
                                        currentBanane += nombre[i];
                                        found = true;
                                    }
                                    i++;
                                }
                            }
                            if (currentBanane > maximumBanane) {
                                maximumBanane = currentBanane;
                                System.out.println("le nombre maintenant est " + currentBanane);
                            }
                            currentBanane = 0;
                        }
                    }
                }
            }

            System.out.println("La nombre maximum de bananes pour la partie 2 est : " + maximumBanane);

        } catch (IOException e) {
            System.err.println("Erreur lors de la lecture du fichier : " + e.getMessage());
        }
    }

}
