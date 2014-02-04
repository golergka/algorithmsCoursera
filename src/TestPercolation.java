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
	
	public static void main(String[] args) {
		
		StdOut.println("Starting tests... ");
		
		TestPercolation tester = new TestPercolation();
		
		tester.testBounds(1);
		tester.testBounds(5);
		tester.testBounds(4096);
		
		tester.testOpen(2, 1);
		tester.testOpen(512, 20);
		tester.testOpen(512, 4000);
		
		StdOut.println("Tests complete.");
	}

}
