package com.github.qqkkbye.snapshots.filters.convolution;

import android.graphics.Bitmap;
import com.github.qqkkbye.snapshots.filters.BitmapFilter;
import com.github.qqkkbye.snapshots.filters.convolution.Convolution;

public class ReliefFilter implements BitmapFilter {

    private static final double[][] SIZE_FIVE_KERNEL = {{-3, -2, -1, 0, 0},
            {-2, -2, -1, 0, 0},
            {-1, -1, 1, 1, 1},
            {0, 0, 1, 2, 2},
            {0, 0, 1, 2, 3}};

    private static final double[][] SIZE_THREE_KERNEL = {{-2, -1, 0},
            {-1, 1, 1},
            {0, 1, 2}};

    @Override
    public Bitmap filterBitmap(Bitmap input, boolean enhance) {
        if (enhance) {
            return Convolution.doConvolution(input, SIZE_FIVE_KERNEL);
        } else {
            return Convolution.doConvolution(input, SIZE_THREE_KERNEL);
        }
    }

}
