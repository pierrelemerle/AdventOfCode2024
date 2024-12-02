import java.util.ArrayList;
import java.util.List;
import java.io.*;

public class Main {

    public static void main(String[] args) {

        String filePath = "inputday2.txt"; 

        try {
            List<List<Integer>> data = Day02Util.readFileToList(filePath);
            System.out.println(data);

            Integer numberOfSafeList=0;
            for (int i = 0; i < data.size(); i++) {
                if (Day02Util.isSafeWithDampener(data.get(i))){
                    numberOfSafeList++;
                }
            }
            System.out.println("the number of safe reports is "+numberOfSafeList);

        } catch (IOException e) {
            System.err.println("Error reading the file: " + e.getMessage());
        }
    }


}