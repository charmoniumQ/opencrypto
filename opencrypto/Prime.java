//Author: Nicklaus McClendon (kulinacs@gmail.com)
//Last Updated: 3 September 2015
//https://github.com/KULinacs

package opencrypto;

public class Prime {

    private static int count, prime;
    
    public Prime() {
    }

    //Check if a number is prime using trial division
    public static boolean checkPrime(int x) {
	if (x < 2) {
	    return false;
        } else if (x < 4) {
	    return true;
	}
	for (int i = 2; i <= Math.sqrt(x); i++) {
	    if (x % i == 0) {
		return false;
	    }
	}
	return true;
    }

    //Check if two numbers are coprime by checking their GCD
    public static boolean checkCoprime(int x, int y) {
	if (CryptFunc.gcd(x,y) == 1) {
	    return true;
	}
	return false;
    }

    //Generate a prime by generating a random number and seeing if it prime
    public static int genPrime(int lower, int upper) {
	count = (5 + upper - lower) * 2;
	if (lower < 0 || upper < 1 || lower > upper) {
	    return -1;
	}
	while (count > 0) {
	    prime = (int)Math.round(Math.random() * (upper - lower) + lower);
	    if (checkPrime(prime)) {
		return prime;
	    }
	    count--;
	}
	return -1;
    }

    //Generate a coprime by generating a random number and seeing if it coprime
    public static int genCoprime(int lower, int upper, int coprime) {
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
    
}
