import java.io.*;
import java.util.*;
import java.awt.Point;

public class Main {

    public static void main(String[] args) {

        String filePath = "resources/input.txt";

        // initialise an empty grid of the right size
        int[][] grid = new int[103][101];
        for (int y = 0; y < grid.length; y++) {
            for (int x = 0; x < grid[y].length; x++) {
                grid[y][x] = 0;
            }
        }

        Map<String, List<Point>> listRobots = parse(filePath);
        int nbRobots = listRobots.get("P").size();

        // for the part 1
        for (int robot = 0; robot < nbRobots; robot++) {
            Day14Util.placeAndMoveOneRobot(grid, listRobots.get("P").get(robot), listRobots.get("V").get(robot), 100);
        }
        int safetyFactor = Day14Util.calculateSafetyFactor(grid);
        System.out.println("Le facteur de securite apres 100 seconde pour la partie 1 est " + safetyFactor);

        // for the part 2. brute forcing by generating plenty of files, and
        // exploring patterns.
        // kind of noticed a cluster every 101 iteration. Hence the loop below to
        // explore deeper
        for (int i = 99; i < 10000; i += 101) {
            grid = new int[103][101];
            for (int robot = 0; robot < nbRobots; robot++) {
                Day14Util.placeAndMoveOneRobot(grid, listRobots.get("P").get(robot), listRobots.get("V").get(robot), i);
            }
            // Just need to create the testFiles directory before running it
            String fileString = "testFiles/test" + i + ".txt";
            Day14Util.printGrid(grid, fileString);
        }

    }

    public static Map<String, List<Point>> parse(String filePath) {
        Map<String, List<Point>> result = new HashMap<>();
        List<Point> pList = new ArrayList<>();
        List<Point> vList = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                // Extract coordinates for p and v using regex or string splitting
                String[] parts = line.split(" ");
                String pCoords = parts[0].split("=")[1]; // Get the coordinates after 'p='
                String vCoords = parts[1].split("=")[1]; // Get the coordinates after 'v='

                // Parse p and v coordinates into Point objects
                String[] pValues = pCoords.split(",");
                String[] vValues = vCoords.split(",");
                Point p = new Point(Integer.parseInt(pValues[0]), Integer.parseInt(pValues[1]));
                Point v = new Point(Integer.parseInt(vValues[0]), Integer.parseInt(vValues[1]));

                // Add the points to the respective lists
                pList.add(p);
                vList.add(v);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Add the lists to the map
        result.put("P", pList);
        result.put("V", vList);

        return result;
    }

}
