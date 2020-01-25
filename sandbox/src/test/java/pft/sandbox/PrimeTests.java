package pft.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

public class PrimeTests {

    @Test

    public void testPrimes () {

        Assert.assertTrue(Primes.isPrime(Integer.MAX_VALUE));
    }

    @Test(enabled = false)

    public void testPrimesLong () {
        long n = Integer.MAX_VALUE;
        Assert.assertTrue(Primes.isPrime(n));
    }

}
