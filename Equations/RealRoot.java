/*
 * Compilation  -> javac RealRoot.java
 * Execution    -> java RealRoot a b
 * Dependencies -> none
 */
package NumericalMethods.Equations;
import java.lang.Math;

public class RealRoot{
public static double[] interval(double start, double increment) {

    double table[] = new double[50];
    double calculationDone[] = new double[4];
    double iteration[] = new double[50];
    double function = 0;

    for (int i = 0; i < 50; i++) {
        // The mathematical function being used to calculate the roots:
        function = (9 * Math.pow(Math.E, -0.7 * start)) * Math.cos(4 * start) - 3.5;
        // Another possible mathematical functions:
        // function =  (1.9520E-14 * Math.pow(start,4)) - (9.5838E-11 * Math.pow(start,3)) + (9.7215E-8 * Math.pow(start,2)) + (1.671E-4 * start) - 0.20597;
        // function = -43 + (3488.266045 * Math.sqrt(4.8 / Math.PI) * Math.pow(Math.E, (-1 / (0.16 * start))));
        // function = 9 * Math.pow(Math.E, -0.4 * i) * Math.cos(7 * i);
        start += increment;
        table[i] = function;
        iteration[i] = start;
    }

    // The Bolzano's theorem is used to determine the interval where the root is located.
    for (int i = 0; i < 49; i++) {
        if ((table[i] < 0 && table[i + 1] > 0) || (table[i] > 0 && table[i + 1] < 0)) {
            calculationDone[0] = table[i];
            calculationDone[1] = iteration[i] - increment;
            calculationDone[2] = table[i + 1];
            calculationDone[3] = iteration[i];
            break;
        }
    }
    return calculationDone;
}

public static void main(String[] args) {

    double calculationDone[] = new double[4];
    double start, increment;

    try {
        start = Double.parseDouble(args[0]);
        increment = Double.parseDouble(args[1]);

        System.out.println("Function -> (9 * Math.pow(Math.E, -0.7 * x)) * Math.cos(4 * x) - 3.5");

        System.out.println("According to the Bolzano's theorem, there is a real root between: ");

        calculationDone = interval(start, increment);
        for (int i = 0; i < 12; i++) {

            calculationDone = interval(start, increment);

            if (calculationDone[0] == 0) {
                System.out.println("The real root is: " + calculationDone[1]);
            } else if (calculationDone[2] == 0) {
                System.out.println("The real root is: " + calculationDone[3]);
            } else {
                System.out.printf("(%5.11f,%5.11f)\n", calculationDone[1], calculationDone[3]);
            }
            start = calculationDone[1];
            increment /= 10;
        }
            

        } catch(Exception exc) {
            System.out.println("It is necessary to enter a start and an increment when executing the program, do not leave args[] empty.");
        }
    }
}
