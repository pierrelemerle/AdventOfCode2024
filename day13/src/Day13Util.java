import java.util.ArrayList;
import java.util.List;

public class Day13Util {

    private static List<Long[]> findAllPositiveSolutions(Long Ax, Long Bx, Long X) {
        List<Long[]> solutions = new ArrayList<>();

        // Loop through possible values of a
        for (Long a = 1L; a * Ax <= X; a++) {
            // Calculate remaining value
            Long remaining = X - a * Ax;
            // Check if b is positive and integer
            if (remaining > 0 && remaining % Bx == 0) {
                Long b = remaining / Bx;
                solutions.add(new Long[] { a, b }); // Add the solution
            }
        }
        return solutions; // Return all solutions
    }

    private static boolean hasPositiveSolution(Long Ax, Long Bx, Long X) {
        // Loop through possible values of a
        for (Long a = 1L; a * Ax <= X; a++) {
            // Check if there exists a positive b such that a * Ax + b * Bx = X
            Long remaining = X - a * Ax;
            if (remaining > 0 && remaining % Bx == 0) {
                return true;
            }
        }
        return false;
    }

    private static Long cheapestSolution(List<Long[]> solution) {
        Long a = solution.get(0)[0];
        Long b = solution.get(0)[1];
        Long cost = a * 3 + b;

        for (int i = 0; i < solution.size(); i++) {
            a = solution.get(i)[0];
            b = solution.get(i)[1];
            if ((a * 3 + b) < cost) {
                cost = a * 3 + b;
            }
        }

        return cost;
    }

    // Function to find one solution to the equation xA + yB = C
    public static Long nbTokenNeeded(Long Ax, Long Bx, Long Ay, Long By, Long X,
            Long Y) {
        // Check if the solution exists
        if (hasPositiveSolution(Ax, Bx, X)) {
            List<Long[]> solution = findAllPositiveSolutions(Ax, Bx, X);
            // go through the list and remove unworking solution with Y
            for (int i = 0; i < solution.size(); i++) {
                if ((solution.get(i)[0] * Ay + solution.get(i)[1] * By) != Y) {
                    solution.remove(i);
                    i--;
                }
            }
            // if there is still at least one solution, we look for the cheapest one
            if (solution.size() > 0)
                return cheapestSolution(solution);
        }
        return 0L;
    }

}
