
public class QuickUnionWeighted extends QuickUnion {
	
	int[] weights;

	public QuickUnionWeighted(int size) {
		super(size);
		
		weights = new int[size];
		for(int i = 0; i < size; i++) {
			weights[i] = 1;
		}
	}
	
	public void union(int p, int q) {
		
		if (weights[q] < weights[p])
		{
			parents[root(q)] = root(p);
			weights[root(p)] += weights[root(q)]; 
		}
		else
		{
			parents[root(p)] = root(q);
			weights[root(q)] += weights[root(p)];
		}
	}

}
