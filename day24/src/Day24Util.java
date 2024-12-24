
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Day24Util {

    // for part 1
    public static void calculateAllPoints(Map<String, Boolean> points, List<String> rules) {
        int i = 0;
        while (!rules.isEmpty()) {
            String rule = rules.get(i);
            String[] parts = rule.split(" ");
            if (points.containsKey(parts[0]) && points.containsKey(parts[2])) {
                points.put(parts[4], evaluateOneRule(parts[1], points.get(parts[0]), points.get(parts[2])));
                rules.remove(i);
            }
            if (i < (rules.size() - 1)) {
                i++;
            } else {
                i = 0;
            }
        }

    }

    public static boolean parseCustomBoolean(String value) {
        if (value == null) {
            throw new IllegalArgumentException("Input cannot be null");
        }
        value = value.trim(); // Remove leading and trailing spaces
        return value.equals("1") || value.equalsIgnoreCase("true");
    }

    private static int findHighestZ(Set<String> nodes) {
        int maxZ = 0;
        for (String node : nodes) {
            if (node.trim().charAt(0) == 'z') {
                int currentValue = Integer.parseInt(node.trim().replace("z", ""));
                if (currentValue > maxZ) {
                    maxZ = currentValue;
                }
            }
        }
        return maxZ;
    }

    public static void printAllZValues(Map<String, Boolean> points) {
        int maxZ = findHighestZ(points.keySet());
        System.out.print("l'ensemble des Z pour la partie 1 est: ");
        for (int i = maxZ; i >= 0; i--) {
            String nodeName = "";
            if (i < 10) {
                nodeName = "z0" + String.valueOf(i);
            } else {
                nodeName = "z" + String.valueOf(i);
            }
            if (points.get(nodeName))
                System.out.print("1");
            else
                System.out.print("0");
        }
        System.out.println();
    }

    private static boolean evaluateOneRule(String rule, boolean a, boolean b) {
        switch (rule) {
            case "OR":
                return or(a, b);
            case "AND":
                return and(a, b);
            case "XOR":
                return xor(a, b);
            default:
                System.out.println("unexpected value " + rule);
                return false;
        }
    }

    private static boolean and(boolean a, boolean b) {
        return a && b;
    }

    private static boolean or(boolean a, boolean b) {
        return a || b;
    }

    private static boolean xor(boolean a, boolean b) {
        return a ^ b;
    }

}