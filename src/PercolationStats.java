
public class PercolationStats {
	
	final int[] m_Experiments;
	final double[] m_Fractions;
	final int m_Size;
	
	// perform T independent computational experiments on an N-by-N grid
	public PercolationStats(int N, int T) {
		if (N <= 0 || T <= 0)
			throw new IllegalArgumentException();
		
		m_Size = N;
		
		m_Experiments = new int[T];
		m_Fractions = new double[T];
		
		for(int i = 0; i < T; i++)
		{
			m_Experiments[i] = experiment(N);
			m_Fractions[i] = m_Experiments[i] / (double) (m_Size * m_Size);
		}
	}
	
	int experiment(int N) {
		int result = 0;
		Percolation p = new Percolation(N);
		
		while(!p.percolates()) {
			int i = StdRandom.uniform(1, N+1);
			int j = StdRandom.uniform(1, N+1);
			if (!p.isOpen(i, j)) {
				result++;
				p.open(i, j);
			}
		}
		
		return result;
	}
	
	// sample mean of percolation threshold
	public double mean() {
		return StdStats.mean(m_Fractions);
	}
	
	// sample standard deviation of percolation threshold
	public double stddev() {
		return StdStats.stddev(m_Fractions);
	}
	
	// returns lower bound of the 95% confidence interval
	public double confidenceLo() {
		return mean() - 1.96 * stddev() / Math.sqrt(m_Experiments.length);
	}
	
	// returns upper bound of the 95% confidence interval
	public double confidenceHi() {
		return mean() + 1.96 * stddev() / Math.sqrt(m_Experiments.length);
	}
	
	public static void main(String[] args) {
		if (args.length < 2)
			StdOut.println("Should have 2 arguments!");
		
		int size = Integer.parseInt(args[0]);
		int experiments = Integer.parseInt(args[1]);
		
		PercolationStats p = new PercolationStats(size, experiments);
		StdOut.println("mean                    = " + p.mean());
		StdOut.println("stddev                  = " + p.stddev());
		StdOut.println("95% confidence interval = " + p.confidenceLo() + ", " + p.confidenceHi());
	}
}
