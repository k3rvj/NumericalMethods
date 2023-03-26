/*
 * Compilation  -> javac Secant.java
 * Execution    -> java Secant a b
 * Dependencies -> none
 */
package NumericalMethods.Equations;

// This is a Java program that implements the secant method to find the roots of a given function
public class Secant {
    // Method to perform the secant method
    public static double[][] secant(double X_i_minus_1, double X_i) {

        double[][] tableX = new double[10][4]; // Table to store results

        double X_i_plus_1 = 0;

        for (int i = 0; i < 10; i++) {
            tableX[i][0] = X_i_minus_1;
            tableX[i][1] = X_i;
            X_i_plus_1 = X_i - ((evaluateFx(X_i) * (X_i_minus_1 - X_i)) / (evaluateFx(X_i_minus_1) - evaluateFx(X_i)));
            tableX[i][2] = X_i_plus_1;

            X_i_minus_1 = X_i;
            X_i = X_i_plus_1;

            if (i != 0) {
                double tmp = ((tableX[i][2] - tableX[i - 1][2]) / tableX[i][2]) * 100;
                if (tmp < 0) {
                    tmp = tmp * -1;
                }
                tableX[i][3] = tmp;
            }
        }

        return tableX; // Return table of results
    }

    // Method to evaluate a given function
    public static double evaluateFx(double x) {

        // Uncomment and modify this line to use a different function: 
        // return ( 9 * Math.pow(Math.E , -0.4 * x )) * Math.cos( 7 * x);
        return (9 * Math.pow(Math.E, -0.7 * x)) * Math.cos(4 * x) - 3.5;
        // return -43 + ( 3488.266045 * Math.sqrt(4.8 / Math.PI ) * Math.pow( Math.E , ( -1 / ( 0.16 * x ))));
        // return 75 * Math.pow(Math.E, -1.5 * x) + 20 * Math.pow(Math.E, -0.075 * x) - 15;
        // return (1.9520E-14 * Math.pow(x,4)) - (9.5838E-11 * Math.pow(x,3)) + (9.7215E-8 * Math.pow(x,2)) + (1.671E-4 * x) - 0.20597;
        // return ( 4 * Math.pow(x,2)) - ( 5 * x );
        // return (Math.pow(x, 3)) * Math.sin(x) + Math.exp(x) - Math.log(Math.pow(x, 2));
    }

    public static void main(String[] args) {
        double X_i_minus_1 = Double.parseDouble(args[0]);
        double X_i = Double.parseDouble(args[1]);

        double[][] tableX = secant(X_i_minus_1, X_i);
                         // | 1  | 1.11111100000000000 | 1.11111200000000000 | 1.45978345355362070 | 0.000000000000000000 |
        System.out.println("|Itr | X_i_minus_1         | X_i                 | X_i_plus_1           | Relative Error %    |");
        try {
            for (int i = 0; i < 8; i++) {
                System.out.printf("| %2d | %4.17f | %4.17f | %4.17f | %4.18f |\n", (i + 1), tableX[i][0], tableX[i][1],
                        tableX[i][2], tableX[i][3]);
            }
        } catch (Exception e) {
            System.out.println("Error: \n" + e.getMessage());
        }

    }
}
