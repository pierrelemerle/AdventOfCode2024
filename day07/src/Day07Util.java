import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.io.IOException;

public class Day07Util {

    // reading the input file and storing it in a list of String
    public static List<String> readFileToList(String filePath) throws IOException {
        List<String> resultList = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                resultList.add(line);
            }
        }
        return resultList;
    }

    // split and convert each String to a list of Long
    public static List<Long> splitAndConvert(String parameter) {
        return Arrays.stream(parameter.split("\\s+")) // Split by spaces
                .map(part -> part.replaceAll("\\D", "")) // Remove non-digit characters
                .filter(part -> !part.isEmpty()) // Ensure it's not empty after cleaning
                .map(Long::parseLong) // Convert to Long
                .collect(Collectors.toList());
    }

    // call the recursive function and check all possibilities
    public static List<Long> generateAllResults(List<Long> nums) {
        List<Long> results = new ArrayList<>();
        if (nums == null || nums.size() < 2)
            return results;

        // Helper function to explore all combinations
        backtrack(nums, 2, nums.get(1), results);
        return results;
    }

    // recursive function to explore all the possibilities
    private static void backtrack(List<Long> nums, int index, Long current, List<Long> results) {
        if (index == nums.size()) {
            results.add(current); // Add the final result
            return;
        }
        // Add
        backtrack(nums, index + 1, current + nums.get(index), results);
        // Multiply
        backtrack(nums, index + 1, current * nums.get(index), results);
    }

}
