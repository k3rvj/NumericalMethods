package NumericalMethods.Equations;

public class NewtonRaphson {

    // Method to calculate the root of an equation using Newton-Raphson
    public static double[][] newRaphson(double Xn) {
        // Create a 10x3 array to store the values of Xn, Xn+1 and the relative error for each iteration
        double[][] tablaX = new double[10][3];

        // Variable to store the value of Xn+1
        double X_n_plus_1 = 0;

        // Variables to store the value of the function and its derivative
        double function = 0, derivative = 0;

        // Loop through 10 iterations
        for (int i = 0; i < 10; i++) {
            tablaX[i][0] = Xn;

            // Evaluate the function and its derivative at Xn
            function = 75 * Math.pow(Math.E, -1.5 * i) + 20 * Math.pow(Math.E, -0.075 * i) - 15;
            derivative = -112.5 * Math.pow(Math.E, -1.7 * i) - 1.5 * Math.pow(Math.E, -0.07 * i);

            // Calculate Xn+1 using Newton-Raphson formula
            X_n_plus_1 = Xn - (function / derivative);
            tablaX[i][1] = X_n_plus_1;

            // Update Xn for the next iteration
            Xn = X_n_plus_1;

            // Calculate and store the relative error if it's not the first iteration
            if (i != 0) {
                double tmp = ((tablaX[i][1] - tablaX[i - 1][1]) / tablaX[i][1]) * 100;
                if (tmp < 0) {
                    tmp = tmp * -1;
                }
                tablaX[i][2] = tmp;
            }

        }
        // Return the array with the values of Xn, Xn+1 and the relative error for each iteration
        return tablaX;
    }

    // Main method to test the implementation of Newton-Raphson method
    public static void main(String[] args) {
        // Get the initial guess for the root from command line arguments
        double Xn = Double.parseDouble(args[0]);

        // Calculate the root using Newton-Raphson method
        double[][] tablaX = newRaphson(Xn);

        // Print a table with the values of Xn, Xn+1 and the relative error for each iteration
        System.out.println("|Itr | Xn                  | X_n_plus_1         | Relative Error %     |");
        try {
            for (int i = 0; i < 8; i++) {
                System.out.printf("| %2d | %4.17f | %4.17f | %4.18f |\n", (i + 1), tablaX[i][0], tablaX[i][1],
                        tablaX[i][2]);
            }
        } catch (Exception e) {
            System.out.println("Error:\n" + e.getMessage());
        }
    }
}
