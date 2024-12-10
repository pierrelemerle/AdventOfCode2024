import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {

    public static void main(String[] args) {

        String filePath = "resources/input.txt";

        try {
            int[][] input = Helper.readFileToTabTabInt(filePath);
            AtomicInteger totalTrailHead = new AtomicInteger(0);

            // debug
            for (int i = 0; i < input.length; i++) {
                for (int j = 0; j < input[i].length; j++) {
                    System.out.print(input[i][j] + " ");
                }
                System.out.println();
            }

            totalTrailHead = Day10Util.generateAllPaths(input);

            System.out.println("Le nombre total de chemin pour la partie 2 est " + totalTrailHead);

        } catch (IOException e) {
            System.err.println("Error reading the file: " + e.getMessage());
        }
    }
}
