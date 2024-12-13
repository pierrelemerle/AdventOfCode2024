import java.io.*;
import java.nio.file.*;
import java.util.*;
import java.util.regex.*;

public class Main {

    public static void main(String[] args) {

        String filePath = "resources/input.txt";

        try {
            Long minimalToken = 0L;

            Map<String, List<Long>> result = parseValues(filePath);
            System.out.println("AX Values: " + result.get("AX"));
            System.out.println("AY Values: " + result.get("AY"));
            System.out.println("BX Values: " + result.get("BX"));
            System.out.println("BY Values: " + result.get("BY"));
            System.out.println("X Values: " + result.get("X"));
            System.out.println("Y Values: " + result.get("Y"));

            for (int i = 0; i < result.get("AX").size(); i++) {
                Long Ax = result.get("AX").get(i);
                Long Ay = result.get("AY").get(i);
                Long Bx = result.get("BX").get(i);
                Long By = result.get("BY").get(i);
                Long X = result.get("X").get(i);
                Long Y = result.get("Y").get(i);

                minimalToken += Day13Util.nbTokenNeeded(Ax, Bx, Ay, By, X, Y);
            }

            System.out.println("Le cout minimal de token pour tout gagner pour la partie 1 est " + minimalToken);

        } catch (IOException e) {
            System.err.println("Error reading the file: " + e.getMessage());
        }
    }

    public static Map<String, List<Long>> parseValues(String filePath) throws IOException {
        // Create separate lists for AX, AY, BX, BY, X, Y
        List<Long> axValues = new ArrayList<>();
        List<Long> ayValues = new ArrayList<>();
        List<Long> bxValues = new ArrayList<>();
        List<Long> byValues = new ArrayList<>();
        List<Long> xValues = new ArrayList<>();
        List<Long> yValues = new ArrayList<>();

        // Regex patterns for parsing AX, AY, BX, BY, and Prize X, Y
        Pattern buttonAPattern = Pattern.compile("Button A: X[+=](\\d+), Y[+=](\\d+)");
        Pattern buttonBPattern = Pattern.compile("Button B: X[+=](\\d+), Y[+=](\\d+)");
        Pattern prizePattern = Pattern.compile("Prize: X=(\\d+), Y=(\\d+)");

        // Read the file line by line
        List<String> lines = Files.readAllLines(Paths.get(filePath));

        for (String line : lines) {
            Matcher aMatcher = buttonAPattern.matcher(line);
            Matcher bMatcher = buttonBPattern.matcher(line);
            Matcher prizeMatcher = prizePattern.matcher(line);

            if (aMatcher.find()) {
                // Add AX and AY values
                axValues.add(Long.parseLong(aMatcher.group(1)));
                ayValues.add(Long.parseLong(aMatcher.group(2)));
            } else if (bMatcher.find()) {
                // Add BX and BY values
                bxValues.add(Long.parseLong(bMatcher.group(1)));
                byValues.add(Long.parseLong(bMatcher.group(2)));
            } else if (prizeMatcher.find()) {
                // Add Prize X and Y values
                xValues.add(Long.parseLong(prizeMatcher.group(1)));
                yValues.add(Long.parseLong(prizeMatcher.group(2)));
            }
        }

        // Store results in a map
        Map<String, List<Long>> resultMap = new HashMap<>();
        resultMap.put("AX", axValues);
        resultMap.put("AY", ayValues);
        resultMap.put("BX", bxValues);
        resultMap.put("BY", byValues);
        resultMap.put("X", xValues);
        resultMap.put("Y", yValues);

        return resultMap;
    }

}
