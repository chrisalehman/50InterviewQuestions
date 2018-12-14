public class _49_NthFibonacci {
    /**
     * Find the nth number in the Fibonacci sequence.
     *
     *     fib => 0, 1, 1, 2, 3, 5, 8, 13, 21, 34, 55
     *     idx => 0, 1, 2, 3, 4, 5, 6, 7,  8,  9,  10
     *
     * NOTE: Counting starts at 0th index.
     *
     * Solution:
     *  1. Recursion: trivial
     *  2. Use a 'cache' array to simulate recursion.
     *
     */
    public static void main(String[] args) {
        System.out.println("Fibonacci, recursion: " + fibonacci1(10));
        System.out.println("Fibonacci, loop: " + fibonacci2(10));
    }

    // recursion
    private static int fibonacci1(int n) {
        if (n < 0)
            return -1;
        if (n < 2)
            return n;
        return fibonacci1(n-2) + fibonacci1(n-1);
    }

    // loop
    private static int fibonacci2(int n) {
        if (n < 0)
            return -1;
        if (n < 2)
            return n;

        int[] cache = new int[n + 1];

        for (int i = 0; i < cache.length; i++) {
            cache[i] = (i < 2) ? i : cache[i - 2] + cache[i - 1];
        }

        return cache[n];
    }
}
