/*
 * Compilation  -> javac HighAcuracy.java
 * Execution    -> java HighAcuracy
 * Dependencies -> none
 */
package NumericalMethods.Derivates;
public class HighAccuracy{
    // This formula was taken from a book on numerical methods
    public static double centeredFormula(double h, double Xi_minus_2, double Xi_minus_1, double Xi_plus_1, double Xi_mas2){
        double numerator = -Xi_mas2 + 8*Xi_plus_1 - 8*Xi_minus_1 + Xi_minus_2;
        double denominator = 12 * h;
        return numerator / denominator ;
    }

    // The function to be differentiated is evaluated at the given points and stored
    // in an array, which is then used to compute the derivative using the centered
    // difference formula. It is necessary to manually enter the function to be derived.
    public static void centered(double x, double h){
        double Xi_minus_2 = x - ( 2 * h );
        double Xi_minus_1 = x - h;
        double Xi_plus_1  = x + h;
        double Xi_plus_2  = x + ( 2 * h );

        double[] functions = new double[5];

        /* function -> e^(cos(x^2)) * sinh(ln(2x)) * tan(x^3) */
        functions[0] = Math.pow(Math.E, Math.cos(Math.pow(Xi_minus_2, 2))) * Math.sinh(Math.log(2 * Xi_minus_2)) * Math.tan(Math.pow(Xi_minus_2, 3));
        functions[1] = Math.pow(Math.E, Math.cos(Math.pow(Xi_minus_1, 2))) * Math.sinh(Math.log(2 * Xi_minus_1)) * Math.tan(Math.pow(Xi_minus_1, 3));
        functions[2] = Math.pow(Math.E, Math.cos(Math.pow(x, 2))) * Math.sinh(Math.log(2 * x)) * Math.tan(Math.pow(x, 3));
        functions[3] = Math.pow(Math.E, Math.cos(Math.pow(Xi_plus_1, 2))) * Math.sinh(Math.log(2 * Xi_plus_1)) * Math.tan(Math.pow(Xi_plus_1, 3));
        functions[4] = Math.pow(Math.E, Math.cos(Math.pow(Xi_plus_2, 2))) * Math.sinh(Math.log(2 * Xi_plus_2)) * Math.tan(Math.pow(Xi_plus_2, 3));

        /* function -> sqrt( 1 - csc^3(x) * cot(x) + sec^3(x) * tan(x) ) */
        //functions[0] = Math.sqrt( 1 - Math.pow((1/Math.sin(Xi_minus_2)),3) * (1/Math.tan(Xi_minus_2)) + Math.pow(1/Math.cos(Xi_minus_2),3) * Math.tan(Xi_minus_2));
        //functions[1] = Math.sqrt( 1 - Math.pow((1/Math.sin(Xi_minus_1)),3) * (1/Math.tan(Xi_minus_1)) + Math.pow(1/Math.cos(Xi_minus_1),3) * Math.tan(Xi_minus_1));
        //functions[2] = Math.sqrt( 1 - Math.pow((1/Math.sin(x)),3) * (1/Math.tan(x)) + Math.pow(1/Math.cos(x),3) * Math.tan(x));
        //functions[3] = Math.sqrt( 1 - Math.pow((1/Math.sin(Xi_plus_1)),3) * (1/Math.tan(Xi_plus_1)) + Math.pow(1/Math.cos(Xi_plus_1),3) * Math.tan(Xi_plus_1));
        //functions[4] = Math.sqrt( 1 - Math.pow((1/Math.sin(Xi_plus_2)),3) * (1/Math.tan(Xi_plus_2)) + Math.pow(1/Math.cos(Xi_plus_2),3) * Math.tan(Xi_plus_2));

        System.out.printf("Xi-2 = %10.8f | f(Xi-2) = %10.8f\n", Xi_minus_2, functions[0]);
        System.out.printf("Xi-1 = %10.8f | f(Xi-1) = %10.8f\n", Xi_minus_1, functions[1]);
        System.out.printf("X    = %10.8f | f(X)    = %10.8f\n", x, functions[2]);
        System.out.printf("Xi+1 = %10.8f | f(Xi+1) = %10.8f\n", Xi_plus_1, functions[3]);
        System.out.printf("Xi+2 = %10.8f | f(Xi+2) = %10.8f\n", Xi_plus_2,functions[4]);

        System.out.println("The value of the first derivative of the function by the centered difference formula is: \nf\'(x) = "+
                           centeredFormula(h, functions[0], functions[1], functions[3], functions[4]));
    }
    public static void main(String[]args){
        // The lower the value of h, the higher the accuracy of the value of the first derivative.
        // centered( x, h );
        centered(1,0.00001);
    }
}
