/*
 * Name:        Lesson 6: Writing Methods (MethodsDemo.java)
 * Description: A runnable tour of declaring methods, parameters, call by value,
 *              return values, and overloading.
 * Created by:  Mr Kowalczewski
 * Last edited: 2026-09-11
 */

public class MethodsDemo {

    public static void main(String[] args) {
        // below we are calling methods (functions) that are defined later in this file.
        // main is a method too -- this whole file is nothing but method declarations.
        sayHello();
        //parametersAndCallByValue();
        //returningAValue();
        //overloadingDemo();
        //commonMethodBugs();
    }

    // method to demonstrate a void method with no parameters
    public static void sayHello() {
        System.out.println();
        System.out.println("=== 1. from python functions to java methods ===");

        System.out.println("Hello from a method!");
        // Every helper method in every Demo file so far has looked exactly like this one.
    }

    // method to demonstrate typed parameters and call by value
    public static void parametersAndCallByValue() {
        System.out.println();
        System.out.println("=== 2. methods with parameters ===");

        greet("Ada", 3);

        // Call by value: the parameter is a COPY. Changing it inside the method
        // never touches the caller's variable.
        int score = 10;
        System.out.println("before call: score = " + score);
        tryToDoubleIt(score);
        System.out.println("after call:  score = " + score);   // <-- still 10!
    }

    // method to demonstrate calling a void method with parameters
    public static void greet(String name, int times) {
        for (int i = 0; i < times; i++) {
            System.out.println("Hi, " + name + "!");
        }
    }

    // method to demonstrate that a parameter is a local copy, not the caller's variable
    public static void tryToDoubleIt(int number) {
        number = number * 2;   // only changes this method's own copy
        System.out.println("inside the method: number = " + number);
    }

    // method to demonstrate non-void methods and the return statement
    public static void returningAValue() {
        System.out.println();
        System.out.println("=== 3. methods that return a value ===");

        double result = square(6.0);
        System.out.println("square(6.0) = " + result);

        // The returned value has to be used -- storing it, printing it, or using
        // it in an expression. Calling square(4.0); alone would compile and do nothing useful.
        System.out.println("square(4.0) + 1 = " + (square(4.0) + 1));

        System.out.println("absoluteValue(-9) = " + absoluteValue(-9));
        System.out.println("absoluteValue(9)  = " + absoluteValue(9));
    }

    // method to demonstrate a non-void method: return type replaces void
    public static double square(double x) {
        return x * x;
    }

    // method to demonstrate an early return -- once return runs, the method exits immediately
    public static int absoluteValue(int x) {
        if (x < 0) {
            return -x;   // <-- exits right here when x is negative
        }
        return x;
    }

    // method to demonstrate overloading: same name, different parameter lists
    public static void overloadingDemo() {
        System.out.println();
        System.out.println("=== 4. overloading ===");

        System.out.println("maxOf(3, 7)      = " + maxOf(3, 7));            // picks maxOf(int, int)
        System.out.println("maxOf(3, 7, 5)   = " + maxOf(3, 7, 5));         // picks maxOf(int, int, int)
        System.out.println("maxOf(3.5, 7.2)  = " + maxOf(3.5, 7.2));        // picks maxOf(double, double)

        // Java chooses which version to run at compile time, based on the
        // number and types of the arguments in the call.
    }

    // method to demonstrate overload #1: two ints
    public static int maxOf(int a, int b) {
        if (a > b) {
            return a;
        }
        return b;
    }

    // method to demonstrate overload #2: three ints, reusing overload #1
    public static int maxOf(int a, int b, int c) {
        return maxOf(maxOf(a, b), c);
    }

    // method to demonstrate overload #3: two doubles -- return type alone can't overload,
    // the parameter list has to differ
    public static double maxOf(double a, double b) {
        if (a > b) {
            return a;
        }
        return b;
    }

    // method to demonstrate common mistakes when writing methods
    public static void commonMethodBugs() {
        System.out.println();
        System.out.println("=== 5. common method bugs ===");

        // A method declared to return a value must return one on every possible
        // path, or it will not compile:
        //
        // public static int broken(int x) {
        //     if (x > 0) {
        //         return x;
        //     }
        //     // <-- missing return here: "missing return statement" compile error
        // }

        // Calling a non-void method and throwing away its result compiles, but
        // wastes the work the method did:
        square(5.0);   // legal, but the computed value goes nowhere
        System.out.println("(the line above computed square(5.0) and threw the result away)");

        // A void method cannot be used inside an expression -- it has no value to use:
        // int x = greet("Sam", 1);   // <-- would not compile: greet returns nothing

        System.out.println("(the two bug examples above are commented out on purpose -- they don't compile)");
    }
}



