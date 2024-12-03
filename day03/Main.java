import java.io.*;
import java.nio.file.*;
import java.util.regex.*;

public class Main {
    public static void main(String[] args) {
        // File path to the input file
        String filePath = "inputday3.txt"; 

        try {
            // Read the entire content of the file
            String input = Files.readString(Path.of(filePath));
            
            // Regular expressions for instructions
            String mulRegex = "mul\\((\\d{1,3}),(\\d{1,3})\\)";
            String doRegex = "do\\(\\)";
            String dontRegex = "don't\\(\\)";
            
            // Track the enabled/disabled state for mul instructions
            boolean isEnabled = true;
            int totalSum = 0;

            // Combine all regex patterns for a single scan
            String combinedRegex = String.join("|", mulRegex, doRegex, dontRegex);
            Pattern combinedPattern = Pattern.compile(combinedRegex);
            Matcher matcher = combinedPattern.matcher(input);

            // Process each match
            while (matcher.find()) {
                if (matcher.group().matches(doRegex)) {
                    // Enable future mul instructions
                    isEnabled = true;
                } else if (matcher.group().matches(dontRegex)) {
                    // Disable future mul instructions
                    isEnabled = false;
                } else if (matcher.group().matches(mulRegex)) {
                    // Process mul instructions if enabled
                    if (isEnabled) {
                        int num1 = Integer.parseInt(matcher.group(1));
                        int num2 = Integer.parseInt(matcher.group(2));
                        totalSum += num1 * num2;
                    }
                }
            }

            // Output the total sum
            System.out.println("Total sum of valid and enabled mul instructions: " + totalSum);
        } catch (IOException e) {
            System.err.println("Error reading the input file: " + e.getMessage());
        }
    }
}