import java.io.IOException;

public class Main {

    public static void main(String[] args) {

        String filePath = "resources/inputexemple.txt";

        try {
            char[][] map = Helper.readFileToTabTabChar(filePath);
            int totalCostFencing = 0;

            // go through the map, and check for each letter, how big is the region and what
            // is the cost
            for (int y = 0; y < map.length; y++) {
                for (int x = 0; x < map[y].length; x++) {
                    // if the letter/region has not been treated yet
                    if (map[y][x] != '.') {
                        // look for the cost of this region and add it to the total
                        totalCostFencing += Day12Util.findASpecificReginCost(map, y, x);
                    }
                }
            }

            System.out.println("Le cout total des enclos pour la partie 2 est " + totalCostFencing);

        } catch (IOException e) {
            System.err.println("Error reading the file: " + e.getMessage());
        }
    }
}
