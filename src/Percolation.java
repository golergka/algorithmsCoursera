
public class Percolation {
	
	private boolean [][] m_Open;
	
	private final int m_Size;
	
	private WeightedQuickUnionUF m_UnionFind;
	
	private boolean[] m_Full;
	private boolean[] m_Grounded;
	
	private boolean m_Percolates = false;
	
	public Percolation(int N) {
		m_Size = N;
		m_Open = new boolean[N+1][N+1];
		m_Full = new boolean[N*N];
		m_Grounded = new boolean[N*N];
		m_UnionFind = new WeightedQuickUnionUF(N*N);
	}
	
	private int coordinatesToInt(int i, int j) {
		return (i-1)*m_Size + (j-1);
	}
	
	private void unionIfOpen(int i1, int j1, int i2, int j2) {
		
		if (m_Open[i1][j1] && m_Open[i2][j2])
		{
			boolean filled = isFull(i1,j2) || isFull(i2, j2);
			boolean grounded = isGrounded(i1,j2) || isGrounded(i2, j2);
			
			m_UnionFind.union(coordinatesToInt(i1,j1), coordinatesToInt(i2,j2));
			
			if (filled) fill(i1,j1);
			if (grounded) ground(i2, j2);
		}
	}
	
	private boolean validCoordinate(int i) {
		return !(i < 1 || i > m_Size);
	}
	
	private void checkCoordinate(int i) {
		if (!validCoordinate(i))
			throw new java.lang.IndexOutOfBoundsException("Coordinate outside of range: " + i + " [1;" + m_Size + "]");
	}
	
	public void open(int i, int j) {
		checkCoordinate(i);
		checkCoordinate(j);
		
		m_Open[i][j] = true;
		
		// Connecting to the top an bottom
		if (i == 1)
			fill(i,j);
		if (i == m_Size)
			ground(i,j);
		
		if (i > 1)
			unionIfOpen(i, j, i-1, j);
		if (i < m_Size)
			unionIfOpen(i, j, i+1, j);
		if (j > 1)
			unionIfOpen(i, j, i, j-1);
		if (j < m_Size)
			unionIfOpen(i, j, i, j+1);
		
		if (isFull(i,j) && isGrounded(i,j))
			m_Percolates = true;
	}
	
	public boolean isOpen(int i, int j) {
		return m_Open[i][j];
	}
	
	public boolean isFull(int i, int j) {
		return m_Full[m_UnionFind.find(coordinatesToInt(i,j))];
	}
	
	private void fill(int i, int j) {
		m_Full[m_UnionFind.find(coordinatesToInt(i,j))] = true;
	}
	
	private boolean isGrounded(int i, int j) {
		return m_Grounded[m_UnionFind.find(coordinatesToInt(i,j))];
	}
	
	private void ground(int i, int j) {
		m_Grounded[m_UnionFind.find(coordinatesToInt(i,j))] = true;
	}
	
	public boolean percolates() {
		return m_Percolates;
	}
}
