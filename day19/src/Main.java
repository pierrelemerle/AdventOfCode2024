import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        try {

            String filePath = "resources/input.txt";
            // Lecture des lignes du fichier
            List<String> lignes = Helper.readFileToListString(filePath);

            // Extraction des motifs disponibles et des designs souhaités
            List<String> motifsDisponibles = Arrays.asList(lignes.get(0).split(", "));
            List<String> designsSouhaites = lignes.subList(2, lignes.size());

            // Comptage des designs possibles pour la partie 1
            int designsPossibles = 0;
            for (String design : designsSouhaites) {
                if (Day19Util.peutFormerDesign(design, motifsDisponibles)) {
                    designsPossibles++;
                }
            }
            System.out.println("Nombre de designs possibles pour la partie 1 : " + designsPossibles);

            // Calculer le nombre total de possibilités pour chaque design pour la partie 2
            long totalPossibilites = 0;
            Map<String, Long> memo = new HashMap<>();

            for (String design : designsSouhaites) {
                long nbPossibilites = Day19Util.combienDePossibilite(design, motifsDisponibles, memo);
                totalPossibilites += nbPossibilites;
            }
            System.out.println("Nombre de designs possibles pour la partie 2 : " + totalPossibilites);

        } catch (IOException e) {
            System.err.println("Error reading the file: " + e.getMessage());
        }
    }

}