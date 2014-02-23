import static org.junit.Assert.*;

public class TestPoint {
    
    public void testCompareTo(int limit) {
        StdOut.println("testCompareTo " + limit);
        
        int x1 = StdRandom.uniform(-limit, limit);
        int y1 = StdRandom.uniform(-limit, limit);
        Point p1 = new Point(x1, y1);
        
        int x2 = x1 + StdRandom.uniform(limit);
        int y2 = y1 + StdRandom.uniform(limit);
        
        Point p2 = new Point(x2, y2);
        Point p3 = new Point(x1, y2);
        Point p4 = new Point(x2, y1);
        
        assertTrue(p1.compareTo(p2) <= 0);
        assertTrue(p1.compareTo(p3) <= 0);
        assertTrue(p1.compareTo(p4) <= 0);
        
        Point pp2 = new Point(x2 + 1, y2 + 1);
        Point pp3 = new Point(x1 + 1, y2 + 1);
        Point pp4 = new Point(x2 + 1, y1 + 1);
        
        assertTrue(p1.compareTo(pp2) < 0);
        assertTrue(p1.compareTo(pp3) < 0);
        assertTrue(p1.compareTo(pp4) < 0);
    }
    
    public void testCompareToSymmetry(int limit) {
        StdOut.println("testCompareToSymmetry " + limit);
        
        Point p1 = new Point(
                StdRandom.uniform(-limit, limit),
                StdRandom.uniform(-limit, limit));
        
        Point p2 = new Point(
                StdRandom.uniform(-limit, limit),
                StdRandom.uniform(-limit, limit));
        
        assertTrue(p1.compareTo(p2) == - p2.compareTo(p1));
    }
    
    public void testCompareToSelf(int limit) {
        StdOut.println("testCompareToSelf " + limit);
        
        Point p = new Point(
                StdRandom.uniform(-limit, limit),
                StdRandom.uniform(-limit, limit));
        
        assertEquals(p.compareTo(p), 0);
    }
    
    public void testSlopeToHorizontal(int limit) {
        StdOut.println("testSlopeToHorizontal " + limit);
        
        int y = StdRandom.uniform(-limit, limit);
        
        Point p1 = new Point(StdRandom.uniform(-limit, limit), y);
        Point p2;
        do { 
            p2 = new Point(StdRandom.uniform(-limit, limit), y);
        } while (p1.compareTo(p2) == 0);
        
        assertEquals("p1 " + p1.toString() + "p2 " + p2.toString(),
                p1.slopeTo(p2), 0d, 0d);
    }
    
    public void testSlopeToVertical(int limit) {
        StdOut.println("testSlopeToVertical " + limit);
        
        int x = StdRandom.uniform(-limit, limit);
        
        Point p1 = new Point(x, StdRandom.uniform(-limit, limit));
        Point p2;
        do { 
            p2 = new Point(x, StdRandom.uniform(-limit, limit));
        } while (p1.compareTo(p2) == 0);
        
        assertEquals("p1 " + p1.toString() + "p2 " + p2.toString(),
                p1.slopeTo(p2), Double.POSITIVE_INFINITY, 0d);
    }
    
    public void testSlopeToSelf(int limit) {
        StdOut.println("testSlopeToSelf " + limit);
        
        Point p = new Point(
                StdRandom.uniform(-limit, limit),
                StdRandom.uniform(-limit, limit));
        
        assertEquals("p: " + p, p.slopeTo(p), Double.NEGATIVE_INFINITY, 0d);
    }
    
    public void testSlopeOrder(int limit) {
        StdOut.println("testSlopeOrder " + limit);
        
        Point p1 = new Point(
                StdRandom.uniform(-limit, limit),
                StdRandom.uniform(-limit, limit));
        
        Point p2 = new Point(
                StdRandom.uniform(-limit, limit),
                StdRandom.uniform(-limit, limit));
        
        Point p3 = new Point(
                StdRandom.uniform(-limit, limit),
                StdRandom.uniform(-limit, limit));
        
        if (p1.slopeTo(p2) > p1.slopeTo(p3))
            assertTrue(p1.SLOPE_ORDER.compare(p2, p3) > 0);
        else if (p1.slopeTo(p2) == p1.slopeTo(p3))
            assertTrue(p1.SLOPE_ORDER.compare(p2, p3) == 0);
        else if (p1.slopeTo(p2) < p1.slopeTo(p3))
            assertTrue(p1.SLOPE_ORDER.compare(p2, p3) < 0);
    }
    
    public static void main(String[] args) {
        StdOut.println("Starting tests for Point...");
        
        TestPoint tester = new TestPoint();
        
        for(int i = 2; i <= 1024; i *= 2) {
            tester.testCompareTo(i);
            tester.testCompareToSymmetry(i);
            tester.testSlopeToHorizontal(i);
            tester.testSlopeToVertical(i);
            tester.testSlopeToSelf(i);
            tester.testSlopeOrder(i);
        }
        
        StdOut.println("Tests complete.");
    }

}
