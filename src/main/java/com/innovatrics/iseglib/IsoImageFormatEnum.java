package com.innovatrics.iseglib;

/**
 * @author Martin Vysny
 */
public enum IsoImageFormatEnum {
    WSQ(2), JPG(3), PNG(5), JPEG2K(4);
    public final int cval;

    private IsoImageFormatEnum(int cval) {
        this.cval = cval;
    }

    public static IsoImageFormatEnum fromCVal(final int cval) {
        for (final IsoImageFormatEnum format : IsoImageFormatEnum.values()) {
            if (format.cval == cval) {
                return format;
            }
        }
        throw new SegLibException("Invalid ISO_IMAGE_FORMAT value: " + cval, -1);
    }
}
