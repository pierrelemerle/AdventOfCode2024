import java.util.HashMap;
import java.util.Map;

public class Day21Util {

    public static String parseStringNumerical(String input) {
        StringBuilder result = new StringBuilder();
        // the robot arm starts on the A button
        char previousChar = 'A';

        for (int i = 0; i < input.length(); i++) {
            char currentChar = input.charAt(i);

            // if we are on the A button
            if (previousChar == 'A') {
                if (currentChar == 'A')
                    result.append("A");
                if (currentChar == '0')
                    result.append("<A");
                if (currentChar == '1')
                    result.append("^<<A");
                if (currentChar == '2')
                    result.append("<^A");
                if (currentChar == '3')
                    result.append("^A");
                if (currentChar == '4')
                    result.append("^^<<A");
                if (currentChar == '5')
                    result.append("<^^A");
                if (currentChar == '6')
                    result.append("^^A");
                if (currentChar == '7')
                    result.append("^^^<<A");
                if (currentChar == '8')
                    result.append("<^^^A");
                if (currentChar == '9')
                    result.append("^^^A");
            }

            // if we are on the 0 button
            if (previousChar == '0') {
                if (currentChar == 'A')
                    result.append(">A");
                if (currentChar == '0')
                    result.append("A");
                if (currentChar == '1')
                    result.append("^<A");
                if (currentChar == '2')
                    result.append("^A");
                if (currentChar == '3')
                    result.append("^>A");
                if (currentChar == '4')
                    result.append("^^<A");
                if (currentChar == '5')
                    result.append("^^A");
                if (currentChar == '6')
                    result.append("^^>A");
                if (currentChar == '7')
                    result.append("^^^<A");
                if (currentChar == '8')
                    result.append("^^^A");
                if (currentChar == '9')
                    result.append("^^^>A");
            }

            // if we are on the 1 button
            if (previousChar == '1') {
                if (currentChar == 'A')
                    result.append(">>vA");
                if (currentChar == '0')
                    result.append(">vA");
                if (currentChar == '1')
                    result.append("A");
                if (currentChar == '2')
                    result.append(">A");
                if (currentChar == '3')
                    result.append(">>A");
                if (currentChar == '4')
                    result.append("^A");
                if (currentChar == '5')
                    result.append("^>A");
                if (currentChar == '6')
                    result.append("^>>A");
                if (currentChar == '7')
                    result.append("^^A");
                if (currentChar == '8')
                    result.append("^^>A");
                if (currentChar == '9')
                    result.append("^^>>A");
            }

            // if we are on the 2 button
            if (previousChar == '2') {
                if (currentChar == 'A')
                    result.append("v>A");
                if (currentChar == '0')
                    result.append("vA");
                if (currentChar == '1')
                    result.append("<A");
                if (currentChar == '2')
                    result.append("A");
                if (currentChar == '3')
                    result.append(">A");
                if (currentChar == '4')
                    result.append("<^A");
                if (currentChar == '5')
                    result.append("^A");
                if (currentChar == '6')
                    result.append("^>A");
                if (currentChar == '7')
                    result.append("<^^A");
                if (currentChar == '8')
                    result.append("^^A");
                if (currentChar == '9')
                    result.append("^^>A");
            }

            // if we are on the 3 button
            if (previousChar == '3') {
                if (currentChar == 'A')
                    result.append("vA");
                if (currentChar == '0')
                    result.append("<vA");
                if (currentChar == '1')
                    result.append("<<A");
                if (currentChar == '2')
                    result.append("<A");
                if (currentChar == '3')
                    result.append("A");
                if (currentChar == '4')
                    result.append("<<^A");
                if (currentChar == '5')
                    result.append("<^A");
                if (currentChar == '6')
                    result.append("^A");
                if (currentChar == '7')
                    result.append("<<^^A");
                if (currentChar == '8')
                    result.append("<^^A");
                if (currentChar == '9')
                    result.append("^^A");
            }

            // if we are on the 4 button
            if (previousChar == '4') {
                if (currentChar == 'A')
                    result.append(">>vvA");
                if (currentChar == '0')
                    result.append(">vvA");
                if (currentChar == '1')
                    result.append("vA");
                if (currentChar == '2')
                    result.append("v>A");
                if (currentChar == '3')
                    result.append("v>>A");
                if (currentChar == '4')
                    result.append("A");
                if (currentChar == '5')
                    result.append(">A");
                if (currentChar == '6')
                    result.append(">>A");
                if (currentChar == '7')
                    result.append("^A");
                if (currentChar == '8')
                    result.append("^>A");
                if (currentChar == '9')
                    result.append("^>>A");
            }

            // if we are on the 5 button
            if (previousChar == '5') {
                if (currentChar == 'A')
                    result.append("vv>A");
                if (currentChar == '0')
                    result.append("vvA");
                if (currentChar == '1')
                    result.append("<vA");
                if (currentChar == '2')
                    result.append("vA");
                if (currentChar == '3')
                    result.append("v>A");
                if (currentChar == '4')
                    result.append("<A");
                if (currentChar == '5')
                    result.append("A");
                if (currentChar == '6')
                    result.append(">A");
                if (currentChar == '7')
                    result.append("<^A");
                if (currentChar == '8')
                    result.append("^A");
                if (currentChar == '9')
                    result.append("^>A");
            }

            // if we are on the 6 button
            if (previousChar == '6') {
                if (currentChar == 'A')
                    result.append("vvA");
                if (currentChar == '0')
                    result.append("<vvA");
                if (currentChar == '1')
                    result.append("<<vA");
                if (currentChar == '2')
                    result.append("<vA");
                if (currentChar == '3')
                    result.append("vA");
                if (currentChar == '4')
                    result.append("<<A");
                if (currentChar == '5')
                    result.append("<A");
                if (currentChar == '6')
                    result.append("A");
                if (currentChar == '7')
                    result.append("<<^A");
                if (currentChar == '8')
                    result.append("<^A");
                if (currentChar == '9')
                    result.append("^A");
            }

            // if we are on the 7 button
            if (previousChar == '7') {
                if (currentChar == 'A')
                    result.append(">>vvvA");
                if (currentChar == '0')
                    result.append(">vvvA");
                if (currentChar == '1')
                    result.append("vvA");
                if (currentChar == '2')
                    result.append("vv>A");
                if (currentChar == '3')
                    result.append("vv>>A");
                if (currentChar == '4')
                    result.append("vA");
                if (currentChar == '5')
                    result.append("v>A");
                if (currentChar == '6')
                    result.append("v>>A");
                if (currentChar == '7')
                    result.append("A");
                if (currentChar == '8')
                    result.append(">A");
                if (currentChar == '9')
                    result.append(">>A");
            }

            // if we are on the 8 button
            if (previousChar == '8') {
                if (currentChar == 'A')
                    result.append("vvv>A");
                if (currentChar == '0')
                    result.append("vvvA");
                if (currentChar == '1')
                    result.append("<vvA");
                if (currentChar == '2')
                    result.append("vvA");
                if (currentChar == '3')
                    result.append("vv>A");
                if (currentChar == '4')
                    result.append("<vA");
                if (currentChar == '5')
                    result.append("vA");
                if (currentChar == '6')
                    result.append("v>A");
                if (currentChar == '7')
                    result.append("<A");
                if (currentChar == '8')
                    result.append("A");
                if (currentChar == '9')
                    result.append(">A");
            }

            // if we are on the 9 button
            if (previousChar == '9') {
                if (currentChar == 'A')
                    result.append("vvvA");
                if (currentChar == '0')
                    result.append("<vvvA");
                if (currentChar == '1')
                    result.append("<<vvA");
                if (currentChar == '2')
                    result.append("<vvA");
                if (currentChar == '3')
                    result.append("vvA");
                if (currentChar == '4')
                    result.append("<<vA");
                if (currentChar == '5')
                    result.append("<vA");
                if (currentChar == '6')
                    result.append("vA");
                if (currentChar == '7')
                    result.append("<<A");
                if (currentChar == '8')
                    result.append("<A");
                if (currentChar == '9')
                    result.append("A");
            }

            // Update the previous character
            previousChar = currentChar;
        }

        return result.toString();
    }

