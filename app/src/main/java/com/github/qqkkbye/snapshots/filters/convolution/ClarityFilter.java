package com.github.qqkkbye.snapshots.filters.convolution;

import android.graphics.Bitmap;
import com.github.qqkkbye.snapshots.filters.BitmapFilter;

public class ClarityFilter implements BitmapFilter {

    private static final double[][] SIZE_FIVE_KERNEL = {{-1, -1, -1, -1, -1},
            {-1, -1, -1, -1, -1},
            {-1, -1, 25, -1, -1},
            {-1, -1, -1, -1, -1},
            {-1, -1, -1, -1, -1}};

    private static final double[][] SIZE_THREE_KERNEL = {{-1, -1, -1},
            {-1, 9, -1},
            {-1, -1, -1}};

    @Override
    public Bitmap filterBitmap(Bitmap input, boolean enhance) {
        if (enhance) {
            return Convolution.doConvolution(input, SIZE_FIVE_KERNEL);
        } else {
            return Convolution.doConvolution(input, SIZE_THREE_KERNEL);
        }
    }

}
