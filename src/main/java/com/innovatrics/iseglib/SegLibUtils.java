package com.innovatrics.iseglib;

import java.awt.Transparency;
import java.awt.color.ColorSpace;
import java.awt.image.*;

/**
 * All stuff not present in Android is moved here.
 * @author Martin Vysny
 */
public class SegLibUtils {

    public static BufferedImage toBufferedImage(RawImage img) {
	final SampleModel sm = DEFAULT_COLOR_MODEL.createCompatibleSampleModel(img.width, img.height);
	final DataBufferByte db = new DataBufferByte(img.image, img.width * img.height);
	final WritableRaster raster = Raster.createWritableRaster(sm, db, null);
	final BufferedImage result = new BufferedImage(DEFAULT_COLOR_MODEL, raster, false, null);
	return result;
    }
    private static final ColorModel DEFAULT_COLOR_MODEL = new ComponentColorModel(ColorSpace.getInstance(ColorSpace.CS_GRAY), new int[]{8}, false, true, Transparency.OPAQUE, DataBuffer.TYPE_BYTE);
}
