import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Main {

    public static void main(String[] args) {
        try {
            // Chemin du fichier d'entrée
            String filePath = "resources/input.txt";

            // Lecture des lignes du fichier
            List<String> inputs = Helper.readFileToListString(filePath);
            Map<String, List<String>> connections = new HashMap<>();

            // build a map of connections
            for (String input : inputs) {
                int delimiterIndex = input.indexOf('-');
                if (delimiterIndex != -1) {
                    String firstPart = input.substring(0, delimiterIndex);
                    String secondPart = input.substring(delimiterIndex + 1);
                    if (connections.containsKey(firstPart)) {
                        connections.get(firstPart).add(secondPart);
                    } else {
                        connections.put(firstPart, new ArrayList<>(Collections.singletonList(secondPart)));
                    }
                    if (connections.containsKey(secondPart)) {
                        connections.get(secondPart).add(firstPart);
                    } else {
                        connections.put(secondPart, new ArrayList<>(Collections.singletonList(firstPart)));
                    }
                }

            }

            // PART 1
            int nombreConnections = Day23Util.connectionA3withT(connections);
            System.out.println("Le nombre de connection a 3 pour la partie 1 est : " + nombreConnections);

            // PART 2
            // reuse part1 function but building the networks of 3 in a Set of List
            Set<List<String>> completeNetworks = Day23Util.buildConnections3(connections);
            int maxLength = 0;

            // enriching this network with all the possible additionnal connections
            Day23Util.buildMoreConnections(connections, completeNetworks);

            for (List<String> network : completeNetworks) {
                maxLength = Math.max(maxLength, network.size());
            }

            // Filter the set to keep only the longest lists
            Set<List<String>> longestNetworks = new HashSet<>();
            for (List<String> network : completeNetworks) {
                if (network.size() == maxLength) {
                    longestNetworks.add(network);
                }
            }

            // Print the results
            System.out.println("Le plus gros reseau pour la partie 2 est :");
            for (List<String> network : longestNetworks) {
                for (int i = 0; i < network.size() - 1; i++) {
                    System.out.print(network.get(i) + ",");
                }
                System.out.print(network.get(network.size() - 1));
            }
            System.out.println();

        } catch (IOException e) {
            System.err.println("Erreur lors de la lecture du fichier : " + e.getMessage());
        }
    }

}
