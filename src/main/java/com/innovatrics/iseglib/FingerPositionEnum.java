package com.innovatrics.iseglib;

/**
 * @author Martin Vysny
 */
public enum FingerPositionEnum {

    RightHandFourFingers(13, 4),
    LeftHandFourFingers(14, 4),
    TwoThumbs(15, 2);
    public final int cval;
    public final int numberOfFingers;

    private FingerPositionEnum(int cval, final int numberOfFingers) {
	this.cval = cval;
	this.numberOfFingers = numberOfFingers;
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
