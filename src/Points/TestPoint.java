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
    
    @SuppressWarnings("deprecation")
    public void testSlopeToHorizontal(int limit) {
        StdOut.println("testSlopeToHorizontal " + limit);
        
        int y = StdRandom.uniform(-limit, limit);
        
        Point p1 = new Point(StdRandom.uniform(-limit, limit), y);
        Point p2 = new Point(StdRandom.uniform(-limit, limit), y);
        
        assertEquals(p1.slopeTo(p2), 0d);
    }
    
    @SuppressWarnings("deprecation")
    public void testSlopeToVertical(int limit) {
        StdOut.println("testSlopeToVertical " + limit);
        
        int x = StdRandom.uniform(-limit, limit);
        
        Point p1 = new Point(x, StdRandom.uniform(-limit, limit));
        Point p2 = new Point(x, StdRandom.uniform(-limit, limit));
        
        assertEquals(p1.slopeTo(p2), Double.POSITIVE_INFINITY);
    }
    
    @SuppressWarnings("deprecation")
    public void testSlopeToSelf(int limit) {
        StdOut.println("testSlopeToSelf " + limit);
        
        Point p = new Point(
                StdRandom.uniform(-limit, limit),
                StdRandom.uniform(-limit, limit));
        
        assertEquals(p.slopeTo(p), Double.NEGATIVE_INFINITY);
    }
    
    public static void main(String[] args) {
        StdOut.println("Starting tests for Point...");
        
        TestPoint tester = new TestPoint();
        
        for(int i = 1; i <= 1024; i *= 2) {
            tester.testCompareTo(i);
            tester.testCompareToSymmetry(i);
            tester.testSlopeToHorizontal(i);
            tester.testSlopeToVertical(i);
            tester.testSlopeToSelf(i);
        }
        
        StdOut.println("Tests complete.");
    }

}
