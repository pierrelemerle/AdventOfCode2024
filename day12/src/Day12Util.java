import java.util.ArrayList;
import java.util.Collections;
import java.awt.geom.Point2D;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class Day12Util {

    public static int findASpecificReginCost(char[][] map, int y, int x) {
        AtomicInteger regionSize = new AtomicInteger(0);
        List<Point2D> existingFence = new ArrayList<>();
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
            List<Point2D> existingFence,
            char plantType) {
        regionSize.getAndIncrement();
        // remove the letter not to count it several times.
        map[y][x] = '.';

        // add the 4 fences in the fencelist if not there already, otherwise they should
        // be removed
        Point2D target = new Point2D.Double(y - 0.5, x);
        if (!existingFence.contains(target)) {
            existingFence.add(target);
        } else {
            existingFence.remove(target);
        }

        target = new Point2D.Double(y + 0.5, x);
        if (!existingFence.contains(target)) {
            existingFence.add(target);
        } else {
            existingFence.remove(target);
        }

        target = new Point2D.Double(y, x - 0.5);
        if (!existingFence.contains(target)) {
            existingFence.add(target);
        } else {
            existingFence.remove(target);
        }

        target = new Point2D.Double(y, x + 0.5);
        if (!existingFence.contains(target)) {
            existingFence.add(target);
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

    // private static int calculateFenceCostDiscount(List<Point2D> existingFence) {
    // int totalcost = existingFence.size();
    // boolean touching = false;

    // for (int i = 0; i < existingFence.size(); i++) {
    // Point2D target = existingFence.get(i);
    // if (target.getX() % 1 == 0.5) {
    // for (int j = 0; j < existingFence.size(); j++) {
    // if (existingFence.get(j).getY() == target.getY()
    // && (existingFence.get(j).getX() == target.getX() - 1
    // || existingFence.get(j).getX() == target.getX() + 1))
    // touching = true;
    // }
    // } else {
    // for (int j = 0; j < existingFence.size(); j++) {
    // if (existingFence.get(j).getX() == target.getX()
    // && (existingFence.get(j).getY() == target.getY() - 1
    // || existingFence.get(j).getY() == target.getY() + 1))
    // touching = true;
    // }
    // }
    // if (touching)
    // totalcost--;
    // touching = false;
    // }
    // System.out.println(totalcost);
    // return totalcost;
    // }

    private static int calculateFenceCostDiscount(List<Point2D> existingFence, int dimension) {
        boolean touching = false;
        int nbFence = 0;
        List<Integer> fences = new ArrayList<>();

        for (double x = -0.5; x < dimension; x++) {
            for (int i = 0; i < existingFence.size(); i++) {
                if (existingFence.get(i).getX() == x)
                    fences.add((int) existingFence.get(i).getY());
            }
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
            for (int i = 0; i < existingFence.size(); i++) {
                if (existingFence.get(i).getY() == y)
                    fences.add((int) existingFence.get(i).getX());
            }
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

        // do same for y.
        return nbFence;
    }

}
