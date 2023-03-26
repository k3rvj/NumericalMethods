/*
 * Compilation  -> javac Bisection.java
 * Execution    -> java Bisection a b
 * Dependencies -> none
 */
package NumericalMethods.Equations;

public class Bisection {
    // Method to perform bisection
    public static Object[] bisection(double X_l, double X_u) {
        // Initialize arrays for table of values and signs
        double[][] tableX = new double[20][4];
        String[][] signs = new String[20][3];

        double X_r = 0;

        // Iterate over 20 times to find the root
        for (int i = 0; i < 20; i++) {
            // Update values for X_l, X_u, and X_r in the table
            tableX[i][0] = X_l;
            tableX[i][1] = X_u;
            tableX[i][2] = (X_l + X_u) / 2;
            X_r = tableX[i][2];

            // Calculate the relative error and store in the table
            if (i != 0) {
                double tmp = ((tableX[i][2] - tableX[i - 1][2]) / tableX[i][2]) * 100;
                if (tmp < 0) {
                    tmp = tmp * -1;
                }
                tableX[i][3] = tmp;
            }
            // Determine the sign of each value and store in the signs array
            signs[i][0] = sign(X_l);
            signs[i][1] = sign(X_u);
            signs[i][2] = sign(X_r);

            // Update values for X_l and X_u based on the signs of X_u and X_r
            if (signs[i][1].equals("+") && signs[i][2].equals("-")) {
                X_l = X_r;
            } else {
                double aux = X_l;
                X_l = X_r;
                X_u = aux;
            }
        }

        // Return the table of values and signs as an object array
        return new Object[] { tableX, signs };
    }

    // Method to determine the sign of a value
    public static String sign(double x) {

        double function = (9 * Math.pow(Math.E, -0.7 * x)) * Math.cos(4 * x) - 3.5;

        if (function < 0) {
            return "-";
        } else {
            return "+";
        }
    }

    // This is the main method of the program
    public static void main(String[] args) {
        // Parsing the lower and upper limits from command line arguments
        double X_l = Double.parseDouble(args[0]);
        double X_u = Double.parseDouble(args[1]);

        // Calling the bisection method and storing the returned results in an array
        Object[] results = bisection(X_l, X_u);
        // Extracting the table of X values and signs from the returned results array
        double[][] tableX = (double[][]) results[0];
        String[][] signs  = (String[][]) results[1];

        try {
            // Printing a table header
            System.out.println("|Itr | X_l          | X_u          | X_r          | signs    |Rel. Error %|");
            // Looping through the table of X values and signs and printing them in a table format
            for (int i = 0; i < 20; i++) {
                System.out.printf("| %2d | %4.10f | %4.10f | %4.10f | %s | %s | %s | %4.8f |\n",
                        (i + 1), tableX[i][0], tableX[i][1], tableX[i][2], signs[i][0], signs[i][1], signs[i][2],
                        tableX[i][3]);
            }
        } catch (Exception e) {
            // Handling any exceptions that may occur during the printing of the table
            System.out.println("Error:\n" + e.getMessage());
        }
    }
}
