import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Day09Util {

    // build the initial file system
    public static List<String> buildTheInitialBlocks(List<Character> input) throws IOException {
        List<String> resultList = new ArrayList<>();
        boolean blockFiles = true;
        Integer fileIdNumber = 0;

        // Convert each character to the list
        for (Character c : input) {
            if (blockFiles) {
                for (int i = 0; i < Character.getNumericValue(c); i++) {
                    resultList.add(fileIdNumber.toString());
                }
                fileIdNumber++;
                blockFiles = false;
            } else {
                for (int i = 0; i < Character.getNumericValue(c); i++) {
                    resultList.add(".");
                }
                blockFiles = true;
            }
        }

        return resultList;
    }

    // move the blocks around following rule in the part1
    public static void moveFilesBlocks(List<String> input) {
        int size = input.size();

        while (true) {
            // Find the rightmost non-space block
            int rightmostBlockIndex = -1;
            for (int i = size - 1; i >= 0; i--) {
                if (input.get(i) != ".") {
                    rightmostBlockIndex = i;
                    break;
                }
            }

            // Stop if no non-space block is found
            if (rightmostBlockIndex == -1) {
                break;
            }

            // Find the leftmost free space
            int leftmostFreeSpaceIndex = -1;
            for (int i = 0; i < size; i++) {
                if (input.get(i) == ".") {
                    leftmostFreeSpaceIndex = i;
                    break;
                }
            }

            // Stop if no free space is found (shouldn't happen if input is valid)
            if (leftmostFreeSpaceIndex == -1) {
                break;
            }

            // Move the rightmost block to the leftmost free space
            String block = input.get(rightmostBlockIndex);
            input.set(leftmostFreeSpaceIndex, block);
            input.set(rightmostBlockIndex, ".");
            input.remove(rightmostBlockIndex);
            size--;
        }
    }

    // move the blocks around following rule in the part2
    public static void moveFilesBlocksPart2(List<String> input) {
        // Convert List<String> to array for easier manipulation
        String[] diskArray = input.toArray(new String[0]);

        // Determine the maximum file ID by finding the highest character
        String maxFileID = "0";
        for (String block : diskArray) {
            if (!block.equals(".") && Integer.parseInt(block) > Integer.parseInt(maxFileID)) {
                maxFileID = block;
            }
        }

        // Iterate over file IDs in decreasing order
        for (int fileID = Integer.parseInt(maxFileID); fileID >= 0; fileID--) {
            String fileIDString = String.valueOf(fileID);

            // Find positions of the fileID in the disk
            int start = -1, end = -1;
            for (int i = 0; i < diskArray.length; i++) {
                if (diskArray[i].equals(fileIDString)) {
                    if (start == -1)
                        start = i;
                    end = i;
                }
            }

            // If fileID does not exist, skip
            if (start == -1)
                continue;

            // Calculate file size
            int fileSize = end - start + 1;

            // Look for the leftmost free space span that fits the file
            for (int i = 0; i <= start - fileSize; i++) {
                boolean canFit = true;
                for (int j = i; j < i + fileSize; j++) {
                    if (!diskArray[j].equals(".")) {
                        canFit = false;
                        break;
                    }
                }

                // If span found, move the file
                if (canFit) {
                    // Clear old file location
                    for (int j = start; j <= end; j++) {
                        diskArray[j] = ".";
                    }

                    // Place the file in the new location
                    for (int j = i; j < i + fileSize; j++) {
                        diskArray[j] = fileIDString;
                    }
                    break; // Move to the next fileID
                }
            }
        }

        // Convert back to List<String> and update diskMap
        for (int i = 0; i < diskArray.length; i++) {
            input.set(i, diskArray[i]);
        }
    }

}
