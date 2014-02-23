import java.util.Iterator;
import java.util.NoSuchElementException;


public class RandomizedQueue<Item> implements Iterable<Item> {
    
    private Item[] contents;
    private int N = 0;
    
    public RandomizedQueue() {
        @SuppressWarnings("unchecked")
        Item[] items = (Item[]) new Object[1];
        contents = items;
    }
    
    public boolean isEmpty() {
        return N == 0;
    }
    
    public int size() {
        return N;
    }
    
    public void enqueue(Item item) {
        if (item == null) {
            throw new NullPointerException();
        }
        contents[N++] = item;
        if (N == contents.length) {
            @SuppressWarnings("unchecked")
            Item[] newContents = (Item[]) new Object[N*2];
            for (int i = 0; i < contents.length; i++) {
                newContents[i] = contents[i];
            }
            contents = newContents;
        }
    }
    
    public Item dequeue() {
        if (N == 0) {
            throw new NoSuchElementException();
        }
        
        int randomIndex = StdRandom.uniform(N);
        Item result = contents[randomIndex];
        contents[randomIndex] = contents[N-1];
        N--;
        if (N < contents.length/4) {
            @SuppressWarnings("unchecked")
            Item[] newContents = (Item[]) new Object[contents.length/2];
            for (int i = 0; i < contents.length/2; i++) {
                newContents[i] = contents[i];
            }
            contents = newContents;
        }
        return result;
    }
    
    public Item sample() {
        if (N == 0) {
            throw new NoSuchElementException();
        }
        
        int randomIndex = StdRandom.uniform(N);
        Item result = contents[randomIndex];
        return result;
    }
    
    private class RandomizedQueueIterator implements Iterator<Item> {
        private int current = 0;
        private Item[] contents;
        
        @SuppressWarnings("unchecked")
        RandomizedQueueIterator(RandomizedQueue<Item> my) {
            contents = (Item[]) new Object[my.N];
            
            for(int i = 0; i < my.N; i++) {
                contents[i] = my.contents[i];
            }
            
            for (int i = contents.length; i > 0; i--) {
                int randomIndex = StdRandom.uniform(i);
                Item temp = contents[randomIndex];
                contents[randomIndex] = contents[i-1];
                contents[i-1] = temp;
            }
        }
        @Override
        public boolean hasNext() {
            return current < contents.length;
        }
        @Override
        public Item next() {
            return contents[current++];
        }
        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    @Override
    public Iterator<Item> iterator() {
        return new RandomizedQueueIterator(this);
    }
    
    public static void main(String[] args) {
        
    }

}
