/*************************************************************************
 * Name:
 * Email:
 *
 * Compilation:  javac Point.java
 * Execution:
 * Dependencies: StdDraw.java
 *
 * Description: An immutable data type for points in the plane.
 *
 *************************************************************************/

import java.util.Comparator;

public class Point implements Comparable<Point> {

    // compare points by slope
    public final Comparator<Point> SLOPE_ORDER = new Comparator<Point>(){

        @Override
        public int compare(Point o1, Point o2) {
            double s1 = Point.this.slopeTo(o1);
            double s2 = Point.this.slopeTo(o2);
            
            if (s1 == s2)
                return 0;
            
            if (s1 > s2)
                return 1;
            
            if (s1 < s2)
                return -1;
            
            throw new IllegalStateException();
        }
        
    };

    private final int x;                              // x coordinate
    private final int y;                              // y coordinate

    // create the point (x, y)
    public Point(int x, int y) {
        /* DO NOT MODIFY */
        this.x = x;
        this.y = y;        
    }

    // plot this point to standard drawing
    public void draw() {
        /* DO NOT MODIFY */
        StdDraw.point(x, y);
    }

    // draw line between this point and that point to standard drawing
    public void drawTo(Point that) {
        /* DO NOT MODIFY */
        StdDraw.line(this.x, this.y, that.x, that.y);
    }

    // slope between this point and that point
    public double slopeTo(Point that) {
        if (that.x == this.x) {
            if (that.y == this.y)
                return Double.NEGATIVE_INFINITY;
            else
                return Double.POSITIVE_INFINITY;
        } else
        {
            if (this.y == that.y)
                return +0.0;
            else
                return (that.y - this.y) / (double) (that.x - this.x);
        }
    }

    // is this point lexicographically smaller than that one?
    // comparing y-coordinates and breaking ties by x-coordinates
    public int compareTo(Point that) {
        int result = this.y - that.y;
        if (result == 0)
            result = this.x - that.x;
        return result;
    }

    // return string representation of this point
    public String toString() {
        /* DO NOT MODIFY */
        return "(" + x + ", " + y + ")";
    }

    // unit test
    public static void main(String[] args) {
        /* YOUR CODE HERE */
    }
}