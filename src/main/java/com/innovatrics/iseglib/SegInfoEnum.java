package com.innovatrics.iseglib;

import java.util.EnumSet;

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

    public static EnumSet<SegInfoEnum> fromFeedback(final int cflags) {
        final EnumSet<SegInfoEnum> result = EnumSet.noneOf(SegInfoEnum.class);
        for (final SegInfoEnum si : SegInfoEnum.values()) {
            if ((cflags & si.cflag) != 0) {
                result.add(si);
            }
        }
        return result;
    }
}
