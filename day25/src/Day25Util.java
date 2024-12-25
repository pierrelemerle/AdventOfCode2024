
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Day25Util {

    // reading the input file and storing it in 2 dimension tab of characters
    public static List<char[][]> readFileToListTabTabChar(String filePath)
            throws IOException {
        List<char[][]> inputs = new ArrayList<>();
        char[][] input = new char[7][5];
        List<String> Lines = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Lines = new ArrayList<>();
                while ((line != null) && (!line.isEmpty())) {
                    Lines.add(line);
                    line = reader.readLine();
                }
                // convert the lines in a char[][] and add it
                input = new char[7][5];
                for (int i = 0; i < Lines.size(); i++) {
                    for (int j = 0; j < Lines.get(i).length(); j++) {
                        input[i][j] = Lines.get(i).charAt(j);
                    }
                }
                inputs.add(input);
            }
        }

        return inputs;
    }

    // for part 1
    public static List<List<Integer>> fetchKeys(List<char[][]> inputs) {
        List<List<Integer>> results = new ArrayList<>();
        List<Integer> result = new ArrayList<>();
        int nb = 0;

        for (char[][] input : inputs) {
            if (input[0][0] == '.') {
                result = new ArrayList<>();
                // for each column
                for (int i = 0; i < input[0].length; i++) {
                    nb = 0;
                    for (int j = 1; j < (input.length - 1); j++) {
                        if (input[j][i] == '#') {
                            nb++;
                        }
                    }
                    result.add(Integer.valueOf(nb));
                }
                results.add(result);
            }
        }
        return results;
    }

    public static List<List<Integer>> fetchLocks(List<char[][]> inputs) {
        List<List<Integer>> results = new ArrayList<>();
        List<Integer> result = new ArrayList<>();
        int nb = 0;

        for (char[][] input : inputs) {
            if (input[0][0] == '#') {
                result = new ArrayList<>();
                // for each column
                for (int i = 0; i < input[0].length; i++) {
                    nb = 0;
                    for (int j = 1; j < (input.length - 1); j++) {
                        if (input[j][i] == '#') {
                            nb++;
                        }
                    }
                    result.add(Integer.valueOf(nb));
                }
                results.add(result);
            }
        }
        return results;
    }

    public static int howManyLocks(List<Integer> key, List<List<Integer>> locks) {

        int nbLocks = 0;
        for (List<Integer> lock : locks) {
            if (fit(key, lock)) {
                nbLocks++;
            }
        }
        return nbLocks;
    }

    private static boolean fit(List<Integer> key, List<Integer> lock) {
        for (int i = 0; i < key.size(); i++) {
            int sum = key.get(i) + lock.get(i);
            if (sum > 5) {
                return false;
            }

        }
        return true;
    }

}