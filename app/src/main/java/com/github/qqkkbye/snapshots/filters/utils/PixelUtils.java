package com.github.qqkkbye.snapshots.filters.utils;

public class PixelUtils {

    private PixelUtils() {
        throw new IllegalStateException("Utility class");
    }

    public static int buildPixel(int a, int r, int g, int b) {
        if (a < 0) a = 0;
        if (a > 255) a = 255;
        if (r < 0) r = 0;
        if (r > 255) r = 255;
        if (g < 0) g = 0;
        if (g > 255) g = 255;
        if (b < 0) b = 0;
        if (b > 255) b = 255;
        int resultPixel = a << 24;
        resultPixel += r << 16;
        resultPixel += g << 8;
        resultPixel += b;
        return resultPixel;
    }

    public static int getAFromPixel(int pixel) {
        return pixel >>> 24;
    }

    public static int getRFromPixel(int pixel) {
        return (pixel & 0x00ff0000) >> 16;
    }

    public static int getGFromPixel(int pixel) {
        return (pixel & 0x0000ff00) >> 8;
    }

    public static int getBFromPixel(int pixel) {
        return pixel & 0x000000ff;
    }

}
