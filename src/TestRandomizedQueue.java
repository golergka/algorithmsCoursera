import static org.junit.Assert.*;

import java.util.Iterator;
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
    
    void testDifferentIterators(int size) {
        StdOut.println("testDifferentIterators " + size);
        
        RandomizedQueue<Integer> r = new RandomizedQueue<Integer>();
        
        for(int i = 0; i < size; i++) {
            r.enqueue(StdRandom.uniform(size));
        }
        
        Iterator<Integer> iterator1 = r.iterator();
        Iterator<Integer> iterator2 = r.iterator();
        
        for(int i = 0; i < size; i++) {
            int n1 = iterator1.next();
            int n2 = iterator2.next();
            
            if (n1 != n2) return;
        }
        
        fail("Sequences should be different");
    }
    
    void testIteratorNextThrowsNoElement(int size) {
        StdOut.println("testIteratorNextThrowsNoElement " + size);
        
        RandomizedQueue<Integer> r = new RandomizedQueue<Integer>();
        
        for(int i = 0; i < size; i++) {
            r.enqueue(StdRandom.uniform(size));
        }
        
        Iterator<Integer> iterator = r.iterator();
        
        for(int i = 0; i < size; i++)
            iterator.next();
        
        try
        {
            iterator.next();
            fail("Should've thrown exception!");
        } catch (NoSuchElementException e) { }
    }

    public static void main(String[] args) {
        StdOut.println("Starting RandomizedQueue tests...");
        
        TestRandomizedQueue tester = new TestRandomizedQueue();
        
        tester.testEmpty();
        
        for(int i = 1; i <= 1024; i *= 2) {
            tester.testEmptyAfterUse(i);
            tester.testIterator(i);
            tester.testSize(i);
            tester.testIteratorNextThrowsNoElement(i);
            
            if (i > 4)
                tester.testDifferentIterators(i);
        }
        
        StdOut.println("tests successfull");
    }
    
}