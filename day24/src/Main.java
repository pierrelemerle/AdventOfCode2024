import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        try {
            Scanner scanner = new Scanner(new File("resources/input.txt"));
            List<String> pointsValues = new ArrayList<>();
            List<String> rules = new ArrayList<>();

            // Parse the part 1 of the file : rules
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (line.isEmpty())
                    break; // Separator between rules and updates
                pointsValues.add(line);
            }

            // Parse the part 2 of the files : updates
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                rules.add(line);
            }
            scanner.close();

            // Process rules into a map
            Map<String, Boolean> points = new HashMap<>();
            for (String pointValue : pointsValues) {
                String[] parts = pointValue.split(":");
                points.put(parts[0].trim(), Day24Util.parseCustomBoolean(parts[1].trim()));
            }
            Day24Util.calculateAllPoints(points, rules);

            // PART 1
            for (Map.Entry<String, Boolean> entry : points.entrySet()) {
                System.out.println("Key: " + entry.getKey() + ", Value: " + entry.getValue());
            }

            Day24Util.printAllZValues(points);
            // PART 2
            // reuse part1 function but building the networks of 3 in a Set of List

        } catch (IOException e) {
            System.err.println("Erreur lors de la lecture du fichier : " + e.getMessage());
        }
    }

}
