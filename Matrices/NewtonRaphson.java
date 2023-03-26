/*
 * Compilation  -> javac NewtonRaphson.java
 * Execution    -> java NewtonRaphson
 * Dependencies -> none
 */
package NumericalMethods.Matrices;

public class NewtonRaphson {

    public static double[] solve(double[][] matrix) {

        // Gaussian elimination with partial pivoting
        for (int i = 0; i < matrix.length; i++) {
            int pivot = i;
            // Find the row with the largest pivot element
            for (int j = i + 1; j < matrix.length; j++) {
                if (Math.abs(matrix[j][i]) > Math.abs(matrix[pivot][i])) {
                    pivot = j;
                }
            }
            // Swap the current row with the row that has the largest pivot element
            double[] temp = matrix[i];
            matrix[i] = matrix[pivot];
            matrix[pivot] = temp;

            // Perform row operations to eliminate entries below the pivot
            for (int j = i + 1; j < matrix.length; j++) {
                double factor = matrix[j][i] / matrix[i][i];
                for (int k = i + 1; k < matrix.length + 1; k++) {
                    matrix[j][k] -= factor * matrix[i][k];
                }
            }
        }

        // Back-substitution to solve for the unknowns
        double[] result = new double[matrix.length];
        for (int i = matrix.length - 1; i >= 0; i--) {
            double sum = 0.0;
            for (int j = i + 1; j < matrix.length; j++) {
                sum += matrix[i][j] * result[j];
            }
            result[i] = (matrix[i][matrix.length] - sum) / matrix[i][i];
        }

        return result;
    }

    public static double[][] originalJacobian(double x, double y, double z) {
        // System of ecuations to be solved
        double[][] original = { { 2 * x, 2 * y, 2 * z, -Math.pow(x, 2) - Math.pow(y, 2) - Math.pow(z, 2) + 16 },
                                { 2 * x, 2 * y - 8, 2 * z, -Math.pow(x, 2) - Math.pow(y - 4, 2) - Math.pow(z, 2) + 16 },
                                { 2 * x, 2 * y - 4, 2 * z - 2 * Math.sqrt(12),-Math.pow(x, 2) - Math.pow(y - 2, 2) - Math.pow(z - Math.sqrt(12), 2) + 16 } };

        // double[][] original = {{2*x,1,-Math.pow(x, 2)-y+37},{1,-2*y,-x+Math.pow(y, 2)+5}};

        return original;
    }

    public static void newton(double[] vector) {
        for (int q = 0; q < 16; q++) {

            // Print the iteration number
            System.out.println("\n\nITERATION " + (q + 1));

            // Get the Jacobian matrix for the current values of x, y, and z
            double[][] jacobian = originalJacobian(vector[0], vector[1], vector[2]);
            // double[][] jacobian = originalJacobian(vector[0],vector[1],0);

            // Print the Jacobian matrix
            for (int i = 0; i < jacobian.length; i++) {
                for (int j = 0; j < jacobian[i].length; j++) {
                    System.out.printf("%-10.10f ", jacobian[i][j]);
                }
                System.out.println("");
            }

            // Solve the system of equations using the Jacobian matrix
            double[] result = solve(jacobian);

            // Update the values of x, y, and z using the solution vector
            for (int i = 0; i < result.length; i++) {
                vector[i] += result[i];
            }

            // Print the solution vector
            switch (vector.length) {
                case 1:
                    System.out.printf("%nh = %-10.10f | x = %-10.10f %n", result[0], vector[0]);
                    break;
                case 2:
                    System.out.printf("h = %-10.10f | x = %-10.14f %n", result[0], vector[0]);
                    System.out.printf("j = %-10.10f | y = %-10.14f %n", result[1], vector[1]);
                    break;

                case 3:
                    System.out.printf("h = %-10.10f | x = %-10.14f %n", result[0], vector[0]);
                    System.out.printf("j = %-10.10f | y = %-10.14f %n", result[1], vector[1]);
                    System.out.printf("k = %-10.10f | z = %-10.14f %n", result[2], vector[2]);
                    break;
                default:
                    break;
            }
        }
        for (int q = 0; q < 16; q++) {

            System.out.println("\n\nITERATION " + (q + 1));

            double[][] jacobiana = originalJacobian(vector[0], vector[1], vector[2]);
            // double[][] jacobiana = originalJacobian(vector[0],vector[1],0);

            for (int i = 0; i < jacobiana.length; i++) {
                for (int j = 0; j < jacobiana[i].length; j++) {
                    System.out.printf("%-10.10f ", jacobiana[i][j]);
                }
                System.out.println("");
            }

            double[] result = solve(jacobiana);

            for (int i = 0; i < result.length; i++) {
                vector[i] += result[i];
            }

            switch (vector.length) {
                case 1:
                    System.out.printf("%nh = %-10.10f | x = %-10.10f %n", result[0], vector[0]);
                    break;
                case 2:
                    System.out.printf("h = %-10.10f | x = %-10.14f %n", result[0], vector[0]);
                    System.out.printf("j = %-10.10f | y = %-10.14f %n", result[1], vector[1]);
                    break;

                case 3:
                    System.out.printf("h = %-10.10f | x = %-10.14f %n", result[0], vector[0]);
                    System.out.printf("j = %-10.10f | y = %-10.14f %n", result[1], vector[1]);
                    System.out.printf("k = %-10.10f | z = %-10.14f %n", result[2], vector[2]);
                    break;
                default:
                    break;
            }
        }
    }

    public static void main(String[] args) {

        double[] vector = { 1, 2.82, 2.33 };
        // double[] vector = {1,1,1};
        // double[] vector = {5,0};

        newton(vector);

    }

}
