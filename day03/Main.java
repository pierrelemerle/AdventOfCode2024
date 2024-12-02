import java.util.ArrayList;
import java.util.List;
import java.io.*;

public class Main {

    public static void main(String[] args) {

        String filePath = "inputday3.txt"; 

        try {
            List<List<Integer>> data = Day03Util.readFileToList(filePath);
            System.out.println(data);

        } catch (IOException e) {
            System.err.println("Error reading the file: " + e.getMessage());
        }
    }
}