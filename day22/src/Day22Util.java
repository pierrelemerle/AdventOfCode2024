
public class Day22Util {

    // public static int generateNextNumber(int secretnNb, int sequence) {
    // return switch (sequence % 3) {
    // case 0 -> case1(secretnNb);
    // case 1 -> case2(secretnNb);
    // case 2 -> case3(secretnNb);
    // default -> throw new IllegalStateException("Valeur inattendue : " +
    // sequence);
    // };
    // }

    public static int generateNextNumber(int secretnNb) {
        int newNb = case1(secretnNb);
        newNb = case2(newNb);
        return case3(newNb);
    }

    private static int case1(int secretNb) {
        int newNb = secretNb * 64;
        newNb = mixNumber(newNb, secretNb);
        return pruneNumber(newNb);
    }

    private static int case2(int secretNb) {
        int newNb = secretNb / 32;
        newNb = mixNumber(newNb, secretNb);
        return pruneNumber(newNb);
    }

    private static int case3(int secretNb) {
        int newNb = secretNb * 2048;
        newNb = mixNumber(newNb, secretNb);
        return pruneNumber(newNb);
    }

    private static int mixNumber(int value, int previousNb) {
        // The ^ operator computes the bitwise XOR
        return previousNb ^ value;
    }

    private static int pruneNumber(int value) {
        // pruning with the number given in the description
        int divisor = 16777216;
        int result = value % divisor;
        return result < 0 ? result + divisor : result;
    }

}