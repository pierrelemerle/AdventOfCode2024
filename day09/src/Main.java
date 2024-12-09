import java.io.IOException;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        String filePath = "resources/input.txt";

        try {
            List<Character> input = Helper.readFileToListChar(filePath);
            Long filesystemChecksum = 0L;

            List<String> builtBlocks = Day09Util.buildTheInitialBlocks(input);
            // List<String> orderedInput = Helper.copyStringToListString(builtBlocks);

            // // PART 1
            // Day09Util.moveFilesBlocks(builtBlocks);
            // System.out.println(builtBlocks.toString());

            // PART 2
            System.out.println(builtBlocks.toString());
            Day09Util.moveFilesBlocksPart2(builtBlocks);
            System.out.println(builtBlocks.toString());

            // calculate the final result, one by one.
            for (int i = 0; i < builtBlocks.size(); i++) {
                if (builtBlocks.get(i) != ".") {
                    int currentPosition = i * Integer.parseInt(builtBlocks.get(i));
                    filesystemChecksum += currentPosition;
                }
            }

            System.out.println("Le checksum pour le systeme de fichier pour la partie 2 est " + filesystemChecksum);

        } catch (IOException e) {
            System.err.println("Error reading the file: " + e.getMessage());
        }
    }
}
