package com.github.qqkkbye.snapshots.filters.convolution;

import android.graphics.Bitmap;
import com.github.qqkkbye.snapshots.filters.utils.PixelUtils;

public class Convolution {

    private Convolution() {
        throw new IllegalStateException("Utility class");
    }

    public static Bitmap doConvolution(Bitmap input, double[][] kernel) {
        Bitmap result = input.copy(Bitmap.Config.ARGB_8888, true);
        int width = input.getWidth();
        int height = input.getHeight();
        int kernelWidth = kernel.length;
        int kernelHeight = kernel[0].length;
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                double rSum = 0;
                double gSum = 0;
                double bSum = 0;
                double kSum = 0;
                for (int i = 0; i < kernelWidth; i++) {
                    for (int j = 0; j < kernelHeight; j++) {
                        int pixelPosX = x + (i - (kernelWidth / 2));
                        int pixelPosY = y + (j - (kernelHeight / 2));
                        if (pixelPosX < 0) {
                            pixelPosX = 0;
                        }
                        if (pixelPosX >= width) {
                            pixelPosX = width - 1;
                        }
                        if (pixelPosY < 0) {
                            pixelPosY = 0;
                        }
                        if (pixelPosY >= height) {
                            pixelPosY = height - 1;
                        }
                        int pixelValue = input.getPixel(pixelPosX, pixelPosY);
                        double kernelVal = kernel[i][j];
                        int r = PixelUtils.getRFromPixel(pixelValue);
                        int g = PixelUtils.getGFromPixel(pixelValue);
                        int b = PixelUtils.getBFromPixel(pixelValue);
                        rSum += r * kernelVal;
                        gSum += g * kernelVal;
                        bSum += b * kernelVal;
                        kSum += kernelVal;
                    }
                }
                if (kSum <= 0) kSum = 1;
                rSum /= kSum;
                if (rSum < 0) rSum = 0;
                if (rSum > 255) rSum = 255;
                gSum /= kSum;
                if (gSum < 0) gSum = 0;
                if (gSum > 255) gSum = 255;
                bSum /= kSum;
                if (bSum < 0) bSum = 0;
                if (bSum > 255) bSum = 255;
                int a = PixelUtils.getAFromPixel(input.getPixel(x, y));
                result.setPixel(x, y, PixelUtils.buildPixel(a, (int) rSum, (int) gSum, (int) bSum));
            }
        }
        return result;
    }

}
