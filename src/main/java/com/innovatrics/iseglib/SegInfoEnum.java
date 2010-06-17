package com.innovatrics.iseglib;

/**
 *
 * @author Martin Vysny
 */
public enum SegInfoEnum {

    MissingFingerUnknown(SegLib.SegLibNative.SEGMENTATION_INFO_MISSING_FINGER_UNKNOWN),
    MissingFinger1(SegLib.SegLibNative.SEGMENTATION_INFO_MISSING_FINGER1),
    MissingFinger2(SegLib.SegLibNative.SEGMENTATION_INFO_MISSING_FINGER2),
    MissingFinger3(SegLib.SegLibNative.SEGMENTATION_INFO_MISSING_FINGER3),
    MissingFinger4(SegLib.SegLibNative.SEGMENTATION_INFO_MISSING_FINGER4),
    LeftHand(SegLib.SegLibNative.SEGMENTATION_INFO_LEFT_HAND),
    RightHand(SegLib.SegLibNative.SEGMENTATION_INFO_RIGHT_HAND);
    public final int cflag;

    private SegInfoEnum(final int cflag) {
        this.cflag = cflag;
    }
}
