import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;

public class Fast {
    
    private static HashMap<Point, Double> lines = new HashMap<Point, Double>();

    public static void main(String[] args) {
        In file = new In(args[0]);
        
        int N = file.readInt();
        
        Point[] points = new Point[N];
        
        StdDraw.clear();
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        
        for (int i = 0; i < N; i++) {
            points[i] = new Point(file.readInt(), file.readInt());
            points[i].draw();
        }
        
        Quick.sort(points);
        
        
        
        for(int i = 0; i < N; i++) {
            Point p = points[i];
            Point[] points2 = new Point[N-i-1];
            for (int j = 0; j < points2.length; j++) {
                points2[j] = points[j+i+1];
            }
            Arrays.sort(points2, p.SLOPE_ORDER);
            double commonSlope = Double.NEGATIVE_INFINITY;
            LinkedList<Point> segment = new LinkedList<Point>();

            for (int j = 0; j < points2.length; j++) {
                Point q = points2[j];
                double thisSlope = p.slopeTo(q);
                if (thisSlope == Double.NEGATIVE_INFINITY) {
                    continue;
                }
               
                if (thisSlope != commonSlope) {
                    if (segment.size() >= 2) {
                        segment(p, segment, commonSlope);
                    }
                    segment.clear();
                }
               
                segment.add(q);
                commonSlope = thisSlope;
               
                if (j == points2.length-1 && segment.size() >= 2) {
                    segment(p, segment, commonSlope);
                }
            }
        }   
    }
    
    private static void segment(
            Point p,
            LinkedList<Point> segment,
            double slope
            ) {
        if (lines.containsKey(p) && lines.get(p) == slope)
            return;
        
        Point[] s = new Point[segment.size() + 1];
        s[0] = p;
        Iterator<Point> iterator = segment.iterator();
        for(int k = 0; k < segment.size(); k++)
            s[k+1] = iterator.next();        
        Arrays.sort(s);
        s[0].drawTo(s[s.length - 1]);
        String output = s[0].toString();
        for (int i = 1; i < s.length; i++)
            output += " -> " + s[i];
        StdOut.println(output);
        
        for(Point sp : s)
            lines.put(sp, slope);
    }
}
