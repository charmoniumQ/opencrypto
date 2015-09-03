//Author: Nicklaus McClendon (kulinacs@gmail.com)
//Last Updated: 3 September 2015
//https://github.com/KULinacs

package opencrypto;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

public class Prime {

    private static ArrayList<Integer> factorList = new ArrayList<Integer>();

    private static int count, prime;
    private static int[] factorArray;
    
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
    public static int getPrime(int lower, int upper) {
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
    public static int getCoprime(int lower, int upper, int coprime) {
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

    //Return the list of prime factors of a number in a collection
    public static int[] getFactors(int n) {
	factorList.clear();
	for (int i = 2; i <= n; i = i + 2) {
	    if (i == 4) {
		i = 3;
	    }
	    while (n % i == 0) {
		factorList.add(i);
		n /= i;
	    }
	}
	factorArray = new int[factorList.size()];
	for (int i = 0; i < factorList.size(); i++) {
	    factorArray[i] = factorList.get(i);
	}
	return factorArray;
    }
    
}
