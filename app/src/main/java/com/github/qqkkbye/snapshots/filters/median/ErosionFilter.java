package com.github.qqkkbye.snapshots.filters.median;

import android.graphics.Bitmap;
import com.github.qqkkbye.snapshots.filters.BitmapFilter;
import com.github.qqkkbye.snapshots.filters.median.Erosion;

public class ErosionFilter implements BitmapFilter {

    private static final double[][] SIZE_FIVE_KERNEL = {{0, 0, 1, 0, 0},
            {0, 1, 1, 1, 0},
            {1, 1, 1, 1, 1},
            {0, 1, 1, 1, 0},
            {0, 0, 1, 0, 0}};

    private static final double[][] SIZE_THREE_KERNEL = {{0, 1, 0},
            {1, 1, 1},
            {0, 1, 0}};

    @Override
    public Bitmap filterBitmap(Bitmap input, boolean enhance) {
        if (enhance) {
            return Erosion.doErosion(input, SIZE_FIVE_KERNEL);
        } else {
            return Erosion.doErosion(input, SIZE_THREE_KERNEL);
        }
    }

}
