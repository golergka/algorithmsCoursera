
public class QuickUnion implements IUnionFind {
	
	protected int[] parents;
	
	public QuickUnion(int size)
	{
		parents = new int[size];
		
		for(int i = 0; i < size; i++)
			parents[i] = i;
	}
	
	int root(int p)
	{
		for(int loopCounter = 0; loopCounter < parents.length; loopCounter++)
		{
			if(parents[p] == p)
				return p;
			else
				p = parents[p];
		}
		
		throw new IllegalStateException("Infinite loop detected!");
	}

	@Override
	public boolean areConnected(int p, int q) {
		return root(p) == root(q);
	}

	@Override
	public void union(int p, int q) {
		if (areConnected(p, q))
			return;
		parents[root(q)] = p;
	}

}
