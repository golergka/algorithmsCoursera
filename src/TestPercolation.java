import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TestPercolation {
	
	Random generator = new Random();

	public void TestOpen(int size, int open) {
		
		Percolation p = new Percolation(size);
		
		// It took all my willpower not to embark on writing generic tuple class. But I was strong enough.
		List<Integer> xList = new ArrayList<Integer>();
		List<Integer> yList = new ArrayList<Integer>();
		
		// Creating pairs
		for(int i = 0; i < open; i++)
		{
			int x = generator.nextInt(size);
			int y = generator.nextInt(size);
			
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
		
		try
		{
			tester.TestOpen(2, 1);
			tester.TestOpen(512, 20);
			tester.TestOpen(512, 4000);
		}
		catch(Exception ex)
		{
			StdOut.println(ex.toString());
		}
		
		StdOut.println("Tests complete.");
	}

}
