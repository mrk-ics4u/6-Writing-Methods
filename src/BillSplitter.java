/*
 * Name:
 * Description:
 * Created by:
 * Last edited:
 */
import java.util.Scanner;

public class BillSplitter {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        // read the subtotal (double)
        double subtotal = Double.parseDouble(input.nextLine());


        // read the tip percent and number of people (int)


        // call printHeader() (defined below)


        // calculate tip using calculateTip(subtotal, percent)


        // calculate defaultTip using the one-argument overload calculateTip(subtotal)


        // calculate total as subtotal + tip, and perPerson as total / people


        // print the six output lines (README.md); dollar amounts via printf %.2f


    }

    // write printHeader() -- void, no parameters, prints "=== Receipt ==="


    // write calculateTip(double subtotal, double percent) -- returns subtotal * percent / 100


    // overload calculateTip(double subtotal) -- calls calculateTip(subtotal, 15) and returns the result


}
