package com.innovatrics.iseglib;

import java.awt.Transparency;
import java.awt.color.ColorSpace;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.ComponentColorModel;
import java.awt.image.DataBuffer;
import java.awt.image.DataBufferByte;
import java.awt.image.Raster;
import java.awt.image.SampleModel;
import java.awt.image.WritableRaster;

/**
 * Holds a raw image.
 * @author Martin Vysny
 */
public class RawImage {

    /**
     * Creates a new raw image.
     * @param width The number of pixels indicating the width of the image
     * @param height The number of pixels indicating the height of the image
     * @param rawImage raw image in 8-bit greyscale raw format (0=black, 255=white),
     */
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

    public BufferedImage toImage(){
	final ColorSpace cs = ColorSpace.getInstance(ColorSpace.CS_GRAY);
	final int[] nBits = {8};
	final ColorModel cm = new ComponentColorModel(cs, nBits, false, true, Transparency.OPAQUE, DataBuffer.TYPE_BYTE);
	final SampleModel sm = cm.createCompatibleSampleModel(width, height);
	final DataBufferByte db = new DataBufferByte(rawImage, width * height);
	final WritableRaster raster = Raster.createWritableRaster(sm, db, null);
	final BufferedImage result = new BufferedImage(cm, raster, false, null);
	return result;
    }
}
