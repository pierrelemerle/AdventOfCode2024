import java.io.IOException;
import java.util.Set;

public class Main {

    public static void main(String[] args) {

        String filePath = "resources/input.txt";

        try {
            char[][] map = Helper.readFileToTabTabChar(filePath);

            // debug
            for (int i = 0; i < map.length; i++) {
                for (int j = 0; j < map[i].length; j++) {
                    System.out.print(map[i][j] + " ");
                }
                System.out.println();
            }

            int bestScore = Day16Util.generateAllPaths(map);
            System.out.println("Le meilleur chemin, le plus court pour la partie 1 est: " + bestScore);

            // corner case left to be fixed to consider ALL the best path and not only one.
            Set<String> bestTiles = Day16Util.findBestTiles(map, bestScore);
            System.out.println("Nombre de siege sur LE meilleur chemin: " + bestTiles.size());

        } catch (IOException e) {
            System.err.println("Error reading the file: " + e.getMessage());
        }
    }

}
