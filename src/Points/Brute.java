
public class Brute {

    public static void main(String[] args) {
        In file = new In(args[0]);
        
        int N = file.readInt();
        
        Point[] points = new Point[N];
        
        for (int i = 0; i < N; i++) {
            points[i] = new Point(file.readInt(), file.readInt());
        }
        
        StdDraw.clear();
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        
        for (int i1 = 0; i1 < N; i1++) {
            Point p1 = points[i1];
            p1.draw();
            
            for (int i2 = i1; i2 < N; i2++) {
                Point p2 = points[i2];
                p2.draw();
                
                double s = p1.slopeTo(p2);

                // It's the same!
                if (s == Double.NEGATIVE_INFINITY)
                    continue;
                
                for (int i3 = i2; i3 < N; i3++) {
                    Point p3 = points[i3];
                    p3.draw();
                    
                    if (p1.slopeTo(p3) == s)
                    {
                        if (p2.slopeTo(p3) == Double.NEGATIVE_INFINITY)
                            continue;
                        
                        for (int i4 = i3; i4 < N; i4++) {
                            Point p4 = points[i4];
                            p4.draw();
                            
                            if (p3.slopeTo(p4) == Double.NEGATIVE_INFINITY)
                                continue;
                            
                            if (p1.slopeTo(p4) == s)
                            {
                                // Found it!
                                StdOut.println(p1 + " -> " + p2 + " -> " + p3 + " -> " + p4);
                                p1.drawTo(p4);
                            }
                        }
                    }
                }
            }
        }
    }

}
