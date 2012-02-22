package com.innovatrics.iseglib;

/**
 * Holds a raw image in 8-bit grayscale raw format (0=black, 127=gray, 255=white),
 * @author Martin Vysny
 */
public class RawImage {

    /**
     * Creates a new raw image.
     * @param width The number of pixels indicating the width of the image
     * @param height The number of pixels indicating the height of the image
     * @param rawImage raw image in 8-bit grayscale raw format (0=black, 127=gray, 255=white),
     */
    public RawImage(int width, int height, byte[] rawImage) {
	this.width = width;
	this.height = height;
	this.image = rawImage;
        if (rawImage.length < width * height) {
            throw new IllegalArgumentException("Parameter raw: invalid size: expected " + width * height + " but got " + rawImage.length);
        }
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
    public final byte[] image;

    @Override
    public String toString() {
	return "RawImage{" + width + "x" + height + '}';
    }

    /**
     * Returns a pixel located on x,y coordinates. 
     * @param x the x coordinate
     * @param y the y coordinate
     * @return the pixel value, 0 = black, 127 = gray, 255 = white.
     */
    public byte getPixel(int x, int y) {
	return image[y * width + x];
    }

    /**
     * Returns size of the image.
     * @return the dimension object.
     */
    public Dimension getDimension() {
	return new Dimension(width, height);
    }

    /**
     * Inverts colors of this image.
     */
    public void invert() {
	for (int i = 0; i < image.length; i++) {
	    image[i] = (byte) (image[i] ^ 0xFF);
	}
    }
}
