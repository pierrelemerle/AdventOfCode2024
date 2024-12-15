import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {

        String filePath = "resources/input.txt";

        // initialise an empty grid of the right size
        List<Character> movements = new ArrayList<>();
        char[][] map = Helper.readFileToTabTabCharAndInstruction(filePath, movements);

        for (int y = 0; y < map.length; y++) {
            for (int x = 0; x < map[y].length; x++) {
                System.out.print(map[y][x]);
            }
            System.out.println();
        }
        System.out.println(movements);
        Day15Util.moveRobots(map, movements);

        for (int y = 0; y < map.length; y++) {
            for (int x = 0; x < map[y].length; x++) {
                System.out.print(map[y][x]);
            }
            System.out.println();
        }
        Long sumAllBoxes = Day15Util.coordinateCalulation(map);
        // for the part 1
        System.out.println("La somme des coordonees de toutes les boites pour la partie 1 est " + sumAllBoxes);

    }

}
