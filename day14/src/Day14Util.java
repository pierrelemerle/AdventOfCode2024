import java.awt.Point;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Day14Util {

    // to simulate movement of one specific robot during nbSecond of times.
    public static void placeAndMoveOneRobot(int[][] grid, Point position, Point velocity, int nbSeconds) {

        // initial position
        grid[position.y][position.x]++;
        moveOneRobot(grid, position, velocity, nbSeconds);

    }

    // to simulate movement of one specific robot during nbSecond of times.
    public static void moveOneRobot(int[][] grid, Point position, Point velocity, int nbSeconds) {
        int velocityX = velocity.x;
        int velocityY = velocity.y;
        int nouveauX = position.x;
        int nouveauY = position.y;
        int sizeY = grid.length;
        int sizeX = grid[0].length;

        for (int i = 0; i < nbSeconds; i++) {
            grid[nouveauY][nouveauX]--;

            nouveauX += velocityX;
            nouveauY += velocityY;

            // if it went outside / after the grid
            if (nouveauX >= sizeX) {
                nouveauX -= sizeX;
            }
            if (nouveauY >= sizeY) {
                nouveauY -= sizeY;
            }

            // if it went outside / before the grid
            if (nouveauX < 0) {
                nouveauX += sizeX;
            }
            if (nouveauY < 0) {
                nouveauY += sizeY;
            }
            grid[nouveauY][nouveauX]++;
        }

    }

    // function specific to part1 to calculate the 4 frames values
    public static int calculateSafetyFactor(int[][] grid) {
        int frame1 = 0;
        int frame2 = 0;
        int frame3 = 0;
        int frame4 = 0;
        int sizeY = grid.length;
        int sizeX = grid[0].length;

        // Frame1
        for (int y = 0; y < (sizeY - 1) / 2; y++) {
            for (int x = 0; x < (sizeX - 1) / 2; x++) {
                frame1 += grid[y][x];
            }
        }

        // Frame2
        for (int y = 0; y < (sizeY - 1) / 2; y++) {
            for (int x = (sizeX - 1) / 2 + 1; x < sizeX; x++) {
                frame2 += grid[y][x];
            }
        }

        // Frame3
        for (int y = (sizeY - 1) / 2 + 1; y < sizeY; y++) {
            for (int x = 0; x < (sizeX - 1) / 2; x++) {
                frame3 += grid[y][x];
            }
        }

        // Frame4
        for (int y = (sizeY - 1) / 2 + 1; y < sizeY; y++) {
            for (int x = (sizeX - 1) / 2 + 1; x < sizeX; x++) {
                frame4 += grid[y][x];
            }
        }

        return frame1 * frame2 * frame3 * frame4;
    }

    // replace the math (int) grid to a more printable one with Char
    public static char[][] prettyGrid(int[][] grid) {

        char[][] printedGrid = new char[grid.length][grid[0].length];
        for (int y = 0; y < grid.length; y++) {
            for (int x = 0; x < grid[y].length; x++) {
                if (grid[y][x] > 0) {
                    printedGrid[y][x] = '#';
                } else {
                    printedGrid[y][x] = ' ';
                }
            }
        }
        return printedGrid;
    }

    // function for part2, printing the grid with # in a txt file to explore
    // patterns
    public static void printGrid(int[][] grid, String fileName) {
        char[][] printedGrid = prettyGrid(grid);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (int y = 0; y < printedGrid.length; y++) {
                for (int x = 0; x < printedGrid[y].length; x++) {
                    writer.write(printedGrid[y][x]);
                }
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("An error occurred while writing to the file: " + e.getMessage());
        }
    }

}
