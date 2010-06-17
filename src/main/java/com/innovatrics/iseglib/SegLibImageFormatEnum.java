package com.innovatrics.iseglib;

/**
 * Enumerates image formats.
 * @author Martin Vysny
 */
public enum SegLibImageFormatEnum {

    BMP(0), PNG(1), WSQ(5), JPEG2K(6);
    public final int cval;

    private SegLibImageFormatEnum(int cval) {
        this.cval = cval;
    }

    public static SegLibImageFormatEnum fromCVal(final int cval) {
        for (final SegLibImageFormatEnum format : SegLibImageFormatEnum.values()) {
            if (format.cval == cval) {
                return format;
            }
        }
        throw new SegLibException("Invalid ISEGLIB_IMAGE_FORMAT value: " + cval, -1);
    }
}
