import java.io.*;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        try {

            // Process rules into a map
            List<char[][]> inputs = Day25Util.readFileToListTabTabChar("resources/input.txt");
            for (char[][] input : inputs) {
                for (int i = 0; i < input.length; i++) {
                    for (int j = 0; j < input[i].length; j++) {
                        System.out.print(input[i][j]);
                    }
                    System.out.println();
                }
                System.out.println();
            }
            List<List<Integer>> keys = Day25Util.fetchKeys(inputs);

            for (List<Integer> key : keys) {
                for (Integer k : key) {
                    System.out.print(k + ",");
                }
                System.out.println();
            }

            List<List<Integer>> locks = Day25Util.fetchLocks(inputs);

            for (List<Integer> lock : locks) {
                for (Integer l : lock) {
                    System.out.print(l + ",");
                }
                System.out.println();
            }

            // PART 1
            int nbTotalMatch = 0;
            for (List<Integer> key : keys) {
                nbTotalMatch += Day25Util.howManyLocks(key, locks);
            }
            System.out.println("le nombre total de combinaison pour la partie 1 est " + nbTotalMatch);

            // PART 2

        } catch (IOException e) {
            System.err.println("Erreur lors de la lecture du fichier : " + e.getMessage());
        }
    }

}
