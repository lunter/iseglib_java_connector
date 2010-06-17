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
         int ISegLib_Init( );
         int ISegLib_SetLicenseContent(byte[] licenseContent, int length);
         int ISegLib_Terminate( );
         int ISegLib_GetVersion(IntByReference major, IntByReference minor);
         int ISegLib_NFIQScore(int width,int height,int imageResolution,unsigned char *rawImage,int *NFIQScore);
         int ISegLib_GetImageQuality( int width, int height, int imageResolution, const unsigned char *rawImage, int *quality );
         int ISegLib_GetImageQualityInfo( int width, int height, int imageResolution, const unsigned char *rawImage, unsigned char *colorQualityBmpImage,int *length, int *activePixelsCount);
         int ISegLib_SegmentFingerprints(int width,int height,int imageResolution,unsigned char *rawImage,int expectedFingersCount,int minFingersCount,int maxFingersCount,int maxRotation,int options,int *segmentedFingersCount,int *globalAngle,int *roundingBoxes,unsigned char *boxedBmpImage,int *boxedBmpImageLength,int outWidth,int outHeight,unsigned char *rawImage1,unsigned char *rawImage2,unsigned char *rawImage3,unsigned char *rawImage4,unsigned char bcgValue,int *feedback);
         int ISegLib_ManualSegmentation(int width,int height,unsigned char *rawImage,int x1,int y1,int x2,int y2,int x3,int y3,int x4,int y4,int outWidth,int outHeight,unsigned char *outRawImage,unsigned char bcgValue);
         int ISegLib_GetImageIntensity(int width,int height,unsigned char *rawImage,int *intensity);int ISegLib_ConvertToRaw(const unsigned char *imageData,int imageLength,ISEGLIB_IMAGE_FORMAT imageFormat,int *width, int *height,unsigned char *rawImage, int *rawImageLength);
         int ISegLib_ConvertRawToImage(const unsigned char *rawImage,int width,int height,unsigned char *outImage,ISEGLIB_IMAGE_FORMAT imageFormat,int bitrate,int *length);
         String ISegLib_GetErrorMessage( int errcode );
    }

    private static void check(int result){
        if(result!=SegLibNative.ISEGLIB_E_NOERROR){
            throw new SegLibException(SegLibNative.INSTANCE.ISegLib_GetErrorMessage(result),result);
        }
    }

    /**
*	Initializes the library. This function initializes and checks the integrity of the library and verifies the validity
*	of the license. It should be called prior to any other function from the library.
*/
    public void init(){
        check(SegLibNative.INSTANCE.ISegLib_Init());
    }
    /**
	* Sets license data for further license check.
	* This function sets the license data for license check done when calling ISegLib_Init.
	* This function helps to avoid the usage of license files. It is meant to protect the license file content.
	* It has to be called before ISegLib_Init function.
        * @param licenseContent license file content
	* @param length Size of license data
        */

         public void setLicenseContent(byte[] licenseContent, int length){
             check(SegLibNative.INSTANCE.ISegLib_SetLicenseContent(licenseContent, length));
         }
/**
	* Terminates the use of the library.
	* This function releases all resources allocated by the library.
	* It should be called as the very last function of the library.
*/
         public void terminate( ){
             check(SegLibNative.INSTANCE.ISegLib_Terminate());
         }
         /**
	* Returns the library version.	This function returns the version number of the library
          * @return version, never null
*/
         public SegLibVersion getVersion(){
             final IntByReference major=new IntByReference();
             final IntByReference minor=new IntByReference();
              check(SegLibNative.INSTANCE.ISegLib_GetVersion(major, minor));
              return new SegLibVersion(major.getValue(), minor.getValue());
         }
}
