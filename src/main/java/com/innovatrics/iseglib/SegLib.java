package com.innovatrics.iseglib;

import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.ptr.IntByReference;

/**
 * Contains bindings for the libiseglib.so library.
 * @author Martin Vysny
 */
public class SegLib {

    public static final int INTENSITY_THRESHOLD_TOO_DARK = 85;
    public static final int INTENSITY_THRESHOLD_TOO_LIGHT = 15;
    /**
     * Summary: Defines minimal width of accepted fingerprint images
     */
    public static final int ISEGLIB_MIN_IMAGE_WIDTH = 90;
    /**
     * Summary: Defines maximal width of accepted fingerprint images
     */
    public static final int ISEGLIB_MAX_IMAGE_WIDTH = 1800;
    /**
     * Summary: Defines minimal height of accepted fingerprint images
     */
    public static final int ISEGLIB_MIN_IMAGE_HEIGHT = 90;
    /**
     * Summary: Defines maximal height of accepted fingerprint images
     */
    public static final int ISEGLIB_MAX_IMAGE_HEIGHT = 1800;

    static interface SegLibNative extends Library {

        final SegLibNative INSTANCE = (SegLibNative) Native.loadLibrary("iseglib", SegLibNative.class); // NOI18N
        public static final int SEGMENTATION_INFO_MISSING_FINGER_UNKNOWN = 1;
        public static final int SEGMENTATION_INFO_MISSING_FINGER1 = 2;
        public static final int SEGMENTATION_INFO_MISSING_FINGER2 = 4;
        public static final int SEGMENTATION_INFO_MISSING_FINGER3 = 8;
        public static final int SEGMENTATION_INFO_MISSING_FINGER4 = 16;
        public static final int SEGMENTATION_INFO_LEFT_HAND = 1024;
        public static final int SEGMENTATION_INFO_RIGHT_HAND = 2048;
        public static final int ISEGLIB_E_NOERROR = 0;

        int ISegLib_Init();

        int ISegLib_SetLicenseContent(byte[] licenseContent, int length);

        int ISegLib_Terminate();

        int ISegLib_GetVersion(IntByReference major, IntByReference minor);

        int ISegLib_NFIQScore(int width, int height, int imageResolution, final byte[] rawImage, IntByReference nfiqScore);

        int ISegLib_GetImageQuality(int width, int height, int imageResolution, final byte[] rawImage, IntByReference quality);

        int ISegLib_GetImageQualityInfo(int width, int height, int imageResolution, final byte[] rawImage, byte[] colorQualityBmpImage, IntByReference length, IntByReference activePixelsCount);

        int ISegLib_SegmentFingerprints(int width, int height, int imageResolution, final byte[] rawImage, int expectedFingersCount, int minFingersCount, int maxFingersCount, int maxRotation, int options, IntByReference segmentedFingersCount, IntByReference globalAngle, IntByReference roundingBoxes, byte[] boxedBmpImage, IntByReference boxedBmpImageLength, int outWidth, int outHeight, byte[] rawImage1, byte[] rawImage2, byte[] rawImage3, byte[] rawImage4, byte bcgValue, IntByReference feedback);

        int ISegLib_ManualSegmentation(int width, int height, byte[] rawImage, int x1, int y1, int x2, int y2, int x3, int y3, int x4, int y4, int outWidth, int outHeight, byte[] outRawImage, byte bcgValue);

        int ISegLib_GetImageIntensity(int width, int height, byte[] rawImage, IntByReference intensity);

        int ISegLib_ConvertToRaw(final byte[] imageData, int imageLength, int/*ISEGLIB_IMAGE_FORMAT*/ imageFormat, IntByReference width, IntByReference height, byte[] rawImage, IntByReference rawImageLength);

        int ISegLib_ConvertRawToImage(final byte[] rawImage, int width, int height, byte[] outImage, int /*ISEGLIB_IMAGE_FORMAT*/ imageFormat, int bitrate, IntByReference length);

        String ISegLib_GetErrorMessage(int errcode);
    }

    private static void check(int result) {
        if (result != SegLibNative.ISEGLIB_E_NOERROR) {
            throw new SegLibException(SegLibNative.INSTANCE.ISegLib_GetErrorMessage(result), result);
        }
    }

    /**
     *	Initializes the library.<p/>This function initializes and checks the integrity of the library and verifies the validity
     *	of the license. It should be called prior to any other function from the library.
     */
    public void init() {
        check(SegLibNative.INSTANCE.ISegLib_Init());
    }

