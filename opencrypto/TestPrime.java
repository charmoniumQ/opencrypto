package opencrypto;
import java.util.ArrayList;
import org.junit.*;
import static java.lang.Math.*;
import static org.junit.Assert.*;
import static opencrypto.Prime.*;

public class TestPrime {
	// Here for assert: options http://junit.org/javadoc/latest/index.html

    @Test
    public void testNthPrime() {
		// from preprogrammed cache
		assertEquals(nthPrime(0), 2);
		assertEquals(nthPrime(100), 547);

		// from generated cache
		assertEquals(nthPrime(300), 1993);
		assertEquals(nthPrime(480), 3433);
    }

    @Test
    public void testPrimeAfter() {
		// this test relies on testNthPrime to be correct
		// if that test fails, fix that first

		assertEquals(nthPrime(primeAfter(0)), 2);
		assertEquals(nthPrime(primeAfter(100)), 101);
		assertEquals(nthPrime(primeAfter(67)), 71);
    }

    @Test
    public void testCheckPrime() {
		assertTrue(checkPrime(2));
		assertTrue(checkPrime(73));
		assertFalse(checkPrime(39));
		assertFalse(checkPrime(49));
		assertFalse(checkPrime(1));
    }

    @Test
    public void testGetPrime() {
		int[][] bounds = new int[][]{{7, 23}, {7, 24}, {6, 23}, {6, 24}};
		int g;
		for (int i = 0; i < 400; ++i) {
			g = getPrime(bounds[i%4][0], bounds[i%4][1]);
			assertTrue(checkPrime(g));
			assertTrue(7 <= g);
			assertTrue(g < 23);
		}
    }

	@Test
	public void testGetFactorsAL() {
		// getFactors just depends on getFactors AL
		// so there is no need to test seperately
		int[] tests = new int[]{2, 3, 5, 6, 7, 10, 15, 26};
		for (int i = 2; i < tests.length; ++i) {
			ArrayList<Integer> factors = getFactorsAL(tests[i]);
			int product = 1;
			for (int factor : factors) {
				product *= factor;
			}
			assertEquals(product, tests[i]);
		}
	}
}
