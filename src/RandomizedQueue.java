import java.util.Iterator;
import java.util.NoSuchElementException;


public class RandomizedQueue<Item> implements Iterable<Item> {
    
    private Item[] contents;
    private int N = 0;
    
    @SuppressWarnings("unchecked")
    public RandomizedQueue() {
        contents = (Item[]) new Object[1];
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
            for(int i = 0; i < contents.length; i++) {
                newContents[i] = contents[i];
            }
            contents = newContents;
        }
    }
    
    public Item dequeue() {
        Item result = sample();
        N--;
        if (N < contents.length/4) {
            @SuppressWarnings("unchecked")
            Item[] newContents = (Item[]) new Object[contents.length/2];
            for(int i = 0; i < contents.length/2; i++) {
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
        contents[randomIndex] = contents[N-1];
        return result;
    }

    @Override
    public Iterator<Item> iterator() {
        // TODO Auto-generated method stub
        return null;
    }
    
    public static void main(String[] args) {
        
    }

}
