import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Day03Util {

    //reading the input file and storing it in a list of list
    public static List<List<Integer>> readFileToList(String filePath) throws IOException {
        List<List<Integer>> result = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] numbers = line.trim().split("\\s+"); // Split by whitespace
                List<Integer> row = new ArrayList<>();
                for (String num : numbers) {
                    row.add(Integer.parseInt(num));
                }
                result.add(row);
            }
        }

        return result;
    }

    //function for part 1 
  
    //function for part 2 


}


