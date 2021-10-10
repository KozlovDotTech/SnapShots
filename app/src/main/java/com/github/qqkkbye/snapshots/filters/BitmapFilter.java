package com.github.qqkkbye.snapshots.filters;

import android.graphics.Bitmap;

public interface BitmapFilter {

    public Bitmap filterBitmap(Bitmap bitmap, boolean enhance);

}
