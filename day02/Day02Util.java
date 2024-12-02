import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Day02Util {

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
    public static boolean safeList(List<Integer> myList) {
        return safeListIncreasing(myList) || safeListDecreasing(myList);
    }

    public static boolean safeListIncreasing(List<Integer> myList) {

        boolean iscompliantIncreasing=true;
        
        //ascending order
        for (int i = 1; i < myList.size(); i++) {
            if (myList.get(i)<=myList.get(i-1)){ //increasing by at least one 
                iscompliantIncreasing=false; 
            }
            if (myList.get(i)>(myList.get(i-1)+3))   {   //but no more than 3
                iscompliantIncreasing=false; 
            }
        }
        return iscompliantIncreasing;
    }

    public static boolean safeListDecreasing(List<Integer> myList) {

        boolean iscompliantDecreasing=true;

        //descending order
        for (int i = 1; i < myList.size(); i++) {
            if (myList.get(i)>=myList.get(i-1)){ //decreasing by at least one 
                iscompliantDecreasing=false; 
            }
            if (myList.get(i)<(myList.get(i-1)-3))   {   //but no more than 3
                iscompliantDecreasing=false; 
            }
        }
    
        return iscompliantDecreasing;
    }

    //function for part 2 
    public static boolean isSafeWithDampener(List<Integer> list) {
        if (safeList(list)) {
            return true;
        }
        for (int i = 0; i < list.size(); i++) {
            List<Integer> modified = new ArrayList<>(list);
            modified.remove(i);
            if (safeList(modified)) {
                return true;
            }
        }
        return false;
    }

}


