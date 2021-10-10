package com.github.qqkkbye.snapshots.filters.color;

import android.graphics.Bitmap;
import com.github.qqkkbye.snapshots.filters.BitmapFilter;

public class DownSaturationFilter implements BitmapFilter {

    private static final double LOW_K = 2;

    private static final double HIGH_K = 1;

    @Override
    public Bitmap filterBitmap(Bitmap input, boolean enhance) {
        if (enhance) {
            return Saturation.doDownSaturation(input, HIGH_K);
        } else {
            return Saturation.doDownSaturation(input, LOW_K);
        }
    }

}
