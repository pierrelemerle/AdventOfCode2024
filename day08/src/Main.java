import java.io.IOException;

public class Main {

    public static void main(String[] args) {

        String filePath = "resources/input.txt";

        try {
            char[][] parameterTab = Helper.readFileToTabTabChar(filePath);
            Integer numberFinalPositions = 0;
            char[][] antinodeTab = Helper.deepCopy(parameterTab);

            // go through the map, and check for each antenna, if there are similar antennas
            // and if they create antinode
            for (int y = 0; y < parameterTab.length; y++) {
                for (int x = 0; x < parameterTab[y].length; x++) {
                    if (parameterTab[y][x] != '.') {
                        // look for similar letters and update antinode in the antinodeMap
                        Day08Util.findAndPlaceAntinode(parameterTab, antinodeTab, y, x);
                    }
                }
            }

            // final calculation for part1 find the number of Antinode in the final map
            for (int y = 0; y < antinodeTab.length; y++) {
                for (int x = 0; x < antinodeTab[y].length; x++) {
                    if (antinodeTab[y][x] == '#') {
                        numberFinalPositions++;
                    }
                }
            }

            System.out.println(
                    "Le nombre total de location avec un antinode pour la partie 1 est " + numberFinalPositions);

            // part 2 - all similar but calling the 'AllAntinode' function instead. and
            // counting all the antenas as Antinodes aswell
            antinodeTab = Helper.deepCopy(parameterTab);
            numberFinalPositions = 0;

            // go through the map, and check for each antenna, if there are similar antennas
            // and if they create antinode
            for (int y = 0; y < parameterTab.length; y++) {
                for (int x = 0; x < parameterTab[y].length; x++) {
                    if (parameterTab[y][x] != '.') {
                        // look for similar letters and update antinode in the antinodeMap
                        Day08Util.findAndPlaceAllAntinode(parameterTab, antinodeTab, y, x);
                    }
                }
            }

            // final calculation for part1 find the number of Antinode in the final map
            for (int y = 0; y < antinodeTab.length; y++) {
                for (int x = 0; x < antinodeTab[y].length; x++) {
                    // anything counts as an antinode now.
                    if (antinodeTab[y][x] != '.') {
                        numberFinalPositions++;
                    }
                }
            }
            System.out.println(
                    "Le nombre total de location avec un antinode pour la partie 2 est " + numberFinalPositions);

        } catch (IOException e) {
            System.err.println("Error reading the file: " + e.getMessage());
        }
    }
}
