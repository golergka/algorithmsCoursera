
public class Subset {

    public static void main(String[] args) {
        if (args.length < 1)
            StdOut.println("Should have 1 argument!");
        
        int k = Integer.parseInt(args[0]);
        
        String[] strings = StdIn.readAllStrings();
        
        RandomizedQueue<String> r = new RandomizedQueue<String>();
        
        for (String s : strings)
            r.enqueue(s);
        
        for (int i = 0; i < k; i++)
            StdOut.println(r.dequeue());
    }
}
