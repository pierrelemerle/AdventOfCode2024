import java.io.*;
import java.util.List;
import java.io.IOException;

public class Main {

    public static void main(String[] args) {

        String filePath = "resources/input.txt";

        try {
            List<String> parameterList = Day07Util.readFileToList(filePath);
            Long numberCorrectList = 0L;

            for (String parameter : parameterList) {
                List<Long> numbers = Day07Util.splitAndConvert(parameter);
                List<Long> results = Day07Util.generateAllResults(numbers);
                System.out.println(numbers.toString());
                if (results.contains(numbers.get(0))) {
                    numberCorrectList += numbers.get(0);
                }
            }

            System.out.println("Le nombre total de liste correcte pour la partie 1 est " + numberCorrectList);

        } catch (IOException e) {
            System.err.println("Error reading the file: " + e.getMessage());
        }
    }

}

    
    
    

    
    
    
        
            
            
            
        
            
            
            
            
        
    

    