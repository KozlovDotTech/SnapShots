package com.github.qqkkbye.snapshots.filters.median;

import android.graphics.Bitmap;
import com.github.qqkkbye.snapshots.filters.BitmapFilter;
import com.github.qqkkbye.snapshots.filters.median.Median;

public class MedianFilter implements BitmapFilter {

    private static final int KERNEL_LOW_SIZE = 5;
    private static final int KERNEL_HIGH_SIZE = 5;

    @Override
    public Bitmap filterBitmap(Bitmap input, boolean enhance) {
        if (enhance) {
            return Median.doMedian(input, KERNEL_HIGH_SIZE);
        } else {
            return Median.doMedian(input, KERNEL_LOW_SIZE);
        }
    }

}
