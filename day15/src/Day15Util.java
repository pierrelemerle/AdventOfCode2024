import java.awt.Point;
import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;

public class Day15Util {

    // to simulate movement of the robot for the whole instruction list.
    public static void moveRobotsPart1(char[][] map, List<Character> instructionList) {
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
    public static Long coordinateCalulationPart1(char[][] map) {
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

    // transform the part1 input map to the needed one for part2
    public static char[][] transformMapToPart2(char[][] mapPart1) {
        char[][] mapPart2 = new char[mapPart1.length][mapPart1[0].length * 2];

        for (int y = 0; y < mapPart1.length; y++) {
            for (int x = 0; x < mapPart1[y].length; x++) {
                if (mapPart1[y][x] == '#') {
                    mapPart2[y][2 * x] = '#';
                    mapPart2[y][2 * x + 1] = '#';
                }
                if (mapPart1[y][x] == '.') {
                    mapPart2[y][2 * x] = '.';
                    mapPart2[y][2 * x + 1] = '.';
                }
                if (mapPart1[y][x] == 'O') {
                    mapPart2[y][2 * x] = '[';
                    mapPart2[y][2 * x + 1] = ']';
                }
                if (mapPart1[y][x] == '@') {
                    mapPart2[y][2 * x] = '@';
                    mapPart2[y][2 * x + 1] = '.';
                }
            }
        }
        return mapPart2;
    }

    // to simulate movement of the robot for the whole instruction list.
    public static void moveRobotsPart2(char[][] map, List<Character> instructionList) {
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
            if (instructionList.get(i) == '^') {
                moveUp(map, robotPosition);
            } else if (instructionList.get(i) == '>') {
                moveRight(map, robotPosition);
            } else if (instructionList.get(i) == 'v') {
                moveDown(map, robotPosition);
            } else if (instructionList.get(i) == '<') {
                moveLeft(map, robotPosition);
            }
        }
    }

    // move the robot for a single move right for the part2 map
    private static void moveRight(char[][] map, Point robotPosition) {

        int y = robotPosition.y;
        int x = robotPosition.x;
        int nextY = y;
        int nextX = x + 1;

        if (map[nextY][nextX] == '.') {
            map[y][x] = '.';
            map[nextY][nextX] = '@';
            return;
        } else if (map[nextY][nextX] == '#') {
            // nothing to do
            return;
        }
        // other wise then it is a box need to verify if we will move, or Not at all
        while (map[nextY][nextX] != '.') {
            nextX++;
            // if we can not do this move then we quit
            if ((nextX >= map[nextY].length) || (map[nextY][nextX] == '#'))
                return;
        }
        map[nextY][x] = '.';
        map[nextY][x + 1] = '@';
        // update the last box
        map[nextY][nextX] = ']';
        // need to switch all the boxes and change all the char from y x+2 until y nextX
        for (int j = x + 2; j < nextX; j++) {
            if (map[nextY][j] == '[')
                map[nextY][j] = ']';
            else
                map[nextY][j] = '[';
        }
    }

    // move the robot for a single move Left for the part2 map
    private static void moveLeft(char[][] map, Point robotPosition) {

        int y = robotPosition.y;
        int x = robotPosition.x;
        int nextY = y;
        int nextX = x - 1;

        if (map[nextY][nextX] == '.') {
            map[y][x] = '.';
            map[nextY][nextX] = '@';
            return;
        } else if (map[nextY][nextX] == '#') {
            // nothing to do
            return;
        }
        // other wise then it is a box need to verify if we will move, or Not at all
        while (map[nextY][nextX] != '.') {
            nextX--;
            // we can not do this move
            if ((nextX == 0) || (map[nextY][nextX] == '#'))
                return;
        }
        map[nextY][x] = '.';
        map[nextY][x - 1] = '@';
        // update the last box
        map[nextY][nextX] = '[';
        // need to switch all the boxes and change all the char from y x+2 until y nextX
        for (int j = x - 2; j > nextX; j--) {
            if (map[nextY][j] == '[')
                map[nextY][j] = ']';
            else
                map[nextY][j] = '[';
        }
    }

    // move the robot for a single move for the part2 map
    private static void moveUp(char[][] map, Point robotPosition) {

        int y = robotPosition.y;
        int x = robotPosition.x;
        int nextY = y - 1;
        int nextX = x;

        if (map[nextY][nextX] == '.') {
            map[y][x] = '.';
            map[nextY][nextX] = '@';
            return;
        } else if (map[nextY][nextX] == '#') {
            // nothing to do
            return;
        }

        List<Point> pointToMove = new ArrayList<>();
        pointToMove.add(new Point(x, y));
        AtomicBoolean movePossible = new AtomicBoolean(true);

        moveUpRecursive(map, pointToMove, movePossible, y, x);
        if (!movePossible.get())
            return;

        // we need to update all the point to move in the list.
        // filter the duplicate to avoid issues
        pointToMove = getUniquePoints(pointToMove);
        // Sort the points
        pointToMove.sort((p1, p2) -> {
            if (p1.y != p2.y) {
                return Integer.compare(p1.y, p2.y); // Compare by y-coordinate
            }
            return Integer.compare(p1.x, p2.x); // Compare by x-coordinate if y is the same
        });

        for (int i = 0; i < pointToMove.size(); i++) {
            y = pointToMove.get(i).y;
            x = pointToMove.get(i).x;
            map[y - 1][x] = map[y][x];
            map[y][x] = '.';
        }

    }

    // move the robot for a single move for the part2 map
    private static void moveDown(char[][] map, Point robotPosition) {

        int y = robotPosition.y;
        int x = robotPosition.x;
        int nextY = y + 1;
        int nextX = x;

        if (map[nextY][nextX] == '.') {
            map[y][x] = '.';
            map[nextY][nextX] = '@';
            return;
        } else if (map[nextY][nextX] == '#') {
            // nothing to do
            return;
        }

        List<Point> pointToMove = new ArrayList<>();
        pointToMove.add(new Point(x, y));
        AtomicBoolean movePossible = new AtomicBoolean(true);

        moveDownRecursive(map, pointToMove, movePossible, y, x);
        if (!movePossible.get())
            return;

        // we need to update all the point to move in the list.
        // filter the duplicate to avoid issues
        pointToMove = getUniquePoints(pointToMove);
        // Sort the points in reverse order
        pointToMove.sort((p1, p2) -> {
            if (p1.y != p2.y) {
                return Integer.compare(p2.y, p1.y); // Compare by y-coordinate in descending order
            }
            return Integer.compare(p2.x, p1.x); // Compare by x-coordinate in descending order if y is the same
        });

        for (int i = 0; i < pointToMove.size(); i++) {
            y = pointToMove.get(i).y;
            x = pointToMove.get(i).x;
            map[y + 1][x] = map[y][x];
            map[y][x] = '.';
        }

    }

    private static void moveUpRecursive(char[][] map, List<Point> pointToMove, AtomicBoolean movePossible, int y,
            int x) {
        if (map[y - 1][x] == '#')
            movePossible.set(false);
        if (map[y - 1][x] == '[') {
            pointToMove.add(new Point(x, y - 1));
            pointToMove.add(new Point(x + 1, y - 1));
            moveUpRecursive(map, pointToMove, movePossible, y - 1, x);
            moveUpRecursive(map, pointToMove, movePossible, y - 1, x + 1);
        }
        if (map[y - 1][x] == ']') {
            pointToMove.add(new Point(x - 1, y - 1));
            pointToMove.add(new Point(x, y - 1));
            moveUpRecursive(map, pointToMove, movePossible, y - 1, x - 1);
            moveUpRecursive(map, pointToMove, movePossible, y - 1, x);
        }
    }

    private static void moveDownRecursive(char[][] map, List<Point> pointToMove, AtomicBoolean movePossible, int y,
            int x) {
        if (map[y + 1][x] == '#')
            movePossible.set(false);
        ;
        if (map[y + 1][x] == '[') {
            pointToMove.add(new Point(x, y + 1));
            pointToMove.add(new Point(x + 1, y + 1));
            moveDownRecursive(map, pointToMove, movePossible, y + 1, x);
            moveDownRecursive(map, pointToMove, movePossible, y + 1, x + 1);
        }
        if (map[y + 1][x] == ']') {
            pointToMove.add(new Point(x - 1, y + 1));
            pointToMove.add(new Point(x, y + 1));
            moveDownRecursive(map, pointToMove, movePossible, y + 1, x - 1);
            moveDownRecursive(map, pointToMove, movePossible, y + 1, x);
        }
    }

    // to calculate the result for part2
    public static Long coordinateCalulationPart2(char[][] map) {
        Long total = 0L;
        for (int y = 0; y < map.length; y++) {
            for (int x = 0; x < map[y].length; x++) {
                if (map[y][x] == '[') {
                    Long thisBox = 100L * y + x;
                    total += thisBox;
                }
            }
        }
        return total;
    }

    // filter the points via a SET to avoid issues in the points update
    private static List<Point> getUniquePoints(List<Point> points) {
        // Use a HashSet to filter out duplicates
        Set<Point> uniquePointsSet = new HashSet<>(points);
        // Convert back to a list
        return new ArrayList<>(uniquePointsSet);
    }

}
