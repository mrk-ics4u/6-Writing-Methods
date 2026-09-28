# ICS 4U0 — Lesson 6: Writing Methods

## Exercise

You're building a bill-splitting calculator for a group at a restaurant.

Everything you need is from Lesson 6:

- a **void method** with no parameters to print a header,
- a **method that returns a value**, with parameters, to calculate a tip, and
- **overloading** that method to support a default tip percentage.

- Read the subtotal (`double`), the tip percentage to use, and the number of people in the party (both whole numbers).
- Write `printHeader()`: a void method, no parameters, that prints `=== Receipt ===`.
- Write `calculateTip(double subtotal, double percent)`: returns the tip, computed as `subtotal * percent / 100`.
- Overload it as `calculateTip(double subtotal)`: returns the tip using a fixed default of 15%.
- In `main`, call `printHeader()`, compute the tip at the entered percentage and the tip at the default 15%, then the total (subtotal plus the entered-percentage tip) and the per-person share (total divided by the number of people).
- Print the six lines of output exactly as shown below.

---

## Input

The subtotal, the tip percentage to use, and the number of people, one per line:

| Line | Value | Type |
|------|-------|------|
| 1 | Subtotal | decimal |
| 2 | Tip percentage to use | whole number |
| 3 | Number of people in the party | whole number |

Example input:

```
52.00
18
3
```

---

## Output

Exactly six lines:

```
=== Receipt ===
Subtotal: $<amount to 2 decimals>
Tip (<percent>%): $<amount to 2 decimals>
Default tip (15%): $<amount to 2 decimals>
Total: $<amount to 2 decimals>
Per person: $<amount to 2 decimals>
```

For the example input above, your program must print **exactly**:

```
=== Receipt ===
Subtotal: $52.00
Tip (18%): $9.36
Default tip (15%): $7.80
Total: $61.36
Per person: $20.45
```

Every space, dollar sign, and colon is compared. `Subtotal:$52.00` and `Subtotal: $52.00` are not the same answer.

---

## Testing

- Test your code yourself first.
- Open the **Testing** panel from the sidebar (flask icon) and click ▶ **Run Tests**.

A green check next to a test means it passed; a red X means it failed and will show you the expected vs. actual output.
