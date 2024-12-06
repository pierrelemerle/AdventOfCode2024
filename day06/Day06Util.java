import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Day06Util {

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
    public static void movingFunction(char[][] tab, int startingY, int startingX)  {
        Integer LastLine = tab.length -1 ;
        Integer LastColumn = tab[startingY].length -1;
        Integer currentX=startingX;
        Integer currentY=startingY;
        boolean keepgoing = true;
        String direction = "up";


        while (keepgoing){
            tab[currentY][currentX]='X';
            if (direction=="up"){
                if (currentY<=0){
                    keepgoing=false;
                }
                else{
                    if(tab[currentY-1][currentX]!='#'){
                        currentY--;
                    }
                    else if ((currentX<LastColumn) || (tab[currentY][currentX+1]!='#')){
                        currentX++;
                        direction = "right";
                    }
                    else if ((currentY==LastLine) || (tab[currentY+1][currentX]!='#')){
                        currentY++;
                        direction = "down";
                    }
                    else if ((currentX==0) || (tab[currentY][currentX-1]!='#')){
                        currentX--;
                        direction = "left";
                    }
                }
            }
            else if (direction=="right"){
                if (currentX>=LastColumn){
                    keepgoing=false;
                }
                else{
                    if(tab[currentY][currentX+1]!='#'){
                        currentX++;
                    }
                    else if ((currentY+1==LastColumn) || (tab[currentY+1][currentX]!='#')){
                        currentY++;
                        direction = "down";
                    }
                    else if ((currentX==0) || (tab[currentY][currentX-1]!='#')){
                        currentX--;
                        direction = "left";
                    }
                    else if ((currentY==0) || (tab[currentY][currentX+1]!='#')){
                        currentY--;
                        direction = "up";
                    }                    
                }
            }
            else if (direction=="down"){
                if (currentY>=LastColumn){
                    keepgoing=false;
                }
                else{
                    if(tab[currentY+1][currentX]!='#'){
                        currentY++;
                    }
                    else if ((currentX==0) || (tab[currentY][currentX-1]!='#')){
                        currentX--;
                        direction = "left";
                    }
                    else if ((currentY==0) || (tab[currentY][currentX+1]!='#')){
                        currentY--;
                        direction = "up";
                    }   
                    else if ((currentX==LastColumn) || (tab[currentY][currentX+1]!='#')){
                        currentX++;
                        direction = "right";
                    }                 
                }
            }
            else if (direction=="left"){
                if (currentX<=0){
                    keepgoing=false;
                }
                else{
                    if(tab[currentY][currentX-1]!='#'){
                        currentX--;
                    }
                    else if ((currentY==0) || (tab[currentY][currentX+1]!='#')){
                        currentY--;
                        direction = "up";
                    }   
                    else if ((currentX==LastColumn) || (tab[currentY][currentX+1]!='#')){
                        currentX++;
                        direction = "right";
                    }  
                    else if ((currentY+1==LastColumn) || (tab[currentY+1][currentX]!='#')){
                        currentY++;
                        direction = "down";
                    }               
                }
            }            

        }

    }

}