    /**
     * Sets license data for further license check.<p/>
     * This function sets the license data for license check done when calling ISegLib_Init.
     * This function helps to avoid the usage of license files. It is meant to protect the license file content.
     * It has to be called before ISegLib_Init function.
     * @param licenseContent license file content
     * @param length Size of license data
     */
    public void setLicenseContent(byte[] licenseContent, int length) {
        check(SegLibNative.INSTANCE.ISegLib_SetLicenseContent(licenseContent, length));
    }

    /**
     * Terminates the use of the library.<p/>
     * This function releases all resources allocated by the library.
     * It should be called as the very last function of the library.
     */
    public void terminate() {
        check(SegLibNative.INSTANCE.ISegLib_Terminate());
    }

    /**
     * Returns the library version.<p/>
     * This function returns the version number of the library
     * @return version, never null
     */
    public SegLibVersion getVersion() {
        final IntByReference major = new IntByReference();
        final IntByReference minor = new IntByReference();
        check(SegLibNative.INSTANCE.ISegLib_GetVersion(major, minor));
        return new SegLibVersion(major.getValue(), minor.getValue());
    }

    /**
     * Returns NFIQ score (quality score defined by NIST) a single fingerprint image.<p/>
     * This function returns NFIQ score (quality score defined by NIST) a fingerprint image
     * quality of the input fingerprint image. Image quality number is calculated in accordance with the general guidelines contained in Section 2.1.42 of ANSI/INCITS 358 standard.
     * @param width The number of pixels indicating the width of the image
     * @param height The number of pixels indicating the height of the image
     * @param imageResolution Resolution (in DPI) of the input image. Typical resolution is 500 DPI.
     * @param rawImage Pointer to the uncompressed raw image
     * @return NFIQ score, the output range is from 1 (highest quality) to 5 (lowest quality)
     */
    public int nfiqScore(int width, int height, int imageResolution, final byte[] rawImage) {
        final IntByReference result = new IntByReference();
        check(SegLibNative.INSTANCE.ISegLib_NFIQScore(width, height, imageResolution, rawImage, result));
        return result.getValue();
    }

    /**
     * Returns quality of a single fingerprint image.<p/>
     * This function returns quality of the input fingerprint image. Image quality number is calculated in accordance with the general guidelines contained in Section 2.1.42 of ANSI/INCITS 358 standard.
     * @param width - [in] The number of pixels indicating the width of the image
     * @param height - [in] The number of pixels indicating the height of the image
     * @param imageResolution - [in] Resolution (in DPI) of the input image. Typical resolution is 500 DPI.
     * @param rawImage - [in] Pointer to the uncompressed raw image
     * @return quality; the output range is from 0 (lowest quality) to 100 (highest quality)
     */
    public int getImageQuality(int width, int height, int imageResolution, final byte[] rawImage) {
        final IntByReference result = new IntByReference();
        check(SegLibNative.INSTANCE.ISegLib_GetImageQuality(width, height, imageResolution, rawImage, result));
        return result.getValue();
    }

    /**
     *	Returns color image quality map and total number of active pixels in the image.<p/>
     * 	This function returns color quality map as bmp image of the input fingerprint image.
     *	It also retuns total number of active pixels (pixels located in high quality zone, not lying in the noisy background).
     *	Total active pixels count can be used in order to detect void/blank images.
     *	This function works for both slap images and single finger images.
     * @param	width The number of pixels indicating the width of the image
     * @param	height The number of pixels indicating the height of the image
     * @param	imageResolution Resolution (in DPI) of the input image. Typical resolution is 500 DPI.
     * @param	rawImage Pointer to the uncompressed raw image
     * @return image with quality.
     */
    public SegLibImage getImageQualityInfo(int width, int height, int imageResolution, final byte[] rawImage) {
        final IntByReference length = new IntByReference();
        check(SegLibNative.INSTANCE.ISegLib_GetImageQualityInfo(width, height, imageResolution, rawImage, null, length, null));
        final SegLibImage result = new SegLibImage();
        result.colorQualityBmpImage = new byte[length.getValue()];
        final IntByReference activePixelsCount = new IntByReference();
        check(SegLibNative.INSTANCE.ISegLib_GetImageQualityInfo(width, height, imageResolution, rawImage, result.colorQualityBmpImage, length, activePixelsCount));
        result.activePixelsCount = activePixelsCount.getValue();
        return result;
    }
}
