# Lesson 6 - Writing Methods

You've been calling methods since Lesson 1 (`println`, `next`, `length`, `Math.sqrt`) and every Demo file has been full of methods you didn't write (`stringMethodsBasics`, `whileLoopBasics`, and so on, each called once from `main`). This lesson is about writing your own: giving them parameters, and getting a value back out of them.

---

## 1. From Python Functions to Java Methods

Python defines a function with `def`, no type declarations, and an indented body.

```python
# Python
def say_hello():
    print("Hello!")
```

Java's version needs a few more words, but the idea is identical: a name, a parameter list (empty here), and a body in braces.

```java
// Java
public static void sayHello() {
    System.out.println("Hello!");
}
```

`public` means that it can be accessed from outside the class (different classes, different files etc). `void` means this method doesn't send a value back to whoever called it. `static` means the method belongs to the class itself, not to an object. Every method you write in this lesson stays `static`; object-based methods are a later unit.

---

## 2. Methods with Parameters

A parameter list works the same way as Python's, except every parameter needs a declared type.

```python
# Python
def greet(name, times):
    for i in range(times):
        print("Hi, " + name + "!")
```

```java
// Java
public static void greet(String name, int times) {
    for (int i = 0; i < times; i++) {
        System.out.println("Hi, " + name + "!");
    }
}
```

Calling it passes **arguments**, matched to the parameters in order:

```java
greet("Ada", 3);
```

Java always passes arguments **by value**: the parameter is a brand-new local copy of whatever was passed in. Changing a parameter inside a method never touches the caller's variable.

```java
public static void tryToDoubleIt(int number) {
    number = number * 2;   // changes only this method's own copy
}

int score = 10;
tryToDoubleIt(score);
System.out.println(score);   // still 10
```

This is the same behaviour Python already gives you for numbers and strings, since neither language lets a function reassign the caller's variable through a plain parameter. The difference is that Java's rule has no exceptions to learn later: it is always a copy, for every type covered so far.

---

## 3. Methods that Return a Value

Replace `void` with a real type, and use `return` to send a value back.

```java
public static double square(double x) {
    return x * x;
}
```

```java
double result = square(6.0);
System.out.println(result);          // 36.0
System.out.println(square(4.0) + 1); // 17.0 -- the return value can be used in an expression
```

The returned value has to go somewhere: stored in a variable, printed, or used in a larger expression. Calling `square(6.0);` on its own line compiles, but the `36.0` it computed is thrown away.

`return` also ends the method immediately, wherever it appears:

```java
public static int absoluteValue(int x) {
    if (x < 0) {
        return -x;   // exits right here when x is negative
    }
    return x;
}
```

If `x` is negative, the second `return x;` never runs at all. Flow of control jumps straight back to whoever called `absoluteValue`.

---

## 4. Overloading

Python lets a parameter have a default value right in the function header:

```python
# Python
def calculate_tip(subtotal, percent=15):
    return subtotal * percent / 100
```

Java has no equivalent syntax. Instead, write two methods with the **same name** and **different parameter lists**; Java picks the matching one based on the arguments in the call.

```java
public static double calculateTip(double subtotal, double percent) {
    return subtotal * percent / 100;
}

public static double calculateTip(double subtotal) {
    return calculateTip(subtotal, 15);   // reuse the two-argument version
}
```

```java
calculateTip(52.00, 18);   // uses the two-argument version
calculateTip(52.00);       // uses the one-argument version, defaults to 15%
```

This is called **overloading**: multiple methods sharing a name, distinguished by their number and/or types of parameters. The return type alone doesn't count; two methods with the same name and the same parameter list, differing only in return type, is a compile error, not an overload.

Overloading isn't limited to filling in defaults. `maxOf(int, int)`, `maxOf(int, int, int)`, and `maxOf(double, double)` can all coexist as three unrelated overloads, each handling a shape of input the others don't.

---

## 5. Common Method Bugs

**Missing a return on some path.** A method declared to return a value must return one no matter which branch runs, or it won't compile:

```java
public static int broken(int x) {
    if (x > 0) {
        return x;
    }
    // no return here if x <= 0 -- "missing return statement"
}
```

**Discarding a return value.** Calling a non-void method for its side effects, when it doesn't have any, silently wastes the computed result:

```java
square(5.0);   // legal, but the 25.0 it computed goes nowhere
```

**Using a void method as if it returned something.** A `void` method has no value to store or use in an expression:

```java
int x = greet("Sam", 1);   // does not compile -- greet returns nothing
```

---

## Try It Yourself

Compile and run the companion file in this folder:

```bash
javac MethodsDemo.java
java MethodsDemo
```

Then work on the exercise in `BillSplitter.java`.

---

## Course Expectations

### ICS 4U

Nothing assessed (yet).  Eventually you will need to write methods as part of writing classes and creating objects.

### AP Expectations

The following are expectations of the AP exam and will show up on the final exam:

- A method is a named block of code that only runs when called; procedural abstraction lets a method be used by knowing what it does, without knowing how it was written (Topic 1.9, Method Signatures — 1.9.A.1)
- A parameter is a variable declared in a method's header; a method's signature consists of its name and its ordered list of parameter types (Topic 1.9, Method Signatures — 1.9.A.2)
- A void method has no return value and cannot be called as part of an expression (Topic 1.9, Method Signatures — 1.9.B.1)
- A non-void method returns a value of the type declared in its header; the return value must be stored in a variable or used as part of an expression (Topic 1.9, Method Signatures — 1.9.B.2)
- Arguments must be compatible in number and order with a method's parameter list, and are passed by call by value, initializing each parameter with a copy of the argument (Topic 1.9, Method Signatures — 1.9.B.3)
- Overloading: multiple methods sharing a name but differing in signature (Topic 1.9, Method Signatures — 1.9.B.4)
- A method call transfers flow of control to the method's body, returning it to the caller once the last statement runs or a `return` statement executes (Topic 1.9, Method Signatures — 1.9.B.5)
- Class methods are associated with the class, not an instance, and are marked with `static` in the header (Topic 1.10, Calling Class Methods — 1.10.A.1)
- A class method is typically called using the class name and the dot operator; within the defining class, the class name is optional (Topic 1.10, Calling Class Methods — 1.10.A.2)
