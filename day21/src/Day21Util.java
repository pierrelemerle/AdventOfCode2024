import java.awt.Point;

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

    static String deplacerEnX(int dx) {
        return switch (dx) {
            case 0 -> "";
            case 1, 2, 3 -> "v".repeat(dx);
            case -1, -2, -3 -> "^".repeat(-dx);
            default -> throw new IllegalStateException("Valeur inattendue : " + dx);
        };
    }

    static String deplacerEnY(int dy) {
        return switch (dy) {
            case 0 -> "";
            case 1, 2, 3 -> ">".repeat(dy);
            case -1, -2, -3 -> "<".repeat(-dy);
            default -> throw new IllegalStateException("Valeur inattendue : " + dy);
        };
    }

    static numericKeyboard convertirClavierNumerique(char caractere) {
        return switch (caractere) {
            case '0' -> numericKeyboard.ZERO;
            case '1' -> numericKeyboard.ONE;
            case '2' -> numericKeyboard.TWO;
            case '3' -> numericKeyboard.THREE;
            case '4' -> numericKeyboard.FOUR;
            case '5' -> numericKeyboard.FIVE;
            case '6' -> numericKeyboard.SIX;
            case '7' -> numericKeyboard.SEVEN;
            case '8' -> numericKeyboard.EIGHT;
            case '9' -> numericKeyboard.NINE;
            case 'A' -> numericKeyboard.A;
            default -> throw new IllegalStateException("Valeur inattendue : " + caractere);
        };
    }

    static int convertirClavierDirectionnel(char caractere) {
        return switch (caractere) {
            case 'A' -> 0;
            case '^' -> 1;
            case 'v' -> 2;
            case '<' -> 3;
            case '>' -> 4;
            default -> throw new IllegalStateException("Valeur inattendue : " + caractere);
        };
    }

    enum numericKeyboard {
        ONE(2, 0),
        TWO(2, 1),
        THREE(2, 2),
        FOUR(1, 0),
        FIVE(1, 1),
        SIX(1, 2),
        SEVEN(0, 0),
        EIGHT(0, 1),
        NINE(0, 2),
        ZERO(3, 1),
        A(3, 2);

        private final Point position;

        numericKeyboard(int x, int y) {
            position = new Point(x, y);
        }

        String sequence(numericKeyboard autreClavier) {
            int dx = autreClavier.position.x - this.position.x;
            int dy = autreClavier.position.y - this.position.y;

            StringBuilder sequenceBuilder = new StringBuilder();

            if (dy < 0) {
                if (this.position.x == 3 && autreClavier.position.y == 0) {
                    sequenceBuilder.append(deplacerEnX(dx)).append(deplacerEnY(dy));
                } else {
                    sequenceBuilder.append(deplacerEnY(dy)).append(deplacerEnX(dx));
                }
            } else {
                if (autreClavier.position.x == 3 && this.position.y == 0) {
                    sequenceBuilder.append(deplacerEnY(dy)).append(deplacerEnX(dx));
                } else {
                    sequenceBuilder.append(deplacerEnX(dx)).append(deplacerEnY(dy));
                }
            }

            return sequenceBuilder.toString();
        }
    }

    enum directionnalKeyboard {
        UP(0, 1),
        DOWN(1, 1),
        LEFT(1, 0),
        RIGHT(1, 2),
        A(0, 2);

        private final Point position;

        directionnalKeyboard(int x, int y) {
            position = new Point(x, y);
        }

        String sequence(directionnalKeyboard autreClavier) {
            int dx = autreClavier.position.x - this.position.x;
            int dy = autreClavier.position.y - this.position.y;

            StringBuilder sequenceBuilder = new StringBuilder();

            if (dy < 0) {
                if (autreClavier.position.y == 0) {
                    sequenceBuilder.append(deplacerEnX(dx)).append(deplacerEnY(dy));
                } else {
                    sequenceBuilder.append(deplacerEnY(dy)).append(deplacerEnX(dx));
                }
            } else {
                if (this.position.y == 0) {
                    sequenceBuilder.append(deplacerEnY(dy)).append(deplacerEnX(dx));
                } else {
                    sequenceBuilder.append(deplacerEnX(dx)).append(deplacerEnY(dy));
                }
            }

            return sequenceBuilder.toString();
        }
    }

}