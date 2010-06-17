package com.innovatrics.iseglib;

/**
 * SegLib version.
 * @author Martin Vysny
 */
public class SegLibVersion {
    /**
     * major library version number
     */
    public final int major;
    /**
     * minor library version
     */
    public final int minor;

    public SegLibVersion(int major, int minor) {
        this.major = major;
        this.minor = minor;
    }

    @Override
    public String toString() {
        return "SegLib v" + major + "." + minor;
    }
}
