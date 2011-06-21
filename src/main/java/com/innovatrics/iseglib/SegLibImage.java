package com.innovatrics.iseglib;

/**
 * @author Martin Vysny
 */
public class SegLibImage {
    /**
     * Color bmp image with color map.
     */
    public RawBmpImage colorQualityImage;
    /**
     * Total count of active pixels (pixels in high quality zone not lying in noisy background)
     */
    public int activePixelsCount;

    @Override
    public String toString() {
        return "SegLibImage{activePixels=" + activePixelsCount + ",colorImage=" + colorQualityImage + "}";
    }
}
