import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(new File("input.txt"));
        List<String> rulesInput = new ArrayList<>();
        List<List<Integer>> updates = new ArrayList<>();
        
        // Parse the part 1 of the file : rules
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if (line.isEmpty()) break; // Separator between rules and updates
            rulesInput.add(line);
        }
        
        // Parse the part 2 of the files : updates
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            List<Integer> update = new ArrayList<>();
            for (String page : line.split(",")) {
                update.add(Integer.parseInt(page));
            }
            updates.add(update);
        }
        scanner.close();

        // Process rules into a map
        Map<Integer, Set<Integer>> rules = new HashMap<>();
        for (String rule : rulesInput) {
            String[] parts = rule.split("\\|");
            int x = Integer.parseInt(parts[0]);
            int y = Integer.parseInt(parts[1]);
            rules.computeIfAbsent(x, k -> new HashSet<>()).add(y);
        }

        // Validate and reorder updates
        int sumOfMiddlePagesCorrect = 0;
        int sumOfMiddlePagesReordered = 0;

        for (List<Integer> update : updates) {
            if (isValidOrder(update, rules)) {
                // Valid update: add its middle page
                int middleIndex = update.size() / 2;
                sumOfMiddlePagesCorrect += update.get(middleIndex);
            } else {
                // Invalid update: reorder and calculate its middle page
                List<Integer> reordered = reorderUpdate(update, rules);
                int middleIndex = reordered.size() / 2;
                sumOfMiddlePagesReordered += reordered.get(middleIndex);
            }
        }

        System.out.println("Somme des pages pour la partie 1 : " + sumOfMiddlePagesCorrect);
        System.out.println("Somme des pages pour la partie 2 : " + sumOfMiddlePagesReordered);
    }

    private static boolean isValidOrder(List<Integer> update, Map<Integer, Set<Integer>> rules) {
        Map<Integer, Integer> pageIndexMap = new HashMap<>();
        for (int i = 0; i < update.size(); i++) {
            pageIndexMap.put(update.get(i), i);
        }

        for (Map.Entry<Integer, Set<Integer>> entry : rules.entrySet()) {
            int x = entry.getKey();
            for (int y : entry.getValue()) {
                // If both pages are in the update, check their order
                if (pageIndexMap.containsKey(x) && pageIndexMap.containsKey(y)) {
                    if (pageIndexMap.get(x) > pageIndexMap.get(y)) {
                        return false; // Invalid order
                    }
                }
            }
        }
        return true;
    }

    private static List<Integer> reorderUpdate(List<Integer> update, Map<Integer, Set<Integer>> rules) {
        // Create a graph for sorting algo
        Map<Integer, List<Integer>> graph = new HashMap<>();
        Map<Integer, Integer> inDegree = new HashMap<>();
        for (int page : update) {
            graph.put(page, new ArrayList<>());
            inDegree.put(page, 0);
        }

        // Build the graph
        for (Map.Entry<Integer, Set<Integer>> entry : rules.entrySet()) {
            int x = entry.getKey();
            for (int y : entry.getValue()) {
                if (graph.containsKey(x) && graph.containsKey(y)) {
                    graph.get(x).add(y);
                    inDegree.put(y, inDegree.get(y) + 1);
                }
            }
        }

        // Sorting 
        Queue<Integer> queue = new LinkedList<>();
        for (Map.Entry<Integer, Integer> entry : inDegree.entrySet()) {
            if (entry.getValue() == 0) {
                queue.offer(entry.getKey());
            }
        }

        List<Integer> sortedOrder = new ArrayList<>();
        while (!queue.isEmpty()) {
            int current = queue.poll();
            sortedOrder.add(current);

            for (int neighbor : graph.get(current)) {
                inDegree.put(neighbor, inDegree.get(neighbor) - 1);
                if (inDegree.get(neighbor) == 0) {
                    queue.offer(neighbor);
                }
            }
        }

        return sortedOrder;
    }
}