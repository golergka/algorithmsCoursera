import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TestPercolation {
	
	Random generator = new Random();
	
	void assertBounds(Percolation p, int x, int y)
	{
		try
		{
			p.open(x, y);
			fail("Should've thrown exception");
		}
		catch(Exception ex)
		{
			assertTrue("Should've thrown java.lang.IndexOutOfBoundsException!",
					ex instanceof java.lang.IndexOutOfBoundsException);
		}
	}
	
	public void testBounds(int size)
	{
		StdOut.println("testBounds " + size);
		
		Percolation p = new Percolation(size);
		
		p.open(1,1);
		p.open(size, size);
		
		assertBounds(p, size+1, size);
		assertBounds(p, size+1, size+1);
		assertBounds(p, 0, 1);
		assertBounds(p, 0, 0);
		assertBounds(p, -size, size);
	}

	public void testOpen(int size, int open) {
		StdOut.println("testOpen " + size);
		
		Percolation p = new Percolation(size);
		
		// It took all my willpower not to embark on writing generic tuple class. But I was strong enough.
		List<Integer> xList = new ArrayList<Integer>();
		List<Integer> yList = new ArrayList<Integer>();
		
		// Creating pairs
		for(int i = 0; i < open; i++)
		{
			int x = generator.nextInt(size) + 1;
			int y = generator.nextInt(size) + 1;
			
			p.open(x, y);
			
			xList.add(x);
			yList.add(y);
		}
		
		// Checking that pairs
		for(int i = 0; i < open; i++)
		{
			assertEquals(p.isOpen(xList.get(i), yList.get(i)), true);
		}
	}
	
	public void testFullClosed(int size) {
		StdOut.println("testFullClosed " + size);
		
		Percolation p = new Percolation(size);
		
		for (int i = 0; i < size*size; i++)
			assertFalse("None should be full yet!", p.isFull(generator.nextInt(size) + 1, generator.nextInt(size) + 1));
	}
	
	public void testFullShafts(int size) {
		StdOut.println("testFullShafts " + size);
		
		Percolation p = new Percolation(size);
		
		for (int i = 0; i < size; i++)
		{
			int column = generator.nextInt(size) + 1;
			int depth = generator.nextInt(size) + 1;
			
//			StdOut.println("Digging to ["+depth+":"+column+"]");
			
			for (int j = 1; j <= depth; j++)
			{
				p.open(j, column);
//				StdOut.println("Dug to" + j);
			}
			
//			assertTrue("Shaft should be dug and filled at [" + depth + ":" + column + "]", p.isFull(depth, column));
		}
	}
	
	public void testPercolatesStraightColumn(int size) {
		StdOut.println("testPercolates " + size);
		
		Percolation p  = new Percolation(size);
		
		int column = generator.nextInt(size) + 1;
		
		for(int i = 1; i <= size; i++)
			p.open(i, column);
		
		assertTrue("Should percolate!",p.percolates());
	}
	
	public static void main(String[] args) {
		
		StdOut.println("Starting tests... ");
		
		TestPercolation tester = new TestPercolation();
		
		tester.testBounds(1);
		tester.testBounds(5);
		tester.testBounds(15);
//		tester.testBounds(512);
		
		tester.testOpen(2, 1);
		tester.testOpen(15, 5);
//		tester.testOpen(512, 20);
//		tester.testOpen(512, 4000);
		
		tester.testFullClosed(1);
		tester.testFullClosed(5);
		tester.testFullClosed(20);
		
		tester.testFullShafts(1);
		tester.testFullShafts(5);
		tester.testFullShafts(15);
//		tester.testFullShafts(128);
//		tester.testFullShafts(256);
		
		int n = 1;
		
		while (n < 5)
		{
			tester.testPercolatesStraightColumn(n);
			n *= 2;
		}
		
//		StdOut.println("Tests complete.");
	}

}
