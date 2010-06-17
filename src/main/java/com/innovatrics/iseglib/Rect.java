package com.innovatrics.iseglib;

import java.awt.Point;

/**
 * A rectangle.
 * @author Martin Vysny
 */
public class Rect {

    public Rect(Rect other) {
        point1 = new Point(other.point1);
        point2 = new Point(other.point2);
        point3 = new Point(other.point3);
        point4 = new Point(other.point4);
    }

    public Rect(int x1, int y1, int x2, int y2, int x3, int y3, int x4, int y4) {
        point1 = new Point(x1, y1);
        point2 = new Point(x2, y2);
        point3 = new Point(x3, y3);
        point4 = new Point(x4, y4);
    }

    public Rect(Point point1, Point point2, Point point3, Point point4) {
        this.point1 = point1;
        this.point2 = point2;
        this.point3 = point3;
        this.point4 = point4;
    }
    public final Point point1, point2, point3, point4;

    /**
     * Expects an array with the following values: x1, y1, x2, y2, x3, y3, x4, y4
     * @param array the array.
     * @param off offset in the array
     * @return rectangle instance, never null.
     */
    public static Rect from(int[] array, int off) {
        return new Rect(array[off], array[off + 1], array[off + 2], array[off + 3], array[off + 4], array[off + 5], array[off + 6], array[off + 7]);
    }
}
