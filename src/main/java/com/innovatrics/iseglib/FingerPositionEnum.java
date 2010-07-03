package com.innovatrics.iseglib;

/**
 * @author Martin Vysny
 */
public enum FingerPositionEnum {

    RightHandFourFingers(13), LeftHandFourFingers(14), TwoThumbs(15);
    public final int cval;

    private FingerPositionEnum(int cval) {
	this.cval = cval;
    }

    public static FingerPositionEnum fromCVal(final int cval) {
	for (final FingerPositionEnum format : FingerPositionEnum.values()) {
	    if (format.cval == cval) {
		return format;
	    }
	}
	throw new SegLibException("Invalid FingerPosition value: " + cval, -1);
    }
}
