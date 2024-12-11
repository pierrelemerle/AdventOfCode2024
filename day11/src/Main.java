import java.io.IOException;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        String filePath = "resources/input.txt";

        try {
            List<Long> input = Helper.readFileToListLong(filePath);
            long[] stones = new long[input.size()];

            for (int i = 0; i < input.size(); i++) {
                stones[i] = input.get(i); // Unboxing Long to long
            }

            System.out.println(stones);

            // blink 25 times.
            for (int i = 0; i < 25; i++) {
                stones = Day11Util.blink(stones);
                System.out.println(i);
            }

            System.out.println("Le nombre de pierre apres avoir cligner des yeux 25 fois est " + stones.length);

        } catch (IOException e) {
            System.err.println("Error reading the file: " + e.getMessage());
        }
    }
}
