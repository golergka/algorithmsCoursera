
public class Percolation {
	
	boolean [][] openTable;
	
	final int size;
	
	public Percolation(int N) {
		size = N;
		openTable = new boolean[N][N];
	}
	
	void checkCoordinate(int i) {
		if (i < 1 || i > size)
			throw new java.lang.IndexOutOfBoundsException("Coordinate outside of range: " + i + " [1;" + size + "]");
	}
	
	public void open(int i, int j) {
		checkCoordinate(i);
		checkCoordinate(j);
		
		openTable[i-1][j-1] = true;
	}
	
	public boolean isOpen(int i, int j) {
		return openTable[i-1][j-1];
	}
	
	public boolean isFull(int i, int j) {
		return false;
	}
	
	public boolean percolates() {
		return false;
	}
}
