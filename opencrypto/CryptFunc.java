//Author: Nicklaus McClendon (kulinacs@gmail.com)
//Last Updated: 3 September 2015
//https://github.com/KULinacs

package opencrypto;

import java.util.ArrayList;

public class CryptFunc {

    private static int phi;
    private static ArrayList<Integer> factorList = new ArrayList<Integer>();
    
    public CryptFunc() {
    }
    
    //Find the GCD using the Euclidean Algorithm
    public static int gcd(int x, int y) {
	if (y == 0) {
	    return x;
	}
	return gcd(y, x % y);
    }

    public static int totient(int x) {
	factorList = Prime.getFactorsAL(x);
	phi = x;
	for (int i = 0; i < factorList.size(); i++) {
	    while (factorList.lastIndexOf(factorList.get(i)) != i) {
		factorList.remove(factorList.lastIndexOf(factorList.get(i)));
	    }
	}
	for (int i = 0; i < factorList.size(); i++) {
	    phi *= 1 - (1.0 / factorList.get(i));
	}
	return phi;
    }
}
