import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class Day23Util {

    // for part 1
    public static int connectionA3withT(Map<String, List<String>> connections) {
        int nbConnectionA3 = 0;
        int nbDouble = 0;
        int nbTriple = 0;

        for (Map.Entry<String, List<String>> connection : connections.entrySet()) {
            String key = connection.getKey();
            if (key.charAt(0) == 't') {
                for (int i = 0; i < connection.getValue().size(); i++) {
                    for (int j = i + 1; j < connection.getValue().size(); j++) {
                        String cible1 = connection.getValue().get(i);
                        String cible2 = connection.getValue().get(j);
                        if (connections.get(cible1).contains(cible2)) {
                            nbConnectionA3++;
                            // Make sure we do not count connection 2 or 3 times if all of them start by T
                            if ((cible1.charAt(0) == 't') && (cible2.charAt(0) == 't')) {
                                nbTriple++;
                            } else if ((cible1.charAt(0) == 't') || (cible2.charAt(0) == 't')) {
                                nbDouble++;
                            }
                        }
                    }
                }

            }
        }
        nbConnectionA3 = nbConnectionA3 - (nbDouble / 2);
        nbConnectionA3 = nbConnectionA3 - (nbTriple / 3);
        return nbConnectionA3;
    }

    // for part 2
    public static Set<List<String>> buildConnections3(Map<String, List<String>> connections) {

        Set<List<String>> networks = new HashSet<>();
        for (Map.Entry<String, List<String>> connection : connections.entrySet()) {
            String key = connection.getKey();
            for (int i = 0; i < connection.getValue().size(); i++) {
                for (int j = i + 1; j < connection.getValue().size(); j++) {
                    String cible1 = connection.getValue().get(i);
                    String cible2 = connection.getValue().get(j);
                    if (connections.get(cible1).contains(cible2)) {
                        List<String> newNetwork = Arrays.asList(key, cible1, cible2);
                        Collections.sort(newNetwork);
                        if (!networks.contains(newNetwork)) {
                            networks.add(newNetwork);
                        }
                    }
                }
            }
        }
        return networks;
    }

    public static boolean buildMoreConnections(Map<String, List<String>> connections, Set<List<String>> networks) {
        boolean builtNewConnection = false;
        Set<List<String>> updatedNetworks = new HashSet<>();

        for (List<String> network : networks) {
            List<String> mutableNetwork = new ArrayList<>(network); // Ensure mutability
            for (Map.Entry<String, List<String>> connection : connections.entrySet()) {
                String key = connection.getKey();
                if (!mutableNetwork.contains(key)) {
                    if (areAllStringsPresent(mutableNetwork, connection.getValue())) {
                        mutableNetwork.add(key);
                        Collections.sort(mutableNetwork);
                        builtNewConnection = true;
                    }
                }
            }
            if (!updatedNetworks.contains(mutableNetwork))
                updatedNetworks.add(mutableNetwork); // Replace the network with the updated one
        }
        // Replace original networks with updated networks
        networks.clear();
        networks.addAll(updatedNetworks);
        networks = cleanAndSortSet(networks);
        return builtNewConnection;
    }

    private static boolean areAllStringsPresent(List<String> network, List<String> connection) {
        // Convert the second list to a Set for faster lookups
        Set<String> set = new HashSet<>(connection);

        // Check if all strings in list1 are in the set
        for (String str : network) {
            if (!set.contains(str)) {
                return false; // Return false as soon as a missing element is found
            }
        }
        return true; // All elements are present
    }

    private static Set<List<String>> cleanAndSortSet(Set<List<String>> inputSet) {
        // Use a TreeSet with a custom comparator to ensure uniqueness and sorting
        Set<List<String>> cleanedSet = new TreeSet<>((list1, list2) -> {
            int sizeComparison = Integer.compare(list1.size(), list2.size());
            if (sizeComparison != 0)
                return sizeComparison;

            for (int i = 0; i < list1.size(); i++) {
                int comparison = list1.get(i).compareTo(list2.get(i));
                if (comparison != 0)
                    return comparison;
            }
            return 0;
        });

        for (List<String> list : inputSet) {
            // Sort each list alphabetically
            List<String> sortedList = new ArrayList<>(list);
            Collections.sort(sortedList);

            // Add the sorted list to the TreeSet
            cleanedSet.add(sortedList);
        }

        return cleanedSet;
    }

}