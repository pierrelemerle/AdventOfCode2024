import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {

        String filePath = "resources/input.txt";

        // initialise an empty grid of the right size
        List<Character> movements = new ArrayList<>();
        char[][] mapPart1 = Helper.readFileToTabTabCharAndInstruction(filePath, movements);
        char[][] mapPart2 = Day15Util.transformMapToPart2(mapPart1);

        // for the part 1
        Day15Util.moveRobotsPart1(mapPart1, movements);
        Long sumAllBoxes = Day15Util.coordinateCalulationPart1(mapPart1);
        System.out.println("La somme des coordonees de toutes les boites pour la partie 1 est " + sumAllBoxes);

        // for the part 2
        for (int y = 0; y < mapPart2.length; y++) {
            for (int x = 0; x < mapPart2[y].length; x++) {
                System.out.print(mapPart2[y][x]);
            }
            System.out.println();
        }

        Day15Util.moveRobotsPart2(mapPart2, movements);

        // for the part 2
        for (int y = 0; y < mapPart2.length; y++) {
            for (int x = 0; x < mapPart2[y].length; x++) {
                System.out.print(mapPart2[y][x]);
            }
            System.out.println();
        }
        Long sumAllBoxes2 = Day15Util.coordinateCalulationPart2(mapPart2);
        System.out.println("La somme des coordonees de toutes les boites pour la partie 2 est " + sumAllBoxes2);

    }

}
