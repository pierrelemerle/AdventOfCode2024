
public class Day08Util {

    // place Antinode within the rule in part1
    public static void findAndPlaceAntinode(char[][] parameterTab, char[][] antinodeTab, int positionY,
            int positionX) {
        // look in the whole tab if there is a smiilar signal
        for (int y = 0; y < parameterTab.length; y++) {
            for (int x = 0; x < parameterTab[y].length; x++) {
                if ((parameterTab[y][x] == parameterTab[positionY][positionX]) && (x != positionX || y != positionY)) {
                    // need to find the difference between the coordinate.
                    int differenceX = positionX - x;
                    int differenceY = positionY - y;
                    int antinodeX = positionX + differenceX;
                    int antinodeY = positionY + differenceY;

                    // find out if the coordinate belongs in the map or not
                    if (belongToGrid(parameterTab, antinodeY, antinodeX)) {
                        antinodeTab[antinodeY][antinodeX] = '#';
                    }
                }
            }
        }
    }

    // place Antinode within the rule in part2
    public static void findAndPlaceAllAntinode(char[][] parameterTab, char[][] antinodeTab, int positionY,
            int positionX) {
        // look in the whole tab if there is a smiilar signal
        for (int y = 0; y < parameterTab.length; y++) {
            for (int x = 0; x < parameterTab[y].length; x++) {
                if ((parameterTab[y][x] == parameterTab[positionY][positionX]) && (x != positionX || y != positionY)) {
                    // need to find the difference between the coordinate.
                    int differenceX = positionX - x;
                    int differenceY = positionY - y;

                    int antinodeX = positionX + differenceX;
                    int antinodeY = positionY + differenceY;
                    // find out if the coordinate belongs in the map or not
                    while (belongToGrid(parameterTab, antinodeY, antinodeX)) {
                        antinodeTab[antinodeY][antinodeX] = '#';
                        antinodeX += differenceX;
                        antinodeY += differenceY;
                    }
                }
            }
        }
    }

    // a quick function that checks if the coordinate belongs to the grid
    public static boolean belongToGrid(char[][] parameterTab, int antinodeY, int antinodeX) {
        return ((antinodeY >= 0) && (antinodeX >= 0) && (antinodeY < parameterTab.length)
                && (antinodeX < parameterTab[antinodeY].length));
    }

}
