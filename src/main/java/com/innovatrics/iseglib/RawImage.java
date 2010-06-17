package com.innovatrics.iseglib;

/**
 * Holds a raw image.
 * @author Martin Vysny
 */
public class RawImage {

    public RawImage(int width, int height, byte[] rawImage) {
        this.width = width;
        this.height = height;
        this.rawImage = rawImage;
    }
    /**
     * The number of pixels indicating the width of the image
     */
    public final int width;
    /**
     *The number of pixels indicating the height of the image
     */
    public final int height;
    /**
     *  raw image in 8-bit raw format,
     */
    public final byte[] rawImage;

    @Override
    public String toString() {
        return "RawImage{" + width + "x" + height + '}';
    }
}
