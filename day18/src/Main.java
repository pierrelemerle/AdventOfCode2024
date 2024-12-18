import java.io.IOException;
import java.util.List;
import java.awt.Point;

public class Main {

    public static void main(String[] args) {

        String filePath = "resources/input.txt";

        try {
            // 12 for the exemple, but will be 1024 for the real case
            int initialNbBytes = 1024;
            // init a map : 7/7 for the exemple, but will be 71/71 for the real case
            char[][] map = new char[71][71];
            for (int i = 0; i < map.length; i++) {
                for (int j = 0; j < map[i].length; j++) {
                    map[i][j] = '.';
                }
            }

            List<Point> instructions = Helper.readFileToListPoint(filePath);
            Day18Util.simulateCorruptedMemory(map, instructions, initialNbBytes);
            for (int i = 0; i < map.length; i++) {
                for (int j = 0; j < map[i].length; j++) {
                    System.out.print(map[i][j]);
                }
                System.out.println();
            }
            int bestScore = Day18Util.findShortestPath(map);
            System.out.println("Le plus court chemin apres 1024 bytes, pour la partie 1 est: " + bestScore);

            while (Day18Util.findShortestPath(map) < 1000) {
                initialNbBytes++;
                Day18Util.simulateOneCorruptedMemory(map, instructions, initialNbBytes);
            }
            System.out.println("Le dernier byte qui a bloque le chemin, pour la partie 2 est: "
                    + instructions.get(initialNbBytes - 1).x + "," + instructions.get(initialNbBytes - 1).y);

        } catch (IOException e) {
            System.err.println("Error reading the file: " + e.getMessage());
        }
    }

}
