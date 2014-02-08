import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TestPercolation {

    Random generator = new Random();
    boolean visualize = false;

    void assertBounds(Percolation p, int x, int y) {
        try {
            p.open(x, y);
            fail("Should've thrown exception");
        } catch (Exception ex) {
            assertTrue("Should've thrown java.lang.IndexOutOfBoundsException!",
                    ex instanceof java.lang.IndexOutOfBoundsException);
        }
        
        try {
            p.isFull(x, y);
            fail("Should've thrown exception");
        } catch (Exception ex) {
            assertTrue("Should've thrown java.lang.IndexOutOfBoundsException!",
                    ex instanceof java.lang.IndexOutOfBoundsException);
        }
    }

    public void testBounds(int size) {
        StdOut.println("testBounds " + size);

        Percolation p = new Percolation(size);

        p.open(1, 1);
        p.open(size, size);

        assertBounds(p, size + 1, size);
        assertBounds(p, size + 1, size + 1);
        assertBounds(p, 0, 1);
        assertBounds(p, 0, 0);
        assertBounds(p, -size, size);
    }
    
    public void testBounds2() {
        StdOut.println("testBounds2");
        
        assertBounds(new Percolation(10), 0, 6);
        assertBounds(new Percolation(10), 12, 6);
        assertBounds(new Percolation(10), 11, 6);
        assertBounds(new Percolation(10), 6, 0);
        assertBounds(new Percolation(10), 6, 12);
        assertBounds(new Percolation(10), 6, 11);
        
    }

    public void testOpen(int size, int open) {
        StdOut.println("testOpen " + size);

        Percolation p = new Percolation(size);

        // It took all my willpower not to embark on writing generic tuple
        // class. But I was strong enough.
        List<Integer> xList = new ArrayList<Integer>();
        List<Integer> yList = new ArrayList<Integer>();

        // Creating pairs
        for (int i = 0; i < open; i++) {
            int x = generator.nextInt(size) + 1;
            int y = generator.nextInt(size) + 1;

            p.open(x, y);

            xList.add(x);
            yList.add(y);
        }

        // Checking that pairs
        for (int i = 0; i < open; i++) {
            assertEquals(p.isOpen(xList.get(i), yList.get(i)), true);
        }
    }

    public void testFullClosed(int size) {
        StdOut.println("testFullClosed " + size);

        Percolation p = new Percolation(size);

        for (int i = 0; i < size * size; i++)
            assertFalse(
                    "None should be full yet!",
                    p.isFull(generator.nextInt(size) + 1,
                            generator.nextInt(size) + 1));
    }

    public void testFullShafts(int size) {
        StdOut.println("testFullShafts " + size);

        Percolation p = new Percolation(size);

        for (int i = 0; i < size; i++) {
            int column = generator.nextInt(size) + 1;
            int depth = generator.nextInt(size) + 1;

            for (int j = 1; j <= depth; j++) {
                p.open(j, column);
                if (visualize)
                    PercolationVisualizer.draw(p, size);
            }

            assertTrue("Shaft should be dug and filled at [" + depth + ":"
                    + column + "]", p.isFull(depth, column));
        }
    }

    public void testPercolatesStraightColumn(int size) {
        StdOut.println("testPercolatesStraightColumn " + size);

        Percolation p = new Percolation(size);

        int column = generator.nextInt(size) + 1;

        for (int i = 1; i <= size; i++) {
            p.open(i, column);
            if (visualize)
                PercolationVisualizer.draw(p, size);
        }

        assertTrue("Should percolate!", p.percolates());
    }

    public void testPercolatesRandomColumn(int size) {
        StdOut.println("testPercolatesRandomColumn " + size);

        Percolation p = new Percolation(size);

        int column = generator.nextInt(size) + 1;

        int[] depths = new int[size];
        for (int i = 0; i < size; i++)
            depths[i] = i + 1;

        // Randomizing depths
        for (int i = size - 1; i > 0; i--) {
            for (int j = i; j > 0; j--) {
                int n = generator.nextInt(j);
                int e = depths[n];
                depths[n] = depths[j];
                depths[j] = e;
            }
        }

        for (int i = 0; i < size; i++) {
            p.open(depths[i], column);
            if (visualize)
                PercolationVisualizer.draw(p, size);
        }

        assertTrue("Should percolate!", p.percolates());
    }

    public static void main(String[] args) {

        StdOut.println("Starting tests... ");

        TestPercolation tester = new TestPercolation();

        tester.testBounds(1);
        tester.testBounds(5);
        tester.testBounds(15);
        
        tester.testBounds2();

        tester.testOpen(2, 1);
        tester.testOpen(15, 5);

        for (int n = 1; n <= 4096; n *= 2) {
            tester.testFullClosed(n);
            tester.testFullShafts(n);
            tester.testPercolatesStraightColumn(n);
            tester.testPercolatesRandomColumn(n);
        }

        StdOut.println("Tests complete.");
    }

}
