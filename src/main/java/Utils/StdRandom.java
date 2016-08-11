package Utils;

import java.util.Random;

/**
 * The {@code Utils.StdRandom} class provides static methods for generating
 * random number from various discrete and continuous distributions,
 * including Bernoulli, uniform, Gaussian, exponential, pareto,
 * Poisson, and Cauchy. It also provides method for shuffling an array
 * or subarray.
 *
 * @author Pratham Tandon
 */

public final class StdRandom {

    private static Random random; // pseudo-random generator
    private static long seed;     // pseudo-random generator seed

    // static initializer
    static {
        seed = System.currentTimeMillis();
        random = new Random(seed);
    }

    private StdRandom() {}

    /**
     * Sets the seed of pseudo-random generator.
     * This method enables you to produce the same sequence of "random"
     * for each execution of the program.
     *
     * @param s the seed
     */
    public static void setSeed(long s) {
        seed = s;
        random = new Random(s);
    }

    /**
     * Returns the seed of pseudo-random number generator.
     *
     * @return the seed.
     */
    public static long getSeed() {
        return seed;
    }

    /**
     * Returns a random real number uniformly in [0,1).
     *
     * @return a random real number uniformly in [0,1).
     */
    public static double uniform() {
        return random.nextDouble();
    }

    /**
     * Returns a random integer uniformly in [0,n).
     *
     * @param n number of possible integers
     * @return a random integer uniformly between 0 (inclusive) and <tt>N</tt> (exclusive)
     * @throws IllegalArgumentException if <tt>n <= 0</tt>
     */
    public static int uniform(int n) {
        if (n <= 0) throw new IllegalArgumentException("Parameter N must be positive");
        return random.nextInt(n);
    }

    /**
     * Returns a random integer uniformly in [a,b).
     *
     * @param a the left endpoint
     * @param b the right endpoint
     * @return a random integer uniformly in [a,b).
     * @throws IllegalArgumentException if <tt>b <= a</tt>
     * @throws IllegalArgumentException if <tt>b - a >= Integer.MAX_VALUE</tt>
     */
    public static int uniform(int a, int b) {
        if (b <= a) throw new IllegalArgumentException("Invalid range");
        if ((long) b - a >= Integer.MAX_VALUE) throw new IllegalArgumentException("Invalid range");
        return a + uniform(b - a);
    }

    /**
     * Returns a random real number uniformly in [a,b)
     *
     * @param a the left endpoint
     * @param b the right endpoint
     * @return a random real number uniformly in [a,b)
     * @throws IllegalArgumentException unless <tt>a < b</tt>
     */
    public static double uniform(double a, double b) {
        if (!(a < b)) throw new IllegalArgumentException("Invalid range");
        return a + (b - a) * uniform();
    }

    /**
     * Returns a random boolean from a Bernoulli distribution with success
     * probability <em>p</em>.
     *
     * @param p the probability of returning <tt>true</tt>.
     * @return <tt>true</tt> with probability <tt>p</tt>
     *         <tt>false</tt> with probability 1 - <tt>p</tt>
     * @throws IllegalArgumentException unless <tt>p >= 0.0</tt> and <tt>p <= 1.0</tt>
     */
    public static boolean bernoulli(double p) {
        if(!(p >= 0.0 && p <= 1.0)) throw new IllegalArgumentException("Probability must be between 0.0 and 1.0");
        return uniform() < p;
    }


    /**
     * Returns a random boolean from a Bernoulli distribution with success
     * probability 1/2.
     *
     * @return <tt>true</tt> with probability 1/2 and
     *          <tt>false</tt> with probability 1/2.
     */
    public static boolean bernoulli() {
        return bernoulli(0.5);
    }

    /**
     * Returns a random real number from a standard Gaussian distribution.
     *
     * @return a random real number from a standard Gaussian distribution
     *          (mean 0 and standard deviation 1)
     */
    public static double gaussian() {
        // use the polar form of Box-Muller transform.
        double r, x, y;
        do {
            x = uniform(-1.0, 1.0);
            y = uniform(-1.0, 1.0);
            r = x*x + y*y;
        } while (r >= 1 || r == 0);
        return x * Math.sqrt(-2 * Math.log(r) / r);
    }

