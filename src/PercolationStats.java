public class PercolationStats {

    private final double mean;
    private final double stddev;
    private final double confidenceLo;
    private final double confidenceHi;

    // perform T independent computational experiments on an N-by-N grid
    public PercolationStats(int N, int T) {
        if (N <= 0 || T <= 0)
            throw new IllegalArgumentException();
        
        double[] fractions = new double[T];

        for (int i = 0; i < T; i++) {
            fractions[i] = experiment(N) / (double) (N * N);
        }
        
        mean = StdStats.mean(fractions);
        stddev = StdStats.stddev(fractions);
        confidenceLo = mean - 1.96 * stddev / Math.sqrt(fractions.length);
        confidenceHi = mean + 1.96 * stddev / Math.sqrt(fractions.length);
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
        return mean;
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return stddev;
    }

    // returns lower bound of the 95% confidence interval
    public double confidenceLo() {
        return confidenceLo;
    }

    // returns upper bound of the 95% confidence interval
    public double confidenceHi() {
        return confidenceHi;
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
