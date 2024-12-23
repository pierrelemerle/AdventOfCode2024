import java.util.*;

public class CompleteNetworkGraph {

    public static List<List<String>> findCompleteNetworks(List<String> connections) {
        // Map to store the adjacency list for each node
        Map<String, Set<String>> graph = new HashMap<>();

        // Build the graph from the connections
        for (String connection : connections) {
            String[] nodes = connection.split("-");
            String a = nodes[0];
            String b = nodes[1];

            graph.computeIfAbsent(a, k -> new HashSet<>()).add(b);
            graph.computeIfAbsent(b, k -> new HashSet<>()).add(a);
        }

        // List to store the complete networks
        List<List<String>> completeNetworks = new ArrayList<>();

        // Find all cliques of size >= 3 using recursive backtracking
        findCliques(new ArrayList<>(), new ArrayList<>(graph.keySet()), graph, completeNetworks);

        // Remove duplicates and filter out smaller cliques
        return filterLargerCliques(completeNetworks);
    }

    private static void findCliques(
            List<String> currentClique,
            List<String> candidates,
            Map<String, Set<String>> graph,
            List<List<String>> result) {
        if (!currentClique.isEmpty() && currentClique.size() >= 3) {
            result.add(new ArrayList<>(currentClique));
        }

        for (int i = 0; i < candidates.size(); i++) {
            String node = candidates.get(i);
            List<String> newCandidates = new ArrayList<>();
            for (String candidate : candidates) {
                if (graph.get(node).contains(candidate)) {
                    newCandidates.add(candidate);
                }
            }
            currentClique.add(node);
            findCliques(currentClique, newCandidates, graph, result);
            currentClique.remove(currentClique.size() - 1);
        }
    }

    private static List<List<String>> filterLargerCliques(List<List<String>> networks) {
        Set<Set<String>> seen = new HashSet<>();
        List<List<String>> filtered = new ArrayList<>();

        for (List<String> network : networks) {
            Set<String> networkSet = new HashSet<>(network);
            if (seen.add(networkSet)) {
                filtered.add(new ArrayList<>(networkSet));
            }
        }

        // Remove smaller cliques that are subsets of larger ones
        filtered.removeIf(network -> filtered.stream().anyMatch(
                larger -> larger.containsAll(network) && larger.size() > network.size()));

        return filtered;
    }

    public static void main(String[] args) {
        List<String> connections = Arrays.asList(
                "kh-tc", "qp-kh", "de-cg", "ka-co", "yn-aq", "qp-ub",
                "cg-tb", "vc-aq", "tb-ka", "wh-tc", "yn-cg", "kh-ub",
                "ta-co", "de-co", "tc-td", "tb-wq", "wh-td", "ta-ka",
                "td-qp", "aq-cg", "wq-ub", "ub-vc", "de-ta", "wq-aq",
                "wq-vc", "wh-yn", "ka-de", "kh-ta", "co-tc", "wh-qp",
                "tb-vc", "td-yn");

        List<List<String>> completeNetworks = findCompleteNetworks(connections);

        // Print the results
        System.out.println("Complete networks:");
        for (List<String> network : completeNetworks) {
            System.out.println(network);
        }
    }
}
