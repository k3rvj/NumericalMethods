package NumericalMethods.Equations;

public class FalsePosition {
    public static double[][] falsePosition(double X_l, double X_u) {
        // Initialize table for storing results
        double[][] tableX = new double[10][4];
        double X_r = 0;

        // Iterate until convergence or maximum number of iterations is reached
        for (int i = 0; i < 10; i++) {
            // Store values of X_l and X_u
            tableX[i][0] = X_l;
            tableX[i][1] = X_u;

            // Compute new estimate of root using False Position formula
            X_r = X_u - ((evalFx(X_u) * (X_l - X_u)) / (evalFx(X_l) - evalFx(X_u)));
            tableX[i][2] = X_r;

            // Update lower bound for next iteration
            X_l = X_r;

            // Compute and store relative error if this is not the first iteration
            if (i != 0) {
                double tmp = ((tableX[i][2] - tableX[i - 1][2]) / tableX[i][2]) * 100;
                if (tmp < 0) {
                    tmp = tmp * -1;
                }
                tableX[i][3] = tmp;
            }
        }

        // Return table with results
        return tableX;
    }

    // Evaluate function at a given value of x
    public static double evalFx(double x) {
        // Example function to be evaluated: f(x) = 9e^-0.7x * cos(4x) - 3.5
        return (9 * Math.pow(Math.E, -0.7 * x)) * Math.cos(4 * x) - 3.5;

        // Other example functions for testing:
        // return (1.9520E-14 * Math.pow(x,4)) - (9.5838E-11 * Math.pow(x,3)) + (9.7215E-8 * Math.pow(x,2)) + (1.671E-4 * x) - 0.20597;
        // return -0.5 * Math.pow(x,2) + 2.5 * x + 4.5;
        // return ( 4 * Math.pow(x,2)) - ( 5 * x );
        // return (Math.pow(x, 3)) * Math.sin(x) + Math.exp(x) - Math.log(Math.pow(x,2));
    }

    public static void main(String[] args) {
        // Get lower and upper bounds for root from command line arguments
        double X_l = Double.parseDouble(args[0]);
        double X_u = Double.parseDouble(args[1]);

        // Compute table of results using False Position method
        double[][] tableX = falsePosition(X_l, X_u);

        // Print table of results to console
        try {
            System.out.println(
                    "|Itr | X_l                 | X_u                 | X_r                 | Error Relativo %     |");
            for (int i = 0; i < 10; i++) {
                System.out.printf("| %2d | %6.17f | %6.17f | %6.17f | %4.18f |\n", (i + 1), tableX[i][0], tableX[i][1],
                        tableX[i][2], tableX[i][3]);
            }
        } catch (Exception e) {
            System.out.println("Error:\n" + e.getMessage());
        }
    }

}