import java.util.ArrayList;
import java.io.*;

public class Main {

    public static void main(String[] args) {

        String filePath = "input.txt"; 

        try {
            char[][] charArray = Day04Util.readFileToList(filePath);

            Integer totalFinal1=0;
            // find X for the part 1
            for (int y=0; y<charArray.length; y++) {
                for (int x=0; x<charArray[y].length; x++) {
                    //System.out.print(charArray[y][x] + " ");   //debugging purpose
                    if (charArray[y][x]=='X'){
                        totalFinal1+=Day04Util.numberWords(charArray, y, x);
                    }
                }
                //System.out.println();      //debugging purpose
            }

            Integer totalFinal2=0;
            // find A for the part 2
            for (int y=0; y<charArray.length; y++) {
                for (int x=0; x<charArray[y].length; x++) {
                    if (charArray[y][x]=='A'){
                        totalFinal2+=Day04Util.numberWordsPart2(charArray, y, x);
                    }
                }
            }
            

            System.out.println("Le nombre total de mot pour la partie 1 est "+totalFinal1);
            System.out.println("Le nombre total de mot pour la partie 2 est "+totalFinal2);

        } catch (IOException e) {
            System.err.println("Error reading the file: " + e.getMessage());
        }
    }


}