    public static String parseStringDirectionnal(String input) {
        StringBuilder result = new StringBuilder();
        // the robot arm starts on the A button
        char previousChar = 'A';

        for (int i = 0; i < input.length(); i++) {
            char currentChar = input.charAt(i);

            // if we go from the A button
            if (previousChar == 'A') {
                if (currentChar == '^')
                    result.append("<A");
                if (currentChar == '>')
                    result.append("vA");
                if (currentChar == '<')
                    result.append("v<<A");
                if (currentChar == 'v')
                    result.append("v<A");
                if (currentChar == 'A')
                    result.append("A");
            }

            // if we are on the > button
            if (previousChar == '>') {
                if (currentChar == '^')
                    result.append("<^A");
                if (currentChar == '>')
                    result.append("A");
                if (currentChar == '<')
                    result.append("<<A");
                if (currentChar == 'v')
                    result.append("<A");
                if (currentChar == 'A')
                    result.append("^A");
            }

            // if we are on the v button
            if (previousChar == 'v') {
                if (currentChar == '^')
                    result.append("^A");
                if (currentChar == '>')
                    result.append(">A");
                if (currentChar == '<')
                    result.append("<A");
                if (currentChar == 'v')
                    result.append("A");
                if (currentChar == 'A')
                    result.append(">^A");
            }

            // if we are on the < button
            if (previousChar == '<') {
                if (currentChar == '^')
                    result.append(">^A");
                if (currentChar == '>')
                    result.append(">>A");
                if (currentChar == '<')
                    result.append("A");
                if (currentChar == 'v')
                    result.append(">A");
                if (currentChar == 'A')
                    result.append(">>^A");
            }

            // if we are on the ^ button
            if (previousChar == '^') {
                if (currentChar == '^')
                    result.append("A");
                if (currentChar == '>')
                    result.append("v>A");
                if (currentChar == '<')
                    result.append("v<A");
                if (currentChar == 'v')
                    result.append("vA");
                if (currentChar == 'A')
                    result.append(">A");
            }

            // Update the previous character
            previousChar = currentChar;
        }

        return result.toString();
    }

    public static long calculateComplexity(String code, String sequence) {
        long complexity = 0L;
        char charToRemove = 'A';
        // Remove the character
        code = code.replace(Character.toString(charToRemove), "");

        long codeInt = Long.parseLong(code);
        complexity = codeInt * sequence.length();
        return complexity;
    }

}