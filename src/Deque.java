import java.util.Iterator;
import java.util.NoSuchElementException;


public class Deque<Item> implements Iterable<Item> {
    
    private class Node {
        Item content;
        Node previous;
        Node next;
        
        Node(Item content, Node previous, Node next) {
            this.content = content;
            this.previous = previous;
            this.next = next;
        }
    }
    
    private Node first;
    private Node last;
    
    private int size = 0;
    
    public Deque() {
    }
    
    public boolean isEmpty() {
        return size == 0;
    }
    
    public int size() {
        return size;
    }
    
    public void addFirst(Item item) {
        size++;
        Node push = first;
        first = new Node(item, null, push);
        if (push != null)
            push.previous = first;
        else
            last = first;
    }
    
    public void addLast(Item item) {
        size++;
        Node push = last;
        last = new Node(item, push, null);
        if (push != null)
            push.next = last;
        else
            first = last;
    }
    
    public Item removeFirst() {
        if (first == null) {
            throw new NoSuchElementException();
        }
        else
        {
            size--;
            Node pop = first;
            first = first.next;
            if (first == null)
                last = null;
            return pop.content;
        }
    }
    
    public Item removeLast() {
        if (last == null) {
            throw new NoSuchElementException();
        }
        else
        {
            size--;
            Node pop = last;
            last = last.previous;
            if (last == null)
                first = null;
            return pop.content;
        }
    }
    
    private class DequeIterator implements Iterator<Item> {
        
        Node current;
        
        DequeIterator(Deque<Item> d) {
            
            current = new Node(null, null, d.first);
            
        }

        @Override
        public boolean hasNext() {
            return current.next != null;
        }

        @Override
        public Item next() {
            current = current.next;
            return current.content;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
        
    }

    @Override
    public Iterator<Item> iterator() {
        return new DequeIterator(this);
    }
    
    public static void main(String[] args) {
        
    }

}
