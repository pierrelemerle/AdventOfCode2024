import java.io.*;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {

    public static void main(String[] args) {
        try {

            String filePath = "resources/input.txt";
            // Lecture des lignes du fichier
            char[][] map = Helper.readFileToTabTabChar(filePath);

            // just print the shortest path for reference
            int shortestStandardPath = Day20Util.findShortestPath(map);
            System.out.println("Le chemin le plus court standard est: " + shortestStandardPath);

            // part 1
            AtomicInteger improvments = new AtomicInteger(0);
            for (int y = 1; y < (map.length - 1); y++) {
                for (int x = 1; x < (map[x].length - 1); x++) {
                    if (map[y][x] == '#') {
                        Day20Util.cheat1s(map, y, x, improvments);
                    }
                }
            }
            System.out.println("Le nombre d'amelioration pour la partie 1 est: " + improvments.get());

            // part 2
            String[][] grid = new String[map.length][map[0].length];
            for (int y = 1; y < (map.length - 1); y++) {
                for (int x = 1; x < (map[x].length - 1); x++) {
                    grid[y][x] = String.valueOf(map[y][x]);
                }
            }

            System.out.println("le nombre d'amelioration pour la partie 2 est: " + Day20Util.cheat2s(grid, 100));

        } catch (IOException e) {
            System.err.println("Error reading the file: " + e.getMessage());
        }
    }

}