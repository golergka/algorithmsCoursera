
public class Percolation {

    private boolean [][] open;

    private final int size;

    private WeightedQuickUnionUF unionFind;

    private boolean[] full;
    private boolean[] grounded;

    private boolean percolates = false;

    public Percolation(int N) {
        size = N;
        open = new boolean[N][N];
        full = new boolean[N*N];
        grounded = new boolean[N*N];
        unionFind = new WeightedQuickUnionUF(N*N);
    }

    private int coordinatesToInt(int i, int j) {
        return (i-1)*size + (j-1);
    }

    private void unionIfOpen(int i1, int j1, int i2, int j2) {

        if (isOpen(i2, j2))
        {
            int c1 = unionFind.find(coordinatesToInt(i1, j1));
            int c2 = unionFind.find(coordinatesToInt(i2, j2));
            
            int r1 = unionFind.find(c1);
            int r2 = unionFind.find(c2);
            
            boolean thisFilled = full[r1] || full[r2];
            boolean thisGrounded = grounded[r1] || grounded[r2];

            unionFind.union(c1, c2);
            
            int rNew = unionFind.find(c1);

            if (thisFilled) full[rNew] = true;
            if (thisGrounded) grounded[rNew] = true;
            
        }
    }
    
    public void open(int i, int j) {

        open[i-1][j-1] = true;

        // Connecting to the top an bottom
        if (i == 1)
            fill(i, j);
        if (i == size)
            ground(i, j);

        if (i > 1)
            unionIfOpen(i, j, i-1, j);
        if (i < size)
            unionIfOpen(i, j, i+1, j);
        if (j > 1)
            unionIfOpen(i, j, i, j-1);
        if (j < size)
            unionIfOpen(i, j, i, j+1);

        if (isFull(i, j) && isGrounded(i, j))
            percolates = true;
    }

    public boolean isOpen(int i, int j) {
        return open[i-1][j-1];
    }

    public boolean isFull(int i, int j) {
        return isOpen(i, j) && full[unionFind.find(coordinatesToInt(i, j))];
    }

    private void fill(int i, int j) {
        full[unionFind.find(coordinatesToInt(i, j))] = true;
    }

    private boolean isGrounded(int i, int j) {
        return grounded[unionFind.find(coordinatesToInt(i, j))];
    }

    private void ground(int i, int j) {
        grounded[unionFind.find(coordinatesToInt(i, j))] = true;
    }

    public boolean percolates() {
        return percolates;
    }
}
