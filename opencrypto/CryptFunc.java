//Author: Nicklaus McClendon (kulinacs@gmail.com)
//Last Updated: 3 September 2015
//https://github.com/KULinacs

package opencrypto;

public class CryptFunc {

    public CryptFunc() {
    }

    //Find the GCD using the Euclidean Algorithm
    public static int gcd(int x, int y) {
	if (y == 0) {
	    return x;
	}
	return gcd(y, x % y);
    }
    
}
