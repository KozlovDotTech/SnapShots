package com.github.qqkkbye.snapshots.filters.median;

import android.graphics.Bitmap;
import com.github.qqkkbye.snapshots.filters.utils.PixelUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.TreeMap;

public class BuildUp {

    private BuildUp() {
        throw new IllegalStateException("Utility class");
    }


    public static Bitmap doBuildUp(Bitmap input, double[][] kernel) {

        Bitmap result = input.copy(Bitmap.Config.ARGB_8888, true);

        int width = input.getWidth();
        int height = input.getHeight();
        int kernelWidth = kernel.length;
        int kernelHeight = kernel[0].length;
        TreeMap<Integer, Integer> pixelMap = new TreeMap<>();
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                int r;
                int g;
                int b;
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
                        if (kernel[i][j] == 1) {
                            int pixelValue = input.getPixel(pixelPosX, pixelPosY);
                            r = PixelUtils.getRFromPixel(pixelValue);
                            g = PixelUtils.getGFromPixel(pixelValue);
                            b = PixelUtils.getBFromPixel(pixelValue);
                            pixelMap.put(r + g + b, pixelValue);
                        }
                    }
                }
                List<Integer> pixelKeyList = new ArrayList<>(pixelMap.keySet());
                Collections.sort(pixelKeyList);
                int pixelKey = pixelKeyList.get(pixelKeyList.size() - 1);
                int resultPixel;
                try {
                    resultPixel = pixelMap.get(pixelKey);
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
