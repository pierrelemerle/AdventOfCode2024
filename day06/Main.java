import java.util.ArrayList;
import java.io.*;

public class Main {

    public static void main(String[] args) {

        String filePath = "input.txt"; 
        Integer startingX=0, startingY=0;

        try {
            char[][] charArray = Day06Util.readFileToList(filePath);

            Integer totalFinal1=0;
            // find the starting position
            for (int y=0; y<charArray.length; y++) {
                for (int x=0; x<charArray[y].length; x++) {
                    System.out.print(charArray[y][x] + " ");   //debugging purpose
                    if (charArray[y][x]=='^'){
                        startingX=x;
                        startingY=y;
                    }
                }
                System.out.println();      //debugging purpose
            }
            
            Day06Util.movingFunction(charArray, startingY, startingX);

            Integer numberCases = 0;
            for (int y=0; y<charArray.length; y++) {
                for (int x=0; x<charArray[y].length; x++) {
                    System.out.print(charArray[y][x] + " ");   //debugging purpose
                    if (charArray[y][x]=='X'){
                        numberCases++;
                    }
                }
                System.out.println();      //debugging purpose
            }

            System.out.println("Le nombre total de case visitee pour la partie 1 est "+numberCases);
            

        } catch (IOException e) {
            System.err.println("Error reading the file: " + e.getMessage());
        }
    }


}