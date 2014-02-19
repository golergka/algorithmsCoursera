import static org.junit.Assert.*;

import java.util.NoSuchElementException;

public class TestRandomizedQueue {
    
    void assertEmpty(RandomizedQueue<?> r) {
       
        assertTrue(r.isEmpty());
        
        try {
            r.sample();
            fail("Shoud've thrown exception");
        } catch (NoSuchElementException ex) {
        }
        
        try {
            r.dequeue();
            fail("Should've thrown exception");
        } catch (NoSuchElementException ex) {
        }
    }
    
    void testEmpty() {
        StdOut.println("testEmpty");
        
        RandomizedQueue<Integer> r = new RandomizedQueue<Integer>();
        assertEmpty(r);
        
    }
    
    void testEmptyAfterUse(int size) {
        StdOut.println("testEmptyAfterUse " + size);
        
        RandomizedQueue<Integer> r = new RandomizedQueue<Integer>();
        
        for(int i = 0; i < size; i++) {
            r.enqueue(StdRandom.uniform(size));
        }
        
        for(int i = 0; i < size; i++) {
            r.dequeue();
        }
        
        assertEmpty(r);
    }
    
    void testSize(int size) {
        StdOut.println("testSize " + size);
        
        RandomizedQueue<Integer> r = new RandomizedQueue<Integer>();
        
        for(int i = 0; i < size; i++) {
            r.enqueue(StdRandom.uniform(size));
        }
        
        assertEquals(size, r.size());
    }
    
    void testIterator(int size) {
        StdOut.println("testIterator " + size);
        
        RandomizedQueue<Integer> r = new RandomizedQueue<Integer>();
        
        for(int i = 0; i < size; i++) {
            r.enqueue(StdRandom.uniform(size));
        }
        
        int s = 0;
        
        for(@SuppressWarnings("unused") int i : r) {
            s++;
        }
        
        assertEquals(s, size);
    }

    public static void main(String[] args) {
        StdOut.println("Starting RandomizedQueue tests...");
        
        TestRandomizedQueue tester = new TestRandomizedQueue();
        
        tester.testEmpty();
        
        for(int i = 1; i < 1024; i *= 2) {
            tester.testEmptyAfterUse(i);
            tester.testIterator(i);
            tester.testSize(i);
        }
    }
    
}