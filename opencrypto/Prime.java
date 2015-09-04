package opencrypto;

import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import java.lang.Math;

public class Prime {

	private static List<Integer> primeList = new ArrayList<Integer>(Arrays.asList(2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97, 101, 103, 107, 109, 113, 127, 131, 137, 139, 149, 151, 157, 163, 167, 173, 179, 181, 191, 193, 197, 199));
	private static int primeCandidate = primeList.get(primeList.size() - 1) + 2; // Next possible prime number

	public static int nthPrime(int n) {
		// get the nth prime for n = {0, 1, 2, 3, ...}
		// throws a RuntimeException if n < 0
		// useful for code like this:
		//
		// int prime;
		// for (int i = 0; i < 10; ++i) {
		//     prime = nthPrime(i);
		//     /* do something with prime */
		// }

		if (n < 0) {
			throw new RuntimeException("Cannot get nth prime for n < 0." + 
			                           " n = " + n);
		}

		while (n >= primeList.size()) {
			int sqrt = (int) Math.sqrt(primeCandidate);
			int i = 0;
			int prime;
			while (i < primeList.size()) {
				prime = primeList.get(i);
				if (prime <= sqrt) {
					if (primeCandidate % prime == 0) {
						// prime divides primeCandidate
						// therefore primeCandidate is composite
						break;
					}
				} else {
					// prime > sqrt(primeCandidate)
					// no prime where prime <= sqrt(primeCandidate)...
					// divide prime, therefore primeCandidate is prime
					primeList.add(primeCandidate);
					break;
				}
				++i;
			}
			primeCandidate += 2;
		}
		return primeList.get(n);
	}

	public static int primeAfter(int n) {
		// Tells you the smallest possible x such that nthPrime(x) > n
		// For example, primeAfter(10) == 4 since nthPrime(4) == 11
		int i = 0;
		while (nthPrime(i) <= n) {
			i++;
		}
		return i;
	}

	public static boolean checkPrime(int x) {
		if (x <= 1) { return false; }
		int sqrt = (int) Math.sqrt(x);
		int i = 0;
		int prime = nthPrime(i);
		while (prime <= Math.sqrt(x)) {
			if (x % prime == 0) {
				// prime divides x
				// therefore x is composite
				return false;
			}
			++i;
			prime = nthPrime(i);
		}
		// prime > x
		// all of the primes where prime <= sqrt(x) do not divide x
		// therefore x is prime
		return true;
	}

    //Check if two numbers are coprime by checking their GCD
    public static boolean checkCoprime(int x, int y) {
	if (CryptFunc.gcd(x,y) == 1) {
	    return true;
	}
	return false;
    }

	public static int getPrime(int lower, int upper) {
		// gets a prime between lower (inclusive) and upper (exclusive)
		// throws a RuntimeException if no prime is found between lower and
		// upper
		int lowerN;
		// make lowerN the ordinal of the next prime greater than or to lower
		if (checkPrime(lower)) {
			lowerN = primeAfter(lower) - 1;
		} else {
			lowerN = primeAfter(lower);
		}

		// make upperN the last prime less than upper
		int upperN = primeAfter(upper) - 1;

		if (upperN - lowerN < 1) {
			throw new RuntimeException("No prime found between " + lower +
			                           " and " + upper);
		}

		int actualN = (int) (Math.random() * (upperN - lowerN)) + lowerN;
		// lowerN <= actualN <= upperN

		// nthPrime is monotonic
		// nthPrime(getPrimeAfterN(x)) > x
		// nthPrime(getPrimeAfterN(x) - 1) <= x

		int actual = nthPrime(actualN);
		// lower <= actual <= upper
		return actual;
	}

    //Generate a coprime by generating a random number and seeing if it coprime
    public static int getCoprime(int lower, int upper, int coprime) {
	int count, prime;
	count = coprime * 2;
	if (lower < 0 || upper < 1 || lower > upper) {
	    return -1;
	}
	while (count > 0) {
	    prime = (int)Math.round(Math.random() * (upper - lower) + lower);
	    if (checkCoprime(prime, coprime)) {
		return prime;
	    }
	    count--;
	}
	return -1;
    }

	//Return the list of prime factors of a number in an ArrayList
	public static ArrayList<Integer> getFactorsAL(int n) {
		//Return the list of prime factors of a number in an ArrayList
		// note that this does not return repeated factors,
		// so getFactorsAL(8) == [2]
		ArrayList<Integer> factorList = new ArrayList<Integer>();
		int prime;
		for (int i = 0; i < primeAfter(n); ++i) {
			prime = nthPrime(i);
			if (n % prime == 0) {
				factorList.add(prime);
			}
		}
		return factorList;
	}

	//Return the list of prime factors of a number in an array
	public static int[] getFactors(int n) {
		ArrayList<Integer> factorList = getFactorsAL(n);
		int[] factorArray = new int[factorList.size()];
		for (int i = 0; i < factorList.size(); ++i) {
			factorArray[i] = factorList.get(i);
		}
		return factorArray;
	}

}
