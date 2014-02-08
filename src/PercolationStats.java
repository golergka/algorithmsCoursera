public class PercolationStats {

    private final int[] experiments;
    private final double[] fractions;
    private final int size;

    // perform T independent computational experiments on an N-by-N grid
    public PercolationStats(int N, int T) {
        if (N <= 0 || T <= 0)
            throw new IllegalArgumentException();

        size = N;

        experiments = new int[T];
        fractions = new double[T];

        for (int i = 0; i < T; i++) {
            experiments[i] = experiment(N);
            fractions[i] = experiments[i] / (double) (size * size);
        }
    }

    private int experiment(int N) {
        int result = 0;
        Percolation p = new Percolation(N);

        while (!p.percolates()) {
            int i = StdRandom.uniform(1, N + 1);
            int j = StdRandom.uniform(1, N + 1);
            if (!p.isOpen(i, j)) {
                result++;
                p.open(i, j);
            }
        }

        return result;
    }

    // sample mean of percolation threshold
    public double mean() {
        return StdStats.mean(fractions);
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return StdStats.stddev(fractions);
    }

    // returns lower bound of the 95% confidence interval
    public double confidenceLo() {
        return mean() - 1.96 * stddev() / Math.sqrt(experiments.length);
    }

    // returns upper bound of the 95% confidence interval
    public double confidenceHi() {
        return mean() + 1.96 * stddev() / Math.sqrt(experiments.length);
    }

    public static void main(String[] args) {
        if (args.length < 2)
            StdOut.println("Should have 2 arguments!");

        int size = Integer.parseInt(args[0]);
        int experiments = Integer.parseInt(args[1]);

        PercolationStats p = new PercolationStats(size, experiments);
        StdOut.println("mean                    = " + p.mean());
        StdOut.println("stddev                  = " + p.stddev());
        StdOut.println("95% confidence interval = " + p.confidenceLo() + ", "
                + p.confidenceHi());
    }
}
