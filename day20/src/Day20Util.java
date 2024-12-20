import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

public class Day20Util {

    public static int findShortestPath(char[][] map) {

        // initialise en partant de S
        int y = 0, x = 0;
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                if (map[i][j] == 'S') {
                    y = i;
                    x = j;
                }
            }
        }

        int[][] visited = new int[map.length][map[0].length];
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                visited[i][j] = 10000; // just random high number
            }
        }

        oneStep(map, visited, 0, y, x);

        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                if (map[i][j] == 'E') {
                    y = i;
                    x = j;
                }
            }
        }

        // return the minimal number we reached the case E
        return visited[y][x];
    }

    // function for part1
    public static void cheat1s(char[][] map, int y, int x, AtomicInteger improvments) {

        int distanceStandard = findShortestPath(map);
        map[y][x] = '.';
        int nouvelleDistance = findShortestPath(map);
        if (nouvelleDistance <= distanceStandard - 100) {
            improvments.incrementAndGet();
        }

        // need to reinit the grid
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
        // if we reached the case E. we are done
        if (map[y][x] == 'E') {
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

    public static int cheat2s(String[][] map, Integer cheatThreshold) {

        return 0;
    }

}