    /**
     * Returns a random real number from a Gaussian distribution with mean &mu;
     * and standard deviation &sigma;
     *
     * @param mu the mean
     * @param sigma the standard deviation
     * @return a real number distributed according to the Gaussian distribution
     *          with mean <tt>mu</tt> and standard deviation <tt>sigma</tt>
     */
    public static double gaussian(double mu, double sigma) {
        return mu + sigma * gaussian();
    }

    /**
     * Returns a random integer from a geometric distribution with success
     * probability <em>p</em>
     *
     * @param p the parameter of the geometric distribution
     * @return a random integer from a geometric distribution with success
     *          probability <tt>p</tt>; or <tt>Integer.MAX_VALUE</tt> if
     *          <tt>p</tt> is (nearly) equal to <tt>1.0</tt>
     * @throws IllegalArgumentException unless <tt>p >= 0.0</tt> and <tt>p <= 1.0</tt>
     */
    public static int geometric(double p) {
        if(!(p >= 0.0 && p <= 1.0))
            throw new IllegalArgumentException("Probability must be between 0.0 and 1.0");
        return (int) Math.ceil(Math.log(uniform()) / Math.log(1.0 - p));
    }

    /**
     * Returns a random integer from a Poisson distribution with mean &lambda;
     *
     * @param lambda the mean of the Poisson distribution
     * @return a random integer from a Poisson distribution with mean <tt>lambda</tt>
     * @throws IllegalArgumentException unless <tt>lambda > 0.0</tt> and not infinite
     */
    public static int poisson(double lambda) {
        if(!(lambda > 0.0))
            throw new IllegalArgumentException("Parameter lambda must be positive");
        if(Double.isInfinite(lambda))
            throw new IllegalArgumentException("Parameter lambda must not be infinite");

        int k = 0;
        double p = 1.0;
        double expLambda = Math.exp(-lambda);
        do {
            k++;
            p *= uniform();
        } while(p >= expLambda);
        return k - 1;
    }

    /**
     * Returns a random real number from the Cauchy distribution.
     *
     * @return a random real number from the Cauchy distribution.
     */
    public static double cauchy() {
        return Math.tan(Math.PI * (uniform() - 0.5));
    }

    /**
     * Returns a random integer from specified discrete distribution.
     *
     * @param probabilities the probability of occurrence of each integer.
     * @return a random integer from a discrete distribution:
     *          <tt>i</tt> with probability <tt>probabilities[i]</tt>
     * @throws NullPointerException if <tt>probabilities</tt> is <tt>null</tt>.
     * @throws IllegalArgumentException if sum of array entries in not (very nearly) equal to <tt>1.0</tt>
     * @throws IllegalArgumentException unless <tt>probabilities[i] >= 0.0</tt> for each index <tt>i</tt>
     */
    public static int discrete(double[] probabilities) {
        if (probabilities == null) throw new NullPointerException("argument array is null");
        double EPSILON = 1E-14;
        double sum = 0.0;
        for (int i = 0; i < probabilities.length; i++) {
            if(!(probabilities[i] >= 0.0))
                throw new IllegalArgumentException("array entry " + i + " must be nonnegative: " + probabilities[i]);
            sum += probabilities[i];
        }

        if(!(Math.abs(sum - 1.0) <= EPSILON))
            throw new IllegalArgumentException("sum of array entries does not approximately equal 1.0: " + sum);

        // the for loop may not return a value when both r is (nearly) 1.0 and when the
        // cumulative sum is less than 1.0 (as a result of floating-point roundoff error)
        while(true) {
            double r = uniform();
            sum = 0.0;
            for (int i = 0; i < probabilities.length; i++) {
                sum += probabilities[i];
                if (sum > r) return i;
            }
        }
    }

