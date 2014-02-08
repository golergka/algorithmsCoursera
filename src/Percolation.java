
public class Percolation {

    private boolean [][] open;

    private final int size;

    private WeightedQuickUnionUF unionFind;

    private boolean[] full;
    private boolean[] grounded;

    private boolean percolates = false;

    public Percolation(int N) {
        size = N;
        open = new boolean[N+1][N+1];
        full = new boolean[N*N];
        grounded = new boolean[N*N];
        unionFind = new WeightedQuickUnionUF(N*N);
    }

    private int coordinatesToInt(int i, int j) {
        return (i-1)*size + (j-1);
    }

    private void unionIfOpen(int i1, int j1, int i2, int j2) {

        if (open[i1][j1] && open[i2][j2])
        {
            boolean thisFilled = isFull(i1, j2) || isFull(i2, j2);
            boolean thisGrounded = isGrounded(i1, j2) || isGrounded(i2, j2);

            unionFind.union(
                    coordinatesToInt(i1, j1),
                    coordinatesToInt(i2, j2)
                    );

            if (thisFilled) fill(i1, j1);
            if (thisGrounded) ground(i2, j2);
        }
    }

    private boolean validCoordinate(int i) {
        return !(i < 1 || i > size);
    }

    private void checkCoordinate(int i) {
        if (!validCoordinate(i))
            throw new java.lang.IndexOutOfBoundsException(
                    "Coordinate outside of range: " + i + " [1;" + size + "]"
                    );
    }

    public void open(int i, int j) {
        checkCoordinate(i);
        checkCoordinate(j);

        open[i][j] = true;

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
        return open[i][j];
    }

    public boolean isFull(int i, int j) {
        return full[unionFind.find(coordinatesToInt(i, j))];
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
