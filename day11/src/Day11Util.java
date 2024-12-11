import java.util.Arrays;

public class Day11Util {

    // simulate a blink, so implement all the rules
    public static long[] blink(long[] input) {
        long[] result = new long[input.length * 2];
        int resultIndex = 0;

        for (int i = 0; i < input.length; i++) {
            long value = input[i];

            // Process the stone and handle size constraints
            resultIndex = changeOneStone(value, result, resultIndex);

        }

        // Return a copy of the array trimmed to the actual size
        return Arrays.copyOf(result, resultIndex);

    }

    // function to update one stone, based on the 3 rules
    private static int changeOneStone(long value, long[] input, int index) {
        if (value == 0) {
            // Rule 1: Replace 0 with 1
            input[index++] = 1L;
        } else {
            int numberDigits = (int) Math.log10(Math.abs(value)) + 1;
            if (numberDigits % 2 == 0) {
                // Rule 2: Split the number
                long divisor = (long) Math.pow(10, numberDigits / 2);
                input[index++] = value / divisor; // First part
                input[index++] = value % divisor; // Second part
            } else {
                // Rule 3: Multiply by 2024
                long multipliedValue = value * 2024;
                input[index++] = multipliedValue;
            }
        }
        return index;
    }
}
