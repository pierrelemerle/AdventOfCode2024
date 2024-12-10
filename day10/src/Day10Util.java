import java.util.concurrent.atomic.AtomicInteger;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

public class Day10Util {

    // call the recursive function and check all the Paths
    public static AtomicInteger generateAllPaths(int[][] input) {
        AtomicInteger nbpath = new AtomicInteger(0);
        if (input == null)
            return nbpath;

        // look for each 0 (start of the path) in the grid
        // debug
        for (int y = 0; y < input.length; y++) {
            for (int x = 0; x < input[y].length; x++) {
                if (input[y][x] == 0) {
                    // call the recursive function for each of them
                    List<Point> cheminExistant = new ArrayList<>();
                    explore(input, y, x, nbpath, cheminExistant);
                }
            }
        }
        return nbpath;
    }

    // recursive function to explore all the possibilities
    private static void explore(int[][] input, int y, int x, AtomicInteger nbpath, List<Point> cheminExistant) {

        int indice = input[y][x];

        // if the indice is 9, then we have a complete path
        if (indice == 9) {
            Point target = new Point(y, x);
            // for part 1 only. (simply remove the if for part2 calculation)
            if (!cheminExistant.contains(target)) {
                cheminExistant.add(target);
                nbpath.incrementAndGet();
            }
        }

        // check all 4 possibilities, verify that they are in the grid and call the
        // recursive function on the new place
        if ((x + 1) < input[y].length && input[y][x + 1] == indice + 1) {
            explore(input, y, x + 1, nbpath, cheminExistant);
        }
        if ((y + 1) < input.length && input[y + 1][x] == indice + 1) {
            explore(input, y + 1, x, nbpath, cheminExistant);
        }
        if ((x - 1) >= 0 && input[y][x - 1] == indice + 1) {
            explore(input, y, x - 1, nbpath, cheminExistant);
        }
        if ((y - 1) >= 0 && input[y - 1][x] == indice + 1) {
            explore(input, y - 1, x, nbpath, cheminExistant);
        }

        // exit the function if no paths are found
        return;

    }

}
