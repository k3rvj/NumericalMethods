/*
 * Compilation  -> javac GaussSeidel
 * Execution    -> java GaussSeidel
 * Dependencies -> none
 */
package NumericalMethods.Matrices;
// A class to implement the Gauss-Seidel method to solve a system of linear equations
public class GaussSeidel {
    
    // A method to perform the Gauss-Seidel iteration to update values of x, y, and z
    public static void gaussSeidel() {
        
        // Initialize a vector with initial values of 0 for x, y, and z
        double[] vector = {0, 0, 0};

        double[] result;  // Declare a double array to store the results of each iteration
        
        // Print a header for the output
        System.out.println("-------------------------------------------------------");
        
        // Perform 20 iterations to update the values of x, y, and z
        for (int i = 0; i < 20; i++) {
            
            // Call the evaluate method to calculate new values of x, y, and z and store the results in "result"
            result = evaluate(vector[0], vector[1], vector[2]);
            
            // Print the results of the iteration
            System.out.println("Iteration " + (i + 1));
            System.out.printf("x = %-2.10f | y = %-2.10f | z = %-2.10f\n\n", result[0], result[1], result[2]);
            
            // Update the values of x, y, and z for the next iteration
            vector[0] = result[0];
            vector[1] = result[1];
            vector[2] = result[2];
        }
        
        // Print the final values of x, y, and z
        System.out.println("\nTherefore, the values of x, y, z are:\n");
        System.out.printf("x = %-2.10f\n", vector[0]);
        System.out.printf("y = %-2.10f\n", vector[1]);
        System.out.printf("z = %-2.10f\n", vector[2]);
    } 

    // A method to evaluate new values of x, y, and z using the Gauss-Seidel method
    public static double[] evaluate(double x, double y, double z) {
        double[] evaluated = {0, 0, 0};  // Declare and initialize a double array to store the evaluated values of x, y, and z

        double x1 = 0, y1 = 0, z1 = 0;  // Declare variables to store the updated values of x, y, and z

        // Calculate the updated values of x, y, and z using the Gauss-Seidel method
        x1 = (7.85 + 0.1 * y + 0.2 * z) / 3;
        y1 = (-19.3 - 0.1 * x1 + 0.3 * z) / 7;
        z1 = (71.4 - 0.3 * x1 + 0.2 * y1) / 10;

        // Store the updated values of x, y, and z in the "evaluated" array and return it
        evaluated[0] = x1;
        evaluated[1] = y1;
        evaluated[2] = z1;
        return evaluated;
    }
    
    // A main method to call the "gaussSeidel" method to execute the program
    public static void main(String[] args) {
        gaussSeidel();
    }
}
