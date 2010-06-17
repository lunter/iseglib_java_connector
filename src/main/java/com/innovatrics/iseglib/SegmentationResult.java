package com.innovatrics.iseglib;

import java.util.EnumSet;

/**
 * Holds the segmentation result data.
 * @author Martin Vysny
 */
public class SegmentationResult {

    /**
     * Contains number of fingers found in the input image
     */
    public int segmentedFingersCount;
    /**
     * On return, contains average rotation angle (in degrees) of fingers detected in the input slap image
     */
    public int globalAngle;
    /**
     * Contains segmented fingerprints. Contains exactly {@link #segmentedFingersCount} fingerprints.
     */
    public SegmentedFingerprint[] fingerprints;
    /**
     * Color bmp image with color boxes indicating positions of detected fingers
     */
    public byte[] boxedBmpImage;
    /**
     * Feedback. Indicates extra information such as hand position (left/right), missing finger position (in case when expectedFingersCount=4 but segmentation function finds only 3 prints).
     *		If a given bit in feedback variable is set, the corresponding information is correct. See {@link SegInfoEnum} for all possible feedbacks.
     */
    public EnumSet<SegInfoEnum> feedback;
}
