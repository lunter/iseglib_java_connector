package com.innovatrics.iseglib;

import java.awt.Point;

/**
 * A rectangle.
 * @author Martin Vysny
 */
public class Rect {

    public Point point1, point2, point3, point4;

    /**
     * Expects an array with the following values: x1, y1, x2, y2, x3, y3, x4, y4
     * @param array the array.
     * @param off offset in the array
     * @return rectangle instance, never null.
     */
    public static Rect from(int[] array, int off) {
        final Rect rect = new Rect();
        rect.point1 = new Point(array[off], array[off + 1]);
        rect.point2 = new Point(array[off + 2], array[off + 3]);
        rect.point3 = new Point(array[off + 4], array[off + 5]);
        rect.point4 = new Point(array[off + 6], array[off + 7]);
        return rect;
    }
}
