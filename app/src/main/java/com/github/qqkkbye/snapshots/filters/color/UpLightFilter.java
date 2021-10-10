package com.github.qqkkbye.snapshots.filters.color;

import android.graphics.Bitmap;
import com.github.qqkkbye.snapshots.filters.BitmapFilter;

public class UpLightFilter implements BitmapFilter {

    private static final double LOW_K = 1.5;

    private static final double HIGH_K = 2.5;

    @Override
    public Bitmap filterBitmap(Bitmap input, boolean enhance) {
        if (enhance) {
            return Light.doLight(input, HIGH_K);
        } else {
            return Light.doLight(input, LOW_K);
        }
    }

}
