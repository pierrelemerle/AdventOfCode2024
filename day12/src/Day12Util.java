import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.awt.geom.Point2D;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class Day12Util {

    public static int findASpecificReginCost(char[][] map, int y, int x) {
        AtomicInteger regionSize = new AtomicInteger(0);
        HashMap<Point2D, List<Character>> existingFence = new HashMap<>();
        char plantType = map[y][x];

        // check next area (if in the grid)
        buildWholeRegion(map, y, x, regionSize, existingFence, plantType);

        int costFence = 0;

        // PART1
        // costFence = existingFence.size();

        // PART" with the discount
        costFence = calculateFenceCostDiscount(existingFence, map.length);

        return regionSize.get() * costFence;
    }

    // call the recursive function and check all the Paths
    public static void buildWholeRegion(char[][] map, int y, int x, AtomicInteger regionSize,
            HashMap<Point2D, List<Character>> existingFence,
            char plantType) {
        regionSize.getAndIncrement();
        // remove the letter not to count it several times.
        map[y][x] = '.';

        // add the 4 fences in the fencelist if not there already, otherwise they should
        // be removed
        Point2D target = new Point2D.Double(y - 0.5, x);
        if (!existingFence.containsKey(target)) {
            existingFence.computeIfAbsent(target, k -> new ArrayList<>()).add('S');
        } else {
            existingFence.remove(target);
        }

        target = new Point2D.Double(y + 0.5, x);
        if (!existingFence.containsKey(target)) {
            existingFence.computeIfAbsent(target, k -> new ArrayList<>()).add('N');
        } else {
            existingFence.remove(target);
        }

        target = new Point2D.Double(y, x - 0.5);
        if (!existingFence.containsKey(target)) {
            existingFence.computeIfAbsent(target, k -> new ArrayList<>()).add('W');
        } else {
            existingFence.remove(target);
        }

        target = new Point2D.Double(y, x + 0.5);
        if (!existingFence.containsKey(target)) {
            existingFence.computeIfAbsent(target, k -> new ArrayList<>()).add('E');
        } else {
            existingFence.remove(target);
        }

        // look for 4 areas around and call the function again
        if ((x - 1 >= 0) && (plantType == map[y][x - 1]))
            buildWholeRegion(map, y, x - 1, regionSize, existingFence, plantType);
        if ((y - 1 >= 0) && (plantType == map[y - 1][x]))
            buildWholeRegion(map, y - 1, x, regionSize, existingFence, plantType);
        if ((x + 1 < map[y].length) && (plantType == map[y][x + 1]))
            buildWholeRegion(map, y, x + 1, regionSize, existingFence, plantType);
        if ((y + 1 < map.length) && (plantType == map[y + 1][x]))
            buildWholeRegion(map, y + 1, x, regionSize, existingFence, plantType);

    }

    private static int calculateFenceCostDiscount(HashMap<Point2D, List<Character>> existingFence, int dimension) {
        boolean touching = false;
        int nbFence = 0;
        List<Integer> fences = new ArrayList<>();

        for (double x = -0.5; x < dimension; x++) {
            fences = getMatchingYCoordinates(existingFence, x, 'S');
            Collections.sort(fences);
            // count the total
            for (int i = 0; i < dimension; i++) {
                if (fences.contains(i)) {
                    if (!touching) {
                        nbFence++;
                    }
                    touching = true;
                } else {
                    touching = false;
                }
            }
            touching = false;
            fences = getMatchingYCoordinates(existingFence, x, 'N');
            Collections.sort(fences);
            // count the total
            for (int i = 0; i < dimension; i++) {
                if (fences.contains(i)) {
                    if (!touching) {
                        nbFence++;
                    }
                    touching = true;
                } else {
                    touching = false;
                }
            }
            touching = false;
            fences = new ArrayList<>();
        }

        for (double y = -0.5; y < dimension; y++) {
            fences = getMatchingXCoordinates(existingFence, y, 'W');
            Collections.sort(fences);
            // count the total
            for (int i = 0; i < dimension; i++) {
                if (fences.contains(i)) {
                    if (!touching) {
                        nbFence++;
                    }
                    touching = true;
                } else {
                    touching = false;
                }
            }
            touching = false;
            fences = getMatchingXCoordinates(existingFence, y, 'E');
            Collections.sort(fences);
            // count the total
            for (int i = 0; i < dimension; i++) {
                if (fences.contains(i)) {
                    if (!touching) {
                        nbFence++;
                    }
                    touching = true;
                } else {
                    touching = false;
                }
            }
            touching = false;
            fences = new ArrayList<>();
        }
        return nbFence;
    }

    private static List<Integer> getMatchingYCoordinates(
            HashMap<Point2D, List<Character>> map,
            double targetX,
            char targetChar) {
        List<Integer> result = new ArrayList<>();

        for (Map.Entry<Point2D, List<Character>> entry : map.entrySet()) {
            Point2D key = entry.getKey();
            List<Character> value = entry.getValue();

            // Check if x-coordinate matches and list contains the character
            if (key.getX() == targetX && value.contains(targetChar)) {
                result.add((int) key.getY()); // Cast y-coordinate to integer
            }
        }

        return result;
    }

    private static List<Integer> getMatchingXCoordinates(
            HashMap<Point2D, List<Character>> map,
            double targetY,
            char targetChar) {
        List<Integer> result = new ArrayList<>();

        for (Map.Entry<Point2D, List<Character>> entry : map.entrySet()) {
            Point2D key = entry.getKey();
            List<Character> value = entry.getValue();

            // Check if x-coordinate matches and list contains the character
            if (key.getY() == targetY && value.contains(targetChar)) {
                result.add((int) key.getX()); // Cast y-coordinate to integer
            }
        }

        return result;
    }

}
