/*
 * BillSplitterTest.java -- unit tests for the Lesson 6 exercise
 * ICS 4U0 - Lesson 6 Exercise: Bill Splitter
 *
 * These tests run BillSplitter.main() exactly as it will be graded: they
 * feed it the input lines it expects on System.in and check the lines it
 * prints to System.out. You don't need to change this file -- just run the
 * tests (see README.md) and fix BillSplitter.java until they all pass.
 */
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.Locale;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BillSplitterTest {

    private InputStream originalIn;
    private PrintStream originalOut;
    private Locale originalLocale;

    @BeforeEach
    public void redirectIoAndPinLocale() {
        originalIn = System.in;
        originalOut = System.out;
        originalLocale = Locale.getDefault();
        // printf("%.2f") follows the default locale -- on a machine that uses
        // a comma for decimals, output like "9,36" would fail the exercise
        // for reasons that have nothing to do with your code, so every test
        // pins the locale before calling main().
        Locale.setDefault(Locale.CANADA);
    }

    @AfterEach
    public void restoreIoAndLocale() {
        System.setIn(originalIn);
        System.setOut(originalOut);
        Locale.setDefault(originalLocale);
    }

    /**
     * Feeds {@code input} to BillSplitter.main() on System.in and returns
     * everything it printed to System.out.
     */
    private String run(String input) {
        System.setIn(new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8)));
        ByteArrayOutputStream captured = new ByteArrayOutputStream();
        System.setOut(new PrintStream(captured, true, StandardCharsets.UTF_8));

        BillSplitter.main(new String[0]);

        return captured.toString(StandardCharsets.UTF_8);
    }

    /**
     * Compares output line by line, trimming trailing whitespace on each
     * line and ignoring one blank line at the very end (println's final
     * newline). Everything else -- including the space after each colon and
     * dollar sign -- is compared exactly.
     */
    private void assertOutputEquals(String expected, String actual) {
        String[] expectedLines = expected.stripTrailing().split("\n", -1);
        String[] actualLines = actual.stripTrailing().split("\n", -1);

        assertEquals(expectedLines.length, actualLines.length,
                "Expected " + expectedLines.length + " line(s) of output, got " + actualLines.length
                        + ".\n--- expected ---\n" + expected + "--- actual ---\n" + actual);

        for (int i = 0; i < expectedLines.length; i++) {
            assertEquals(expectedLines[i].stripTrailing(), actualLines[i].stripTrailing(),
                    "Line " + (i + 1) + " didn't match.\n--- expected ---\n" + expected
                            + "--- actual ---\n" + actual);
        }
    }

    @Test
    public void exampleFromReadme() {
        String input = "52.00\n18\n3\n";
        String expected = "=== Receipt ===\n"
                + "Subtotal: $52.00\n"
                + "Tip (18%): $9.36\n"
                + "Default tip (15%): $7.80\n"
                + "Total: $61.36\n"
                + "Per person: $20.45\n";
        assertOutputEquals(expected, run(input));
    }

    @Test
    public void tipUsesMultiplyBeforeDivideNotIntegerTruncation() {
        // percent and 100 are both int. A solution that writes
        // percent / 100 * subtotal truncates 10 / 100 to 0 before it ever
        // meets subtotal, so the tip prints as $0.00 instead of $10.00.
        String input = "100.00\n10\n2\n";
        String expected = "=== Receipt ===\n"
                + "Subtotal: $100.00\n"
                + "Tip (10%): $10.00\n"
                + "Default tip (15%): $15.00\n"
                + "Total: $110.00\n"
                + "Per person: $55.00\n";
        assertOutputEquals(expected, run(input));
    }

    @Test
    public void defaultTipIsAlwaysFifteenPercentRegardlessOfEnteredPercent() {
        String input = "200.00\n5\n4\n";
        String expected = "=== Receipt ===\n"
                + "Subtotal: $200.00\n"
                + "Tip (5%): $10.00\n"
                + "Default tip (15%): $30.00\n"
                + "Total: $210.00\n"
                + "Per person: $52.50\n";
        assertOutputEquals(expected, run(input));
    }

    @Test
    public void zeroPercentTipStillHasNonZeroDefaultTip() {
        String input = "80.00\n0\n1\n";
        String expected = "=== Receipt ===\n"
                + "Subtotal: $80.00\n"
                + "Tip (0%): $0.00\n"
                + "Default tip (15%): $12.00\n"
                + "Total: $80.00\n"
                + "Per person: $80.00\n";
        assertOutputEquals(expected, run(input));
    }

    @Test
    public void perPersonSplitRoundsToTwoDecimals() {
        String input = "45.00\n20\n7\n";
        String expected = "=== Receipt ===\n"
                + "Subtotal: $45.00\n"
                + "Tip (20%): $9.00\n"
                + "Default tip (15%): $6.75\n"
                + "Total: $54.00\n"
                + "Per person: $7.71\n";
        assertOutputEquals(expected, run(input));
    }
}