    /**
     * Returns a random integer from the specified discrete distribution.
     *
     * @param frequencies the frequency of occurrence of each integer.
     * @return a random integer from a discrete distribution:
     *          <tt>i</tt> with probability proportional to <tt>frequencies[i]</tt>
     * @throws NullPointerException if <tt>frequencies</tt> is <tt>null</tt>
     * @throws IllegalArgumentException if all array entries are <tt>0</tt>
     * @throws IllegalArgumentException if <tt>frequencies[i]</tt> is negative for any index <tt>i</tt>
     * @throws IllegalArgumentException if sum of frequencies exceeds <tt>Integer.MAX_VALUE</tt> (2<sup>31</sup> - 1)
     */
    public static int discrete(int[] frequencies) {
        if(frequencies == null) throw new NullPointerException("argument array is null");
        long sum = 0;
        for (int i = 0; i < frequencies.length; i++) {
            if(frequencies[i] < 0)
                throw new IllegalArgumentException("array entry " + i + " must be nonnegative: " + frequencies[i]);
            sum += frequencies[i];
        }
        if(sum == 0)
            throw new IllegalArgumentException("at least one array entry must be positive");
        if(sum >= Integer.MAX_VALUE)
            throw new IllegalArgumentException("sum of frequencies overflows int");

        // pick index i with probability proportional to frequencies[i]
        double r = uniform((int) sum);
        sum = 0;
        for (int i = 0; i < frequencies.length; i++) {
            sum += frequencies[i];
            if(sum > r) return i;
        }

        // can't reach here
        assert false;
        return -1;
    }

    /**
     * Returns a random real number from an exponential distribution
     * with rate &lambda;
     *
     * @param lambda the rate of the exponential distribution
     * @return a random real number from an exponential distribution with
     *          rate <tt>lambda</tt>
     * @throws IllegalArgumentException unless <tt>lambda > 0.0</tt>
     */
    public static double exp(double lambda) {
        if(!(lambda > 0.0))
            throw new IllegalArgumentException("Rate lambda must be positive");
        return -Math.log(1 - uniform()) / lambda;
    }

    /**
     * Rearranges the elements of the specified array in uniformly random order.
     *
     * @param a the array to shuffle
     * @throws NullPointerException if <tt>a</tt> is <tt>null</tt>
     */
    public static void shuffle(Object[] a) {
        if(a == null) throw new NullPointerException("argument array is null");
        int n = a.length;
        for (int i = 0; i < n; i++) {
            int r = uniform(i, n);
            Object temp = a[i];
            a[i] = a[r];
            a[r] = temp;
        }
    }

    /**
     * Rearranges the elements of the specified subarray in uniformly random order.
     *
     * @param a the array to shuffle
     * @param lo the left endpoint (inclusive)
     * @param hi the right endpoint (inclusive)
     * @throws NullPointerException if <tt>a</tt> is <tt>null</tt>
     * @throws IndexOutOfBoundsException unless <tt>(0 <= lo) && (lo <= hi) && (hi < a.length)</tt>
     */
    public static void shuffle(Object[] a, int lo, int hi) {
        if(a == null) throw new NullPointerException("argument array is null");
        if(lo < 0 || lo > hi || hi >= a.length)
            throw new IndexOutOfBoundsException("Illegal subarray range");
        for (int i = lo; i < hi; i++) {
            int r = uniform(i, hi);
            Object temp = a[i];
            a[i] = a[r];
            a[r] = temp;
        }
    }

    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        if (args.length == 2) StdRandom.setSeed(Long.parseLong(args[1]));
        double[] probabilities = {0.5, 0.3, 0.1, 0.1};
        int[] frequencies = {5, 3, 1, 1};
        String[] a = "A B C D E F G".split(" ");

        System.out.println("seed = " + StdRandom.getSeed());
        for (int i = 0; i < n; i++) {
            System.out.print(String.format("%2d ", uniform(100)));
            System.out.print(String.format("%8.5f ", uniform(10.0, 99.0)));
            System.out.print(String.format("%5b ", bernoulli(0.6)));
            System.out.print(String.format("%7.5f ", gaussian(9.0, 0.2)));
            System.out.print(String.format("%1d ", discrete(probabilities)));
            System.out.print(String.format("%1d ", discrete(frequencies)));
            StdRandom.shuffle(a);
            for(String s: a)
                System.out.print(s);
            System.out.println();
        }
    }
}
