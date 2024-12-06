import java.io.*;

public class Main {

    public static void main(String[] args) {

        String filePath = "resources/input.txt";
        Integer startingX = 0, startingY = 0;

        try {
            char[][] charArray = Day06Util.readFileToList(filePath);

            // find the starting position
            for (int y = 0; y < charArray.length; y++) {
                for (int x = 0; x < charArray[y].length; x++) {
                    System.out.print(charArray[y][x] + " "); // debugging purpose
                    if (charArray[y][x] == '^') {
                        startingX = x;
                        startingY = y;
                    }
                }
                System.out.println(); // debugging purpose
            }

            // Create a new array with the same dimensions
            char[][] tabPart2 = new char[charArray.length][charArray[0].length];

            // Copy each row
            for (int i = 0; i < charArray.length; i++) {
                // Use System.arraycopy for efficient copying
                System.arraycopy(charArray[i], 0, tabPart2[i], 0, charArray[i].length);
            }

            // PART 1 of the puzzle
            Day06Util.movingFunction(charArray, startingY, startingX);

            Integer numberCases = 0;
            for (int y = 0; y < charArray.length; y++) {
                for (int x = 0; x < charArray[y].length; x++) {
                    System.out.print(charArray[y][x] + " "); // debugging purpose
                    if (charArray[y][x] == 'X') {
                        numberCases++;
                    }
                }
                System.out.println(); // debugging purpose
            }

            // PART 2 of the puzzle
            Integer numberInfiniteLoops = 0;
            Integer limitInitial = Day06Util.initialLine(tabPart2, startingY, startingX);
            for (int y = 0; y < tabPart2.length; y++) {
                for (int x = 0; x < tabPart2[y].length; x++) {
                    if (tabPart2[y][x] == '.') {
                        // removing the starting column from the initial positions for the obstacle
                        if ((x != startingX) || (y <= limitInitial) || (y > startingY)) {
                            tabPart2[y][x] = '#';
                            if (Day06Util.isInfiniteLoop(tabPart2, startingY, startingX)) {
                                numberInfiniteLoops++;
                            }
                            tabPart2[y][x] = '.';
                        }
                    }
                }
            }

            System.out.println("Le nombre total de case visitee pour la partie 1 est " + numberCases);
            System.out.println("Le nombre total d'obstacle potentiel pour la partie 2 est " + numberInfiniteLoops);

        } catch (IOException e) {
            System.err.println("Error reading the file: " + e.getMessage());
        }
    }

}