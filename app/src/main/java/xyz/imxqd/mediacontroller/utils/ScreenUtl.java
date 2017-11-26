package xyz.imxqd.mediacontroller.utils;

import android.util.DisplayMetrics;

import xyz.imxqd.mediacontroller.App;

/**
 * Created by imxqd on 2017/11/26.
 */

public class ScreenUtl {
    private static float sDensity = -1;

    public static int dp2px(float dp) {
        if (sDensity == -1) {
            DisplayMetrics metrics = App.get().getResources().getDisplayMetrics();
            sDensity = metrics.density;
        }
        return (int) (dp * sDensity);
    }
}
