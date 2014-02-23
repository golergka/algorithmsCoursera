
import static org.junit.Assert.*;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class TestDeque {
    
    void testStraightPass(int size) {
        StdOut.println("testStraightPass " + Integer.toString(size));
        
        Deque<Integer> d = new Deque<Integer>();
        
        for(int i = 0; i < size; i++) {
            d.addFirst(i);
        }
        
        for(int i = 0; i < size; i++) {
            int r = d.removeLast();
            assertTrue("fail at " + Integer.toString(i) + " it is " + r, r == i);
        }
        
    }
    
    void testReversePass(int size) {
        StdOut.println("testReversePass " + Integer.toString(size));
        
        Deque<Integer> d = new Deque<Integer>();
        
        for(int i = 0; i < size; i++) {
            d.addFirst(i);
        }
        
        for(int i = size - 1; i >= 0; i--) {
            int r = d.removeFirst();
            assertTrue("fail at " + Integer.toString(i) + " it is " + r,
                    r == i);
        }
    }
    
    void assertEmpty(Deque<?> d) {
        
        assertTrue(d.isEmpty());
        
        try {
            d.removeFirst();
            fail("Should've thrown exception");
        } catch (NoSuchElementException ex) {
        }
        
        try {
            d.removeLast();
            fail("Should've thrown exception");
        } catch (NoSuchElementException ex) {
        }
    }
    
    void testEmpty() {
        StdOut.println("testEmpty");
        
        Deque<Integer> d = new Deque<Integer>();
        assertEmpty(d);
        assertFalse(d.iterator().hasNext());
        
    }
    
    void testEmptyAfterUse(int size) {
        StdOut.println("testEmptyAfterUse " + size);
        
        Deque<Integer> d = new Deque<Integer>();
        
        for(int i = 0; i < size; i ++) {
            d.addFirst(StdRandom.uniform(size));
        }
        
        for(int i = 0; i < size; i++) {
            d.removeFirst();
        }
        
        assertEmpty(d);
    }
    
    void testSize(int size) {
        StdOut.println("testSize " + size);
        
        Deque<Integer> d = new Deque<Integer>();
        
        for(int i = 0; i < size; i++) {
            d.addFirst(StdRandom.uniform(size));
        }
        
        assertEquals(size, d.size());
    }
    
    void testIterator(int size) {
        StdOut.println("testIterator " + size);
        
        Deque<Integer> d = new Deque<Integer>();
        
        for(int i = 0; i < size; i++) {
            d.addFirst(StdRandom.uniform(size));
        }
        
        int s = 0;
        
        for(@SuppressWarnings("unused") int i : d) {
            s++;
        }
        
        assertEquals(s, size);
    }
    
    void testThrowsNull() {
        StdOut.println("testThrowsNull");
        
        Deque<Object> d = new Deque<Object>();
        
        try {
            d.addFirst(null);
            fail("Should've thrown exception!");
        } catch (NullPointerException e) { }
        
        try {
            d.addLast(null);
            fail("Should've thrown exception!");
        } catch (NullPointerException e) { }
    }
    
    void testIteratorNextThrowsNoElement(int size) {
        StdOut.println("testIteratorNextThrowsNoElement " + size);
        
        Deque<Integer> d = new Deque<Integer>();
        
        for(int i = 0; i < size; i++) {
            d.addFirst(StdRandom.uniform(size));
        }
        
        Iterator<Integer> iterator = d.iterator();
        
        for(int i = 0; i < size; i++)
            iterator.next();
        
        try
        {
            iterator.next();
            fail("Should've thrown exception!");
        } catch (NoSuchElementException e) { }
    }
    
    public static void main(String[] args) {
        StdOut.println("Starting Deque tests...");
        
        TestDeque tester = new TestDeque();
        
        tester.testEmpty();
        tester.testThrowsNull();
        
        for(int i = 1; i <= 1024; i *= 2) {
            tester.testStraightPass(i);
            tester.testReversePass(i);
            tester.testEmptyAfterUse(i);
            tester.testSize(i);
            tester.testIterator(i);
            tester.testIteratorNextThrowsNoElement(i);
        }
        
        StdOut.println("Deque tests complete.");
    }

}
