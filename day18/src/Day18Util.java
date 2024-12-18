import java.awt.Point;
import java.util.List;

public class Day18Util {

    // Directions: {East, South, West, North}
    static int[][] directions = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };

    public static int findShortestPath(char[][] map) {

        // initialise en partant de 0,0
        int y = 0, x = 0;

        int[][] visited = new int[map.length][map[0].length];
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                visited[i][j] = 1000; // just random high number
            }
        }

        oneStep(map, visited, 0, y, x);

        // return the minimal number we reached the last case. 70,70
        return visited[visited.length - 1][visited.length - 1];
    }

    public static void simulateCorruptedMemory(char[][] map, List<Point> instructions, int nbCorrupted) {
        for (int i = 0; i < nbCorrupted; i++) {
            int y = (int) instructions.get(i).getY();
            int x = (int) instructions.get(i).getX();
            map[y][x] = '#';
        }
    }

    public static void simulateOneCorruptedMemory(char[][] map, List<Point> instructions, int nbCorrupted) {
        int y = (int) instructions.get(nbCorrupted - 1).getY();
        int x = (int) instructions.get(nbCorrupted - 1).getX();
        map[y][x] = '#';
    }

    private static void oneStep(char[][] map, int[][] visited, int currentNbSteps, int y,
            int x) {
        // if we are not on an optimal path for a specific case, we can already stop
        // exploring this way
        if (currentNbSteps >= visited[y][x]) {
            return;
        }
        visited[y][x] = currentNbSteps;
        // if we reached the end, we are done.
        if (x == (map.length - 1) && y == (map.length - 1)) {
            return;
        }
        // handle the 4 alternatives now
        if (((y + 1) < map.length) && (map[y + 1][x] != '#')) {
            oneStep(map, visited, currentNbSteps + 1, y + 1, x);
        }
        if (((y - 1) >= 0) && (map[y - 1][x] != '#')) {
            oneStep(map, visited, currentNbSteps + 1, y - 1, x);
        }
        if (((x + 1) < map.length) && (map[y][x + 1] != '#')) {
            oneStep(map, visited, currentNbSteps + 1, y, x + 1);
        }
        if (((x - 1) >= 0) && (map[y][x - 1] != '#')) {
            oneStep(map, visited, currentNbSteps + 1, y, x - 1);
        }

    }
}