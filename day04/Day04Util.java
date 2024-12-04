import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Day04Util {

    //reading the input file and storing it in 2 dimension tab of characters
    public static char[][] readFileToList(String filePath) throws IOException {
        List<String> resultList = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                resultList.add(line);
            }        
        }
        // Create a char[][] with size equal to the list size
        char[][] result = new char[resultList.size()][];

        // Populate the char[][] using each string in the list
        for (int i = 0; i < resultList.size(); i++) {
            result[i] = resultList.get(i).toCharArray();
        }    
        return result;
    }

    //reading the char tab and checking from a specific X if there is a following horizontal XMAS
    public static boolean isHorizontal(char[][] tab, int y, int x)  {
        if (x>tab[y].length-4){
            return false;
        }
        if (tab[y][x+1]=='M' && tab[y][x+2]=='A' && tab[y][x+3]=='S'){
            return true;
        }
        return false;
    }

    //reading the char tab and checking from a specific X if there is a following horizontal  backward SAMX
    public static boolean isHorizontalBackward(char[][] tab, int y, int x)  {
        if (x<3){
            return false;
        }
        if (tab[y][x-1]=='M' && tab[y][x-2]=='A' && tab[y][x-3]=='S'){
            return true;
        }
        return false;
    }

    //reading the char tab and checking from a specific X if there is a following Vertical XMAS
    public static boolean isVertical(char[][] tab, int y, int x)  {
        if (y>tab.length-4){
            return false;
        }
        if (tab[y+1][x]=='M' && tab[y+2][x]=='A' && tab[y+3][x]=='S'){
            return true;
        }
        return false;
    }    

    //reading the char tab and checking from a specific X if there is a following Vertical Backward XMAS
    public static boolean isVerticalBackward(char[][] tab, int y, int x)  {
        if (y<3){
            return false;
        }
        if (tab[y-1][x]=='M' && tab[y-2][x]=='A' && tab[y-3][x]=='S'){
            return true;
        }
        return false;
    }  

    //reading the char tab and checking from a specific X if there is a following Diagonal XMAS
    public static boolean isDiagonal(char[][] tab, int y, int x)  {
        if ((x>tab[y].length-4)||(y>tab.length-4)){
            return false;
        }
        if (tab[y+1][x+1]=='M' && tab[y+2][x+2]=='A' && tab[y+3][x+3]=='S'){
            return true;
        }
        return false;
    } 

    //reading the char tab and checking from a specific X if there is a following Diagonal Backward XMAS
    public static boolean isDiagonalBackward(char[][] tab, int y, int x)  {
        if ((x<3)||(y<3)){
            return false;
        }
        if (tab[y-1][x-1]=='M' && tab[y-2][x-2]=='A' && tab[y-3][x-3]=='S'){
            return true;
        }
        return false;
    }  

    //reading the char tab and checking from a specific X if there is a following Diagonal Reverse XMAS
    public static boolean isDiagonalReverse(char[][] tab, int y, int x)  {
        if ((x<3)||(y>tab.length-4)){
            return false;
        }
        if (tab[y+1][x-1]=='M' && tab[y+2][x-2]=='A' && tab[y+3][x-3]=='S'){
            return true;
        }
        return false;
    }     

    //reading the char tab and checking from a specific X if there is a following Diagonal Reverse Backward XMAS
    public static boolean isDiagonalReverseBackward(char[][] tab, int y, int x)  {
        if ((y<3)||(x>tab.length-4)){
            return false;
        }
        if (tab[y-1][x+1]=='M' && tab[y-2][x+2]=='A' && tab[y-3][x+3]=='S'){
            return true;
        }
        return false;
    }    
    
    //testing all combinations for each X in the grid - part 1
    public static Integer numberWords(char[][] charArray, int y, int x)  {
        Integer total=0;
        if (isHorizontal(charArray, y, x)){
            total++;
        }
        if (isHorizontalBackward(charArray, y, x)){
            total++;
        }
        if (isVertical(charArray, y, x)){
            total++;
        }
        if (isVerticalBackward(charArray, y, x)){
            total++;
        }
        if (isDiagonal(charArray, y, x)){
            total++;
        }
        if (isDiagonalBackward(charArray, y, x)){
            total++;
        }
        if (isDiagonalReverse(charArray, y, x)){
            total++;
        }
        if (isDiagonalReverseBackward(charArray, y, x)){
            total++;
        }
        return total;
    }

    public static boolean isDiagonalPart2(char[][] tab, int y, int x)  {
        if ((x==0)||(y==0)||y==(tab.length-1)||(x==tab[y].length-1)){
            return false;
        }
        if (tab[y-1][x-1]=='M' && tab[y+1][x-1]=='M' && tab[y-1][x+1]=='S' && tab[y+1][x+1]=='S'){
            return true;
        }
        return false;
    } 

    public static boolean isDiagonalReversePart2(char[][] tab, int y, int x)  {
        if ((x==0)||(y==0)||y==(tab.length-1)||(x==tab[y].length-1)){
            return false;
        }
        if (tab[y-1][x-1]=='S' && tab[y+1][x-1]=='S' && tab[y-1][x+1]=='M' && tab[y+1][x+1]=='M'){
            return true;
        }
        return false;
    }     


    public static boolean isDiagonalBackwardPart2(char[][] tab, int y, int x)  {
        if ((x==0)||(y==0)||y==(tab.length-1)||(x==tab[y].length-1)){
            return false;
        }
        if (tab[y-1][x-1]=='M' && tab[y+1][x-1]=='S' && tab[y-1][x+1]=='M' && tab[y+1][x+1]=='S'){
            return true;
        }
        return false;
    } 

    public static boolean isDiagonalBackwardReversePart2(char[][] tab, int y, int x)  {
        if ((x==0)||(y==0)||y==(tab.length-1)||(x==tab[y].length-1)){
            return false;
        }
        if (tab[y-1][x-1]=='S' && tab[y+1][x-1]=='M' && tab[y-1][x+1]=='S' && tab[y+1][x+1]=='M'){
            return true;
        }
        return false;
    } 

    //testing all combinations for each A in the grid -- part 2 
    public static Integer numberWordsPart2(char[][] charArray, int y, int x)  {
        Integer total=0;
        if (isDiagonalPart2(charArray, y, x)){
            total++;
        }
        if (isDiagonalReversePart2(charArray, y, x)){
            total++;
        }
        if (isDiagonalBackwardPart2(charArray, y, x)){
            total++;
        }
        if (isDiagonalBackwardReversePart2(charArray, y, x)){
            total++;
        }
        return total;
    }    

}


