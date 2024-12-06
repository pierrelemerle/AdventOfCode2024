import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class Day06Util {

    // reading the input file and storing it in 2 dimension tab of characters
    public static char[][] readFileToList(String filePath) throws IOException {
        List<String> resultList = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                resultList.add(line);
            }
        }
        // Create a char[][] with size equal to the list size
        char[][] result = new char[resultList.size()][];

        // Populate the char[][] using each string in the list
        for (int i = 0; i < resultList.size(); i++) {
            result[i] = resultList.get(i).toCharArray();
        }
        return result;
    }

    // template function to make 1 move for the guard
    public static boolean moveOneStep(char[][] tab, AtomicInteger currentY, AtomicInteger currentX,
            StringBuilder direction) {
        Integer LastLine = tab.length - 1;
        Integer LastColumn = tab[currentY.get()].length - 1;

        if (direction.toString().equals("up")) {
            if (currentY.get() <= 0) {
                return false;
            } else {
                if (tab[currentY.get() - 1][currentX.get()] != '#') {
                    currentY.getAndDecrement();
                } else if ((currentX.get() == LastColumn) || (tab[currentY.get()][currentX.get() + 1] != '#')) {
                    currentX.getAndIncrement();
                    direction.setLength(0);
                    direction.append("right");
                } else if ((currentY.get() == LastLine) || (tab[currentY.get() + 1][currentX.get()] != '#')) {
                    currentY.getAndIncrement();
                    direction.setLength(0);
                    direction.append("down");
                } else if ((currentX.get() == 0) || (tab[currentY.get()][currentX.get() - 1] != '#')) {
                    currentX.getAndDecrement();
                    direction.setLength(0);
                    direction.append("left");
                }
            }
        } else if (direction.toString().equals("right")) {
            if (currentX.get() >= LastColumn) {
                return false;
            } else {
                if (tab[currentY.get()][currentX.get() + 1] != '#') {
                    currentX.getAndIncrement();
                } else if ((currentY.get() == LastColumn) || (tab[currentY.get() + 1][currentX.get()] != '#')) {
                    currentY.getAndIncrement();
                    direction.setLength(0);
                    direction.append("down");
                } else if ((currentX.get() == 0) || (tab[currentY.get()][currentX.get() - 1] != '#')) {
                    currentX.getAndDecrement();
                    direction.setLength(0);
                    direction.append("left");
                } else if ((currentY.get() == 0) || (tab[currentY.get() - 1][currentX.get()] != '#')) {
                    currentY.getAndDecrement();
                    direction.setLength(0);
                    direction.append("up");
                }
            }
        } else if (direction.toString().equals("down")) {
            if (currentY.get() >= LastColumn) {
                return false;
            } else {
                if (tab[currentY.get() + 1][currentX.get()] != '#') {
                    currentY.getAndIncrement();
                } else if ((currentX.get() == 0) || (tab[currentY.get()][currentX.get() - 1] != '#')) {
                    currentX.getAndDecrement();
                    direction.setLength(0);
                    direction.append("left");
                } else if ((currentY.get() == 0) || (tab[currentY.get() - 1][currentX.get()] != '#')) {
                    currentY.getAndDecrement();
                    direction.setLength(0);
                    direction.append("up");
                } else if ((currentX.get() == LastColumn) || (tab[currentY.get()][currentX.get() + 1] != '#')) {
                    currentX.getAndIncrement();
                    direction.setLength(0);
                    direction.append("right");
                }
            }
        } else if (direction.toString().equals("left")) {
            if (currentX.get() <= 0) {
                return false;
            } else {
                if (tab[currentY.get()][currentX.get() - 1] != '#') {
                    currentX.getAndDecrement();
                } else if ((currentY.get() == 0) || (tab[currentY.get() - 1][currentX.get()] != '#')) {
                    currentY.getAndDecrement();
                    direction.setLength(0);
                    direction.append("up");
                } else if ((currentX.get() == LastColumn) || (tab[currentY.get()][currentX.get() + 1] != '#')) {
                    currentX.getAndIncrement();
                    direction.setLength(0);
                    direction.append("right");
                } else if ((currentY.get() == LastColumn) || (tab[currentY.get() + 1][currentX.get()] != '#')) {
                    currentY.getAndIncrement();
                    direction.setLength(0);
                    direction.append("down");
                }
            }
        }
        return true;
    }

    // function for the part1 moving the guard around and replacing each visited
    // case by a X
    public static void movingFunction(char[][] tab, int startingY, int startingX) {

        AtomicInteger currentX = new AtomicInteger(startingX);
        AtomicInteger currentY = new AtomicInteger(startingY);
        boolean keepgoing = true;
        // first direction is always up
        StringBuilder direction = new StringBuilder("up");

        while (keepgoing) {
            tab[currentY.get()][currentX.get()] = 'X';
            keepgoing = moveOneStep(tab, currentY, currentX, direction);
        }
    }

    // function for the part 2. checking if Yes or No we are in an infinite loop
    public static boolean isInfiniteLoop(char[][] tab, int startingY, int startingX) {

        AtomicInteger currentX = new AtomicInteger(startingX);
        AtomicInteger currentY = new AtomicInteger(startingY);
        boolean keepgoing = true;
        // first direction is always up
        StringBuilder direction = new StringBuilder("up");
        Integer nbLoop = 0;
        // using this as an escape from what at this stage can be considered infinite
        Integer infiniteLoopEscapeValue = 100000;

        // reusing the move algorithm from part1 but with the escape value as a
        // protection
        while ((keepgoing) && (nbLoop < infiniteLoopEscapeValue)) {
            nbLoop++;
            keepgoing = moveOneStep(tab, currentY, currentX, direction);
        }
        if (nbLoop >= infiniteLoopEscapeValue) {
            return true;
        } else {
            return false;
        }

    }

    // allow us to identify the initial line the guard does, and to ignore these
    // positions in the final calculation
    public static Integer initialLine(char[][] tab, int startingY, int startingX) {
        for (Integer y = startingY; y > 0; y--) {
            if (tab[y][startingX] == '#') {
                return y;
            }

        }
        return 0;

    }

}
