import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Helper {

    // reading the input file and storing it in a list of String
    public static List<String> readFileToListString(String filePath) throws IOException {
        List<String> resultList = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                resultList.add(line);
            }
        }
        return resultList;
    }

    // reading the input file and storing it in 2 dimension tab of characters
    public static char[][] readFileToTabTabChar(String filePath) throws IOException {
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

    // reading the input file and storing it in a list of characters
    public static List<Character> readFileToListChar(String filePath) throws IOException {
        List<Character> resultList = new ArrayList<>();
        try {
            // Read all lines as a single string
            String content = Files.readString(Paths.get(filePath));

            // Convert each character to the list
            for (char c : content.toCharArray()) {
                resultList.add(c);
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
        return resultList;
    }

    // reading the input file and storing it in a list of list of Integer
    public static List<List<Integer>> readFileToListListInteger(String filePath) throws IOException {
        List<List<Integer>> result = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] numbers = line.trim().split("\\s+"); // Split by whitespace
                List<Integer> row = new ArrayList<>();
                for (String num : numbers) {
                    row.add(Integer.parseInt(num));
                }
                result.add(row);
            }
        }

        return result;
    }

    // split and convert each String to a list of Long
    public static List<Long> splitAndConvert(String parameter) {
        return Arrays.stream(parameter.split("\\s+")) // Split by spaces
                .map(part -> part.replaceAll("\\D", "")) // Remove non-digit characters
                .filter(part -> !part.isEmpty()) // Ensure it's not empty after cleaning
                .map(Long::parseLong) // Convert to Long
                .collect(Collectors.toList());
    }

    // copy a char[][] into an other one to have 2 distinct objects
    public static char[][] deepCopyCharTabTab(char[][] source) {
        if (source == null) {
            return null;
        }

        char[][] result = new char[source.length][];
        for (int i = 0; i < source.length; i++) {
            result[i] = source[i].clone(); // Clone each row
        }
        return result;
    }

    // reading the input file and storing it in a list of characters
    public static List<Character> copyStringToListCharacters(String filePath) throws IOException {
        List<Character> resultList = new ArrayList<>();

        // Convert each character to the list
        for (char c : filePath.toCharArray()) {
            resultList.add(c);
        }

        return resultList;
    }

}
