package com.github.qqkkbye.snapshots.filters.color;

import android.graphics.Bitmap;
import com.github.qqkkbye.snapshots.filters.utils.PixelUtils;

public class Saturation {

    private Saturation() {
        throw new IllegalStateException("Utility class");
    }

    public static Bitmap doUpSaturation(Bitmap input, double k) {
        Bitmap result = input.copy(Bitmap.Config.ARGB_8888, true);
        int width = input.getWidth();
        int height = input.getHeight();
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                int pixelValue = input.getPixel(x, y);
                int r = PixelUtils.getRFromPixel(pixelValue);
                int g = PixelUtils.getGFromPixel(pixelValue);
                int b = PixelUtils.getBFromPixel(pixelValue);
                int a = PixelUtils.getAFromPixel(pixelValue);
                int max = Math.max(Math.max(r, g), b);
                r -= (max - r) / k;
                g -= (max - g) / k;
                b -= (max - b) / k;
                result.setPixel(x, y, PixelUtils.buildPixel(a, r, g, b));
            }
        }
        return result;
    }

    public static Bitmap doDownSaturation(Bitmap input, double k) {
        Bitmap result = input.copy(Bitmap.Config.ARGB_8888, true);
        int width = input.getWidth();
        int height = input.getHeight();
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                int pixelValue = input.getPixel(x, y);
                int r = PixelUtils.getRFromPixel(pixelValue);
                int g = PixelUtils.getGFromPixel(pixelValue);
                int b = PixelUtils.getBFromPixel(pixelValue);
                int a = PixelUtils.getAFromPixel(pixelValue);
                int min = Math.min(Math.min(r, g), b);
                r -= (r - min) / k;
                g -= (g - min) / k;
                b -= (b - min) / k;
                result.setPixel(x, y, PixelUtils.buildPixel(a, r, g, b));
            }
        }
        return result;
    }

}
