import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.Random;


public class TestPercolationStats {
	
	Random generator = new Random();
	
	public void testConstructorArgumentBounds() {
		StdOut.println("testConstructorArgumentBounds");
		try
		{
			new PercolationStats(0,0);
			fail("Should've thrown exception");
		}
		catch(Exception ex)
		{
			assertTrue("Should've thrown java.lang.IndexOutOfBoundsException!",
					ex instanceof java.lang.IllegalArgumentException);
		}
	}
	
	public static void main(String[] args) {
		StdOut.println("Starting tests... ");
		
		TestPercolationStats tester = new TestPercolationStats();
		tester.testConstructorArgumentBounds();
		
		StdOut.println("Tests complete.");
	}

}
