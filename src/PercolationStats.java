
public class PercolationStats {
	
	int[] m_Experiments;
	
	// perform T independent computational experiments on an N-by-N grid
	public PercolationStats(int N, int T) {
		if (N <= 0 || T <= 0)
			throw new IllegalArgumentException();
		
		m_Experiments = new int[T];
		
		for(int i = 0; i < T; i++)
			m_Experiments[i] = experiment(N);
	}
	
	int experiment(int N) {
		int result = 0;
		Percolation p = new Percolation(N);
		
		while(!p.percolates()) {
			int i = StdRandom.uniform(N)+1;
			int j = StdRandom.uniform(N)+1;
			if (!p.isOpen(i, j)) {
				result++;
				p.open(i, j);
			}
		}
		
		return result;
	}
	
	// sample mean of percolation threshold
	public double mean() {
		return 0;
	}
	
	// sample standard deviation of percolation threshold
	public double stddev() {
		return 0;
	}
	
	// returns lower bound of the 95% confidence interval
	public double confidenceLo() {
		return 0;
	}
	
	// returns upper bound of the 95% confidence interval
	public double confidenceHi() {
		return 0;
	}
	
	public void main(String[] args) {
		
	}
}
