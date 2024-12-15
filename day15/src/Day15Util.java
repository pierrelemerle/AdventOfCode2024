import java.awt.Point;
import java.util.*;

public class Day15Util {

    // to simulate movement of the robot for the whole instruction list.
    public static void moveRobots(char[][] map, List<Character> instructionList) {
        for (int i = 0; i < instructionList.size(); i++) {
            Point robotPosition = new Point();
            for (int y = 0; y < map.length; y++) {
                for (int x = 0; x < map[y].length; x++) {
                    if (map[y][x] == '@') {
                        robotPosition.x = x;
                        robotPosition.y = y;
                    }
                }
            }
            makeOneMove(map, instructionList.get(i), robotPosition.y, robotPosition.x);
        }
    }

    // to calculate the result for part1
    public static Long coordinateCalulation(char[][] map) {
        Long total = 0L;
        for (int y = 0; y < map.length; y++) {
            for (int x = 0; x < map[y].length; x++) {
                if (map[y][x] == 'O') {
                    Long thisBox = 100L * y + x;
                    total += thisBox;
                }
            }
        }
        return total;
    }

    // move the robot for a single move
    private static void makeOneMove(char[][] map, Character instruction, int y, int x) {

        int nextY = y;
        int nextX = x;
        if (instruction == '^') {
            nextY--;
        } else if (instruction == '>') {
            nextX++;
        } else if (instruction == '<') {
            nextX--;
        } else if (instruction == 'v') {
            nextY++;
        }
        if (map[nextY][nextX] == '.') {
            map[y][x] = '.';
            map[nextY][nextX] = '@';
            return;
        } else if (map[nextY][nextX] == '#') {
            // nothing to do
            return;
        }
        // need to verify if we will move, or Not at all
        if (instruction == '^') {
            while (map[nextY][nextX] != '.') {
                nextY--;
                // we can not do this move
                if ((nextY == 0) || (map[nextY][nextX] == '#'))
                    return;
            }
            // we do the whole move
            map[y][x] = '.';
            map[y - 1][x] = '@';
            map[nextY][nextX] = 'O';
        } else if (instruction == '>') {
            while (map[nextY][nextX] != '.') {
                nextX++;
                // we can not do this move
                if ((nextX >= map[nextY].length) || (map[nextY][nextX] == '#'))
                    return;
            }
            map[y][x] = '.';
            map[y][x + 1] = '@';
            map[nextY][nextX] = 'O';
        } else if (instruction == '<') {
            while (map[nextY][nextX] != '.') {
                nextX--;
                // we can not do this move
                if ((nextX == 0) || (map[nextY][nextX] == '#'))
                    return;
            }
            map[y][x] = '.';
            map[y][x - 1] = '@';
            map[nextY][nextX] = 'O';
        } else if (instruction == 'v') {
            while (map[nextY][nextX] != '.') {
                nextY++;
                // we can not do this move
                if ((nextY >= map.length) || (map[nextY][nextX] == '#'))
                    return;
            }
            map[y][x] = '.';
            map[y + 1][x] = '@';
            map[nextY][nextX] = 'O';
        }
    }
}
