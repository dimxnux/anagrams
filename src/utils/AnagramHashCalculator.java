package utils;

public class AnagramHashCalculator {

    private static final int LETTER_COUNT = 26;
    private static final int[] PRIMES = generatePrimes(LETTER_COUNT);

    private static int[] generatePrimes(int count) {
        int[] result = new int[count];
        int primeCounter = 0;
        int i = 2;
        while (primeCounter != count) {
            if (isPrime(i)) {
                result[primeCounter++] = i;
            }
            ++i;
        }
        return result;
    }

    private static boolean isPrime(int n) {
        for (int i = 2; i <= n / 2; ++i) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }

    private static int getPrimeByCharacter(char character) {
        return PRIMES[character - 'a'];
    }

    // uses prime numbers
    public static long calculateHashUsingPrimes(String word) {
        char[] chars = word.toCharArray();
        long hash = 1;

        for (char c : chars) {
            hash *= getPrimeByCharacter(c);
        }

        return hash;
    }

    public static long calculateHashUsingStringHashcode(String word) {
        char[] chars = new char[26];

        // 'a' = 97
        // 'z' = 122

        for (char c : word.toCharArray()) {
            // possible index range [0, 25]
            ++chars[c - 'a'];
        }

        return String.valueOf(chars).hashCode();
    }

    public static void main(String[] args) {
    }


}
