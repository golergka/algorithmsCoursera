
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

//        StdOut.println("unionIfOpen " + Integer.toString(i1) + " "
//        + Integer.toString(j1) + " " + Integer.toString(i2)
//        + " " + Integer.toString(j2));

        if (isOpen(i1, j1) && isOpen(i2, j2))
        {
//            StdOut.println("both open");
            
            boolean thisFilled = isFull(i1, j1) || isFull(i2, j2);
            boolean thisGrounded = isGrounded(i1, j1) || isGrounded(i2, j2);
            
//            StdOut.println("filled: " + Boolean.toString(thisFilled));
//            StdOut.println("grounded: " + Boolean.toString(thisGrounded));

            unionFind.union(
                    coordinatesToInt(i1, j1),
                    coordinatesToInt(i2, j2)
                    );
            
            if (unionFind.find(coordinatesToInt(i1, j1))
                    != unionFind.find(coordinatesToInt(i2, j2))) {
                throw new java.lang.IllegalStateException(
                        "Can't union ["+i1+","+j1+"] and ["+i2+","+j2+"]"
                        );
            }

            if (thisFilled) fill(i1, j1);
            if (thisGrounded) ground(i1, j1);

//            StdOut.println("filled1 " + Boolean.toString(isFull(i1, j1)));
//            StdOut.println("filled2 " + Boolean.toString(isFull(i2, j2)));
    }
    }

    public void open(int i, int j) {

//        StdOut.println("open " + Integer.toString(i) + " " + Integer.toString(j));

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
