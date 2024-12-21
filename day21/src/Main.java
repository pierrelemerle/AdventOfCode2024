import java.io.*;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        try {

            String filePath = "resources/input.txt";
            // Lecture des lignes du fichier
            List<String> codes = Helper.readFileToListString(filePath);
            int complexityTotal = 0;
            int nbDirectionnalRobots = 2;

            for (String code : codes) {
                // Parse the string for the first time
                String parsed = Day21Util.parseStringNumerical(code);
                System.out.println("Numerical Parsed is : " + parsed);

                // as many times as we have directionnal robots
                for (int i = 0; i < nbDirectionnalRobots; i++) {
                    // Parse the string with a directionnal keyboard
                    parsed = Day21Util.parseStringDirectionnal(parsed);
                    System.out.println("Parsed: " + parsed);
                }

                long complexity = Day21Util.calculateComplexity(code, parsed);
                complexityTotal += complexity;
                System.out.println("La complexite pour la partie 1 est: " + complexity);
            }
            System.out.println("La complexite pour la partie 1 est: " + complexityTotal);

        } catch (IOException e) {
            System.err.println("Error reading the file: " + e.getMessage());
        }
    }

}