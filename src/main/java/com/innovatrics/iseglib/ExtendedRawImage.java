package com.innovatrics.iseglib;

/**
 * @author Martin Vysny
 */
public class ExtendedRawImage {

    public IsoImageFormatEnum originalImageFormat;
    public FingerPositionEnum fingerPosition;
    public RawImage raw;
    public int dpiX;
    public int dpiY;

    @Override
    public String toString() {
        return "ExtendedRawImage{" + "originalImageFormat=" + originalImageFormat + ", fingerPosition=" + fingerPosition + ", raw=" + raw + ", dpiX=" + dpiX + ", dpiY=" + dpiY + '}';
    }
}
