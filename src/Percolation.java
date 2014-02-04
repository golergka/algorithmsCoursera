
public class Percolation {
	
	boolean [][] openTable;
	
	public Percolation(int N) {
		openTable = new boolean[N][N];
	}
	
	public void open(int i, int j) {
		openTable[i][j] = true;
	}
	
	public boolean isOpen(int i, int j) {
		return openTable[i][j];
	}
	
	public boolean isFull(int i, int j) {
		return false;
	}
	
	public boolean percolates() {
		return false;
	}
}
