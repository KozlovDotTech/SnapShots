package com.github.qqkkbye.snapshots.filters.median;

import android.graphics.Bitmap;
import com.github.qqkkbye.snapshots.filters.utils.PixelUtils;

import java.util.*;

public class Median {

    private Median() {
        throw new IllegalStateException("Utility class");
    }


    public static Bitmap doMedian(Bitmap input, int kernelSize) {

        Bitmap result = input.copy(Bitmap.Config.ARGB_8888, true);

        int width = input.getWidth();
        int height = input.getHeight();
        TreeMap<Integer, Integer> pixelMap = new TreeMap<>();
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                int r;
                int g;
                int b;
                for (int i = 0; i < kernelSize; i++) {
                    for (int j = 0; j < kernelSize; j++) {
                        int pixelPosX = x + (i - (kernelSize / 2));
                        int pixelPosY = y + (j - (kernelSize / 2));
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
                        r = PixelUtils.getRFromPixel(pixelValue);
                        g = PixelUtils.getGFromPixel(pixelValue);
                        b = PixelUtils.getBFromPixel(pixelValue);
                        pixelMap.put(r + g + b, pixelValue);
                    }
                }
                List<Integer> pixelKeyList = new ArrayList<>(pixelMap.keySet());
                Collections.sort(pixelKeyList);
                int avgPixelKey = pixelKeyList.get(pixelKeyList.size() / 2);
                int resultPixel;
                try {
                    resultPixel = pixelMap.get(avgPixelKey);
                } catch (NullPointerException exception) {
                    exception.printStackTrace();
                    resultPixel = 0;
                }
                result.setPixel(x, y, resultPixel);
                pixelMap.clear();
            }
        }
        return result;
    }

}
