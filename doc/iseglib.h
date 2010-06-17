#ifndef _INC_ISEGLIB
#define _INC_ISEGLIB

#ifdef __cplusplus
extern "C" {
#endif

#define ISEGLIB_API

typedef enum
{
    FORMAT_BMP = 0,
    FORMAT_PNG = 1,
    FORMAT_WSQ = 5,
    FORMAT_JPEG2K = 6
} ISEGLIB_IMAGE_FORMAT;

#define SEGMENTATION_INFO_MISSING_FINGER_UNKNOWN 1
#define SEGMENTATION_INFO_MISSING_FINGER1 2
#define SEGMENTATION_INFO_MISSING_FINGER2 4
#define SEGMENTATION_INFO_MISSING_FINGER3 8
#define SEGMENTATION_INFO_MISSING_FINGER4 16
#define SEGMENTATION_INFO_LEFT_HAND 1024
#define SEGMENTATION_INFO_RIGHT_HAND 2048

#define INTENSITY_THRESHOLD_TOO_DARK 85
#define INTENSITY_THRESHOLD_TOO_LIGHT 15


/*
Summary: Defines minimal width of accepted fingerprint images
*/
#define ISEGLIB_MIN_IMAGE_WIDTH 90
/*
Summary: Defines maximal width of accepted fingerprint images
*/
#define ISEGLIB_MAX_IMAGE_WIDTH 1800
/*
Summary: Defines minimal height of accepted fingerprint images
*/
#define ISEGLIB_MIN_IMAGE_HEIGHT 90
/*
Summary: Defines maximal height of accepted fingerprint images
*/
#define ISEGLIB_MAX_IMAGE_HEIGHT 1800


#define ISEGLIB_E_UNKNOWN_MSG		"Unknown error."


#define ISEGLIB_E_NOERROR             0
#define ISEGLIB_E_NOERROR_MSG	"No error."
#define ISEGLIB_E_BADPARAM            1101
#define ISEGLIB_E_BADPARAM_MSG	"Invalid parameter type provided."
#define ISEGLIB_E_BLANKIMAGE          1114
#define ISEGLIB_E_BLANKIMAGE_MSG "Image is blank or contains non-recognizable fingerprint."
#define ISEGLIB_E_BADIMAGE            1115
#define ISEGLIB_E_BADIMAGE_MSG  "Invalid image or unsupported image format."
#define ISEGLIB_E_INIT                1116
#define ISEGLIB_E_INIT_MSG		"Library was not initialized."
#define ISEGLIB_E_FILE                1117
#define ISEGLIB_E_FILE_MSG			"Error occured while opening/reading file."
#define ISEGLIB_E_MEMORY              1120
#define ISEGLIB_E_MEMORY_MSG		"Memory allocation failed."
#define ISEGLIB_E_NULLPARAM           1121
#define ISEGLIB_E_NULLPARAM_MSG		"NULL input parameter provided."
#define ISEGLIB_E_OTHER               1122
#define ISEGLIB_E_OTHER_MSG			"Other unspecified error."
#define ISEGLIB_E_BADLICENSE	      1129
#define ISEGLIB_E_BADLICENSE_MSG "Provided license is not valid, or no license was found."
#define ISEGLIB_E_BADFORMAT           1132
#define ISEGLIB_E_BADFORMAT_MSG "Unsupported format."
#define ISEGLIB_E_BADVALUE            1133
#define ISEGLIB_E_BADVALUE_MSG "Invalid value provided."
#define ISEGLIB_E_BADTEMPLATE         1135
#define ISEGLIB_E_BADTEMPLATE_MSG	"Invalid template or unsupported template format."
#define ISEGLIB_E_READONLY            1136
#define ISEGLIB_E_READONLY_MSG	"Value cannot be modified."
#define ISEGLIB_E_NOTDEFINED          1137
#define ISEGLIB_E_NOTDEFINED_MSG	"Value is not defined."
#define ISEGLIB_E_NULLTEMPLATE        1138
#define ISEGLIB_E_NULLTEMPLATE_MSG    "Template is NULL (contains no finger view)."
#define ISEGLIB_E_FOUND_MORE_FINGERS  1160
#define ISEGLIB_E_FOUND_MORE_FINGERS_MSG "Found more fingers than expected."
#define ISEGLIB_E_FOUND_LESS_FINGERS  1161
#define ISEGLIB_E_FOUND_LESS_FINGERS_MSG "Found less fingers than expected."

/*
Summary:
	Initializes the library
Description:
	This function initializes and checks the integrity of the library and verifies the validity
	of the license. It should be called prior to any other function from the library.
Return Value List:
	ISEGLIB_OK - No error occurred.
	ISEGLIB_E_BADLICENSE - Provided license is not valid, or no license was found.
*/
ISEGLIB_API int ISegLib_Init( );

/*
Summary:
	Sets license data for further license check. 

Description:
	This function sets the license data for license check done when calling ISegLib_Init.
	This function helps to avoid the usage of license files. It is meant to protect the license file content.
	It has to be called before ISegLib_Init function.

Parameters:
	licenseContent - [in] Pointer to license file content
	length - [in] Size of license data

Return Value List:
	ISEGLIB_OK - No error occurred.
	ISEGLIB_E_BADLICENSE - License is not valid.
*/
ISEGLIB_API int ISegLib_SetLicenseContent(unsigned char *licenseContent, int length);


/*
Summary:
	Terminates the use of the library
Description:
	This function releases all resources allocated by the library.
	It should be called as the very last function of the library.
Return Value List:
	ISEGLIB_OK - No error occurred.
*/
ISEGLIB_API int ISegLib_Terminate( );

/*
Summary:
	Returns the library version
Description:
	This function returns the version number of the library
Parameters:
	major - [out] Pointer where major library version number will be stored
	minor - [out] Pointer where minor library version number will be stored
Return Value List:
	ISEGLIB_OK - No error occurred.
	ISEGLIB_E_NULLPARAM - NULL input parameter provided.
*/
ISEGLIB_API int ISegLib_GetVersion(int *major, int *minor);



/*
Summary:
	Returns NFIQ score (quality score defined by NIST) a single fingerprint image

Parameters:
	width - [in] The number of pixels indicating the width of the image
	height - [in] The number of pixels indicating the height of the image
	imageResolution - [in] Resolution (in DPI) of the input image. Typical resolution is 500 DPI.
	rawImage - [in] Pointer to the uncompressed raw image
	NFIQScore - [out] NFIQ scre, the output range is from 1 (highest quality) to 5 (lowest quality)

Description:
	This function returns NFIQ score (quality score defined by NIST) a fingerprint image
quality of the input fingerprint image. Image quality number is calculated in accordance with the general guidelines contained in Section 2.1.42 of ANSI/INCITS 358 standard.

Return Value List:
	ISEGLIB_OK - No error occurred.
	ISEGLIB_E_BADFORMAT - Unsupported image format.
	ISEGLIB_E_INIT - Library was not initialized.
	ISEGLIB_E_NULLPARAM - NULL input parameter provided.
*/
ISEGLIB_API int ISegLib_NFIQScore(int width,int height,int imageResolution,unsigned char *rawImage,int *NFIQScore);


/*
Summary:
	Returns quality of a single fingerprint image

Parameters:
	width - [in] The number of pixels indicating the width of the image
	height - [in] The number of pixels indicating the height of the image
	imageResolution - [in] Resolution (in DPI) of the input image. Typical resolution is 500 DPI.
	rawImage - [in] Pointer to the uncompressed raw image
	quality - [out] Fingerprint image quality, the output range is from 0 (lowest quality) to 100 (highest quality)

Description:
	This function returns quality of the input fingerprint image. Image quality number is calculated in accordance with the general guidelines contained in Section 2.1.42 of ANSI/INCITS 358 standard.

Return Value List:
	ISEGLIB_OK - No error occurred.
	ISEGLIB_E_BADFORMAT - Unsupported image format.
	ISEGLIB_E_INIT - Library was not initialized.
	ISEGLIB_E_NULLPARAM - NULL input parameter provided.
*/
ISEGLIB_API int ISegLib_GetImageQuality( int width, int height, int imageResolution, const unsigned char *rawImage, int *quality );

/*
Summary:
	Returns color image quality map and total number of active pixels in the image

Parameters:
	width - [in] The number of pixels indicating the width of the image
	height - [in] The number of pixels indicating the height of the image
	imageResolution - [in] Resolution (in DPI) of the input image. Typical resolution is 500 DPI.
	rawImage - [in] Pointer to the uncompressed raw image
	colorQualityBmpImage - [out] Pointer where color bmp image with color map will be stored
	length - [in/out] On input, it has to be set to the size of colorQualityBmpImage, on output, it indicates number of bytes needed/written to colorQualityBmpImage
	activePixelsCount - [out] Returns total count of active pixels (pixels in high quality zone not lying in noisy background)

Description:
	This function returns color quality map as bmp image of the input fingerprint image.
	It also retuns total number of active pixels (pixels located in high quality zone, not lying in the noisy background).
	Total active pixels count can be used in order to detect void/blank images.
	This function works for both slap images and single finger images.

Return Value List:
	ISEGLIB_OK - No error occurred.
	ISEGLIB_E_BADFORMAT - Unsupported image format.
	ISEGLIB_E_INIT - Library was not initialized.
	ISEGLIB_E_NULLPARAM - NULL input parameter provided.
*/
ISEGLIB_API int ISegLib_GetImageQualityInfo( int width, int height, int imageResolution, const unsigned char *rawImage, unsigned char *colorQualityBmpImage,int *length, int *activePixelsCount);

/*
Summary:
	Segments slap fingerprint image into individual prints.

Description:
	This function separates slap fingerprint image into individual prints, returns
	image with color boxes indicating positions of detected fingers, returns number of
	prints detected in the input image, returns information on missing digits (fingers),
	for 4 and 3 finger slap images, returns information about hand position (left/right hand).

Parameters:
	width - [in] The number of pixels indicating the width of the image
	height - [in] The number of pixels indicating the height of the image
	imageResolution - [in] Resolution (in DPI) of the input image. Typical resolution is 500 DPI.
	rawImage - [in] Pointer to the uncompressed raw image
	expectedFingersCount - [in] Number if fingers expected to be found in the input slap image. Valid range: 0..4
	minFingersCount - [in] Minimum number of fingers that has to be detected in the input slap image. If less fingers are detected, an error code is returned. Valid range: 0..4
	maxFingersCount - [in] Maximum number of fingers that has to be detected in the input slap image. If more fingers are detected, an error code is returned. Valid range: 0..4
	maxRotation - [in] Value indicating maximum rotation of fingers in the input slap image. Value is in degrees. Valid range: 0..45
	options - [in] Reserved for future use, should be set to 0.
	segmentedFingersCount - [out] On return, contains number of fingers found in the input image
	globalAngle - [out] On return, contains average rotation angle (in degrees) of fingers detected in the input slap image
	roundingBoxes - [out] On return, contains coordinates of rectangles where detected fingers are lying. 8 numbers are returned for each detected finger, in the following format: [x1,y1],[x2,y2],[x3,y3],[x4,y4]
	boxedBmpImage - [out] Pointer where color bmp image with color boxes indicating positions of detected fingers will be written
	boxedBmpImageLength - [in/out] On input, this value should indicate size of boxedBmpImage, on output, it indicates number of bytes needed/written to boxedBmpImage
	outWidth - [in] Indicates width of returned raw images of segmented fingers. If found segmented fingers are smaller, they will be centered in the middle and border will be set to bcgValue
	outHeight - [in] Indicates height of returned raw images of segmented fingers. If found segmented fingers are smaller, they will be centered in the middle and border will be set to bcgValue
	rawImage1 - [out] Pointer where first detected finger (if any) will be stored (sequence going from left to right). Image will be stored here as uncompressed raw image, the size of this array has to be equal or bigger than outWidth * outHeight
	rawImage2 - [out] Pointer where second detected finger (if any)  will be stored (sequence going from left to right). Image will be stored here as uncompressed raw image, the size of this array has to be equal or bigger than outWidth * outHeight
	rawImage3 - [out] Pointer where third detected finger (if any) will be stored (sequence going from left to right). Image will be stored here as uncompressed raw image, the size of this array has to be equal or bigger than outWidth * outHeight
	rawImage4 - [out] Pointer where forth detected finger (if any) will be stored (sequence going from left to right). Image will be stored here as uncompressed raw image, the size of this array has to be equal or bigger than outWidth * outHeight
	bcgValue - [in] Value used for background for returned segmented images if these images have smaller dimensions than outWith,outHeight
	feedback - [out] Indicates extra information such as hand position (left/right), missing finger position (in case when expectedFingersCount=4 but segmentation function finds only 3 prints).
		If a given bit in feedback variable is set, the corresponding information is correct. See SEGMENTATION_INFO_XXX defines for all possible feedbacks.

Return Value List:
	ISEGLIB_OK - No error occurred.
	ISEGLIB_E_BADFORMAT - Unsupported image format.
	ISEGLIB_E_INIT - Library was not initialized.
	ISEGLIB_E_NULLPARAM - NULL input parameter provided.
	ISEGLIB_E_FOUND_LESS_FINGERS - if less fingers than minFingersCount is detected
	ISEGLIB_E_FOUND_MORE_FINGERS - if more fingers than maxFingersCount is detected
*/
ISEGLIB_API int ISegLib_SegmentFingerprints(int width,int height,int imageResolution,unsigned char *rawImage,int expectedFingersCount,int minFingersCount,int maxFingersCount,int maxRotation,int options,int *segmentedFingersCount,int *globalAngle,int *roundingBoxes,unsigned char *boxedBmpImage,int *boxedBmpImageLength,int outWidth,int outHeight,unsigned char *rawImage1,unsigned char *rawImage2,unsigned char *rawImage3,unsigned char *rawImage4,unsigned char bcgValue,int *feedback);

/*
Summary:
	Performs manual segmentation, e.g. extracts specified rectangle from slap image.

Description:
	This function returns the content of the rectangle as manually specified by user. The content of this rectangle is returned as raw image.

Parameters:
	width - [in] The number of pixels indicating the width of the image
	height - [in] The number of pixels indicating the height of the image
	rawImage - [in] Pointer to the uncompressed raw image
	x1 - [in] x coordinate of first point of the rectangle
	y1 - [in] y coordinate of first point of the rectangle
	x2 - [in] x coordinate of second point of the rectangle
	y2 - [in] y coordinate of second point of the rectangle
	x3 - [in] x coordinate of third point of the rectangle
	y3 - [in] y coordinate of third point of the rectangle
	x4 - [in] x coordinate of forth point of the rectangle
	y4 - [in] y coordinate of forth point of the rectangle
	outWidth - [in] Indicates width of returned raw image. If extracted image is smaller, it will be centered in the middle and border will be set to bcgValue
	outHeight - [in] Indicates height of returned raw image. If extracted image is smaller, it will be centered in the middle and border will be set to bcgValue
	outRawImage - [out] Pointer where extracted image will be stored. Image will be written here as uncompressed raw image, the size of this array has to be equal or bigger than outWidth * outHeight
	bcgValue - [in] Value used for background for returned image if this image has smaller dimensions than outWith,outHeight

Return Value List:
	ISEGLIB_OK - No error occurred.
	ISEGLIB_E_INIT - Library was not initialized.
	ISEGLIB_E_NULLPARAM - NULL input parameter provided.
*/
ISEGLIB_API int ISegLib_ManualSegmentation(int width,int height,unsigned char *rawImage,int x1,int y1,int x2,int y2,int x3,int y3,int x4,int y4,int outWidth,int outHeight,unsigned char *outRawImage,unsigned char bcgValue);

/*
Summary:
	Returns image intensity (darkness).

Description:
	This function analyzes fingerprint image contained in the input image and returns the intensity of the contained print(s).
	The intensity value is low for dry fingerprints (and for low pressure prints) and high for wet fingerprints (or for high pressure prints)
	This function can be either used with single finger images or with slap images.

Parameters:
	width - [in] The number of pixels indicating the width of the image
	height - [in] The number of pixels indicating the height of the image
	rawImage - [in] Pointer to the uncompressed raw image
	intensity - [out] Indicates the intensity of the input image. Returned values are in the following range: 0..100.
		0 means lowest intensity (dry finger, low pressure), 100 means highest intensity (wet fnger, high pressure).

Return Value List:
	ISEGLIB_OK - No error occurred.
	ISEGLIB_E_INIT - Library was not initialized.
	ISEGLIB_E_NULLPARAM - NULL input parameter provided.
*/
ISEGLIB_API int ISegLib_GetImageIntensity(int width,int height,unsigned char *rawImage,int *intensity);

/*
Summary:
	Reads formatted image from memory and converts it to raw 8-bit format

Description:
	This function reads image encoded in a byte array and converts it
	to raw 8-bit format as described in paragraph <link Fingerprint Image Data>

Parameters:
	imageData - [in] Pointer to the image in BMP format stored in the memory
	imageLength - [in] Indicates lenght of input image
	imageFormat - [in] Indicates format in which input image is encoded
	width - [out] On return, contains the width of converted image
	height - [out] On return, contains the height of converted image
	rawImage - [out] Pointer to memory space where raw image will be written
	rawImageLength - [in/out] On input, rawImageLength value is interpreted as the total size of allocated memory pointed by rawImage parameter.
		On return, this parameter will be equal to the total size of the image after conversion to raw format.

Returns:
	If rawImage is NULL or if rawImageLength parameter is less than the size of the input image after conversion to 8-bit raw format,
	rawImageLength parameter will be set to the required length of rawImage array.

	If rawImage is not NULL and if rawImageLength is greater or equal to the total memory size required to store the input image in 8-bit raw format,
	input image will be converted to 8-bit raw image format and written into memory space pointed by rawImage parameter.

Return Value List:
	ISEGLIB_OK - No error occurred.
	ISEGLIB_E_BADFORMAT - Unsupported image format.
	ISEGLIB_E_INIT - Library was not initialized.
	ISEGLIB_E_NULLPARAM - NULL input parameter provided.
*/
ISEGLIB_API int ISegLib_ConvertToRaw(const unsigned char *imageData,int imageLength,ISEGLIB_IMAGE_FORMAT imageFormat,int *width, int *height,unsigned char *rawImage, int *rawImageLength);

/*
Summary:
	Converts raw 8-bit image into formatted image

Description:
	This function reads raw 8-bit image and encodes it into specified image format

Parameters:
	rawImage - [in] Pointer to the uncompressed raw image
	width - [in] The number of pixels indicating the width of the image
	height - [in] The number of pixels indicating the height of the image
	outImage - [out] Pointer to memory space where output (converted) image will be written
	imageFormat - [in] Indicates image format in which the output image should be encoded
	bitrate - [in] Specifies compression rate for JPEG2000 and WSQ images. Ignored for other image formats.
	length - [in/out] On input, this value is interpreted as the total size of allocated memory pointed by outImage parameter.
		On return, this parameter will be equal to the total size of the output image after conversion to the specified format.

Returns:
	If outImage is NULL or if length parameter is less than the size of the ouput image,
	length parameter will be set to the required length of outImage array.

	If outImage is not NULL and if length parameter is greater or equal to the total memory size required to store the output image,
	input image will be encoded as specified and written into memory space pointed by outImage parameter. See also values INTENSITY_THRESHOLD_TOO_DARK and
	INTENSITY_THRESHOLD_TOO_LIGHT constants for recommended thresholds defining too dark and too light prints.

Return Value List:
	ISEGLIB_OK - No error occurred.
	ISEGLIB_E_BADFORMAT - Unsupported image format.
	ISEGLIB_E_INIT - Library was not initialized.
	ISEGLIB_E_NULLPARAM - NULL input parameter provided.
*/
ISEGLIB_API int ISegLib_ConvertRawToImage(const unsigned char *rawImage,int width,int height,unsigned char *outImage,ISEGLIB_IMAGE_FORMAT imageFormat,int bitrate,int *length);

/*
Summary:
	Returns human understandable error message
Parameters:
	errcode - [in] Error code to be translated into error message
Description:
	This function returns error message string corresponding to the specified error code.
*/
ISEGLIB_API char * ISegLib_GetErrorMessage( int errcode );

#ifdef __cplusplus
}
#endif

#endif
