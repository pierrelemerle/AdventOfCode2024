import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

public class Day16Util {

    // Directions: {East, South, West, North}
    static int[][] directions = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };

    public static int generateAllPaths(char[][] map) {

        int bestScore = 0;
        // initialise
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
                visited[i][j] = 133588; // find that out to progressively optimise after couple of runs
            }
        }

        List<Integer> solutions = new ArrayList<>();
        oneStep(map, visited, 0, solutions, y, x, 'E');

        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                if (map[i][j] == 'E') {
                    bestScore = visited[i][j];
                }
            }
        }
        return bestScore;
    }

    private static void oneStep(char[][] map, int[][] visited, int currentNbSteps, List<Integer> solutions, int y,
            int x,
            char facing) {
        if (currentNbSteps > visited[y][x] + 1000) {
            return;
        }
        if (currentNbSteps < visited[y][x]) {
            visited[y][x] = currentNbSteps;
        }
        if (map[y][x] == 'E') {
            solutions.add(currentNbSteps);
        }
        // handle the 4 alternatives now (turn one side or the other, or 2 turns, or
        // step forward)
        // going south
        if (map[y + 1][x] != '#') {
            if (facing == 'S') {
                oneStep(map, visited, currentNbSteps + 1, solutions, y + 1, x, 'S');
            } else if (facing == 'W' || facing == 'E') {
                oneStep(map, visited, currentNbSteps + 1001, solutions, y + 1, x, 'S');
            }
        }
        // going North
        if (map[y - 1][x] != '#') {
            if (facing == 'N') {
                oneStep(map, visited, currentNbSteps + 1, solutions, y - 1, x, 'N');
            } else if (facing == 'W' || facing == 'E') {
                oneStep(map, visited, currentNbSteps + 1001, solutions, y - 1, x, 'N');
            }
        }
        // going West
        if (map[y][x - 1] != '#') {
            if (facing == 'W') {
                oneStep(map, visited, currentNbSteps + 1, solutions, y, x - 1, 'W');
            } else if (facing == 'N' || facing == 'S') {
                oneStep(map, visited, currentNbSteps + 1001, solutions, y, x - 1, 'W');
            }
        }
        // going East
        if (map[y][x + 1] != '#') {
            if (facing == 'E') {
                oneStep(map, visited, currentNbSteps + 1, solutions, y, x + 1, 'E');
            } else if (facing == 'N' || facing == 'S') {
                oneStep(map, visited, currentNbSteps + 1001, solutions, y, x + 1, 'E');
            }
        }
    }

    static Set<String> findBestTiles(char[][] maze, int bestScore) {
        // PriorityQueue for BFS
        Queue<State> queue = new PriorityQueue<>(Comparator.comparingInt(s -> s.score));
        boolean[][][] visited = new boolean[maze.length][maze[0].length][4];
        Set<String> bestTiles = new HashSet<>();
        List<List<int[]>> allBestPaths = new ArrayList<>();

        // Find start position
        int startX = 0, startY = 0;
        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze[i].length; j++) {
                if (maze[i][j] == 'S') {
                    startX = i;
                    startY = j;
                }
            }
        }

        // Add initial state
        queue.add(new State(startX, startY, 0, 0, new ArrayList<>()));

        while (!queue.isEmpty()) {
            State current = queue.poll();

            // Stop if this score exceeds the best score
            if (current.score > bestScore)
                continue;

            // Mark visited for this specific path and score
            if (visited[current.x][current.y][current.direction])
                continue;
            visited[current.x][current.y][current.direction] = true;

            // If we reach the end tile with the best score, store this path
            if (maze[current.x][current.y] == 'E' && current.score == bestScore) {
                current.path.add(new int[] { current.x, current.y });
                allBestPaths.add(current.path);
                continue;
            }

            // Explore next states
            for (State next : getNextStates(current, maze)) {
                if (next.score <= bestScore) {
                    queue.add(next);
                }
            }
        }

        // Mark all tiles from all best paths
        for (List<int[]> path : allBestPaths) {
            markPath(bestTiles, path);
        }

        return bestTiles;
    }

    static void markPath(Set<String> bestTiles, List<int[]> path) {
        for (int[] tile : path) {
            bestTiles.add(tile[0] + "," + tile[1]);
        }
    }

    static List<State> getNextStates(State current, char[][] maze) {
        List<State> nextStates = new ArrayList<>();

        // Move forward
        int newX = current.x + directions[current.direction][0];
        int newY = current.y + directions[current.direction][1];
        if (isInBounds(newX, newY, maze) && maze[newX][newY] != '#') {
            List<int[]> newPath = new ArrayList<>(current.path);
            newPath.add(new int[] { current.x, current.y });
            nextStates.add(new State(newX, newY, current.direction, current.score + 1, newPath));
        }

        // Rotate clockwise
        nextStates.add(new State(current.x, current.y, (current.direction + 1) % 4, current.score + 1000,
                new ArrayList<>(current.path)));

        // Rotate counterclockwise
        nextStates.add(new State(current.x, current.y, (current.direction + 3) % 4, current.score + 1000,
                new ArrayList<>(current.path)));

        return nextStates;
    }

    static boolean isInBounds(int x, int y, char[][] maze) {
        return x >= 0 && x < maze.length && y >= 0 && y < maze[0].length;
    }

    static class State {
        int x, y, direction, score;
        List<int[]> path;

        State(int x, int y, int direction, int score, List<int[]> path) {
            this.x = x;
            this.y = y;
            this.direction = direction;
            this.score = score;
            this.path = path;
        }
    }
}