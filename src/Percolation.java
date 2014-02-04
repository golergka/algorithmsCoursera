
public class Percolation {
	
	boolean [][] openTable;
	
	final int size;
	
	IUnionFind unionFind;
	
	boolean percolates = false;
	
	public Percolation(int N) {
		size = N;
		openTable = new boolean[N][N];
		unionFind = new QuickUnion(N*N + 1);
	}
	
	void unionIfOpen(int i1, int j1, int i2, int j2) {
		
		if (openTable[i1-1][j1-1] && openTable[i2-1][j2-1])
			unionFind.union((i1-1)*size + (j1-1), (i2-1)*size + (j2-1));
	}
	
	boolean validCoordinate(int i) {
		return !(i < 1 || i > size);
	}
	
	void checkCoordinate(int i) {
		if (!validCoordinate(i))
			throw new java.lang.IndexOutOfBoundsException("Coordinate outside of range: " + i + " [1;" + size + "]");
	}
	
	public void open(int i, int j) {
		checkCoordinate(i);
		checkCoordinate(j);
		
		openTable[i-1][j-1] = true;
		
		// Connecting to the top layer
		if (i == 1)
			unionFind.union((i-1)*size+(j-1), size*size);
		
		if (i > 1)
			unionIfOpen(i, j, i-1, j);
		if (i < size)
			unionIfOpen(i, j, i+1, j);
		if (j > 1)
			unionIfOpen(i, j, i, j-1);
		if (j < size)
			unionIfOpen(i, j, i, j+1);
		
		if (i == size && isFull(i,j))
			percolates = true;
		
//		print();
	}
	
	void print() {
		
		for(int i = 0; i < size; i++)
		{
			for(int j = 0; j < size; j++)
				if (openTable[i][j])
					if (isFull(i+1, j+1))
						StdOut.print("X");
					else
						StdOut.print("O");
				else
					StdOut.print("_");
			StdOut.print("\n");
		}
		
	}
	
	public boolean isOpen(int i, int j) {
		return openTable[i-1][j-1];
	}
	
	public boolean isFull(int i, int j) {
		return unionFind.connected((i-1)*size + (j-1), size*size);
	}
	
	public boolean percolates() {
		return percolates;
	}
}
