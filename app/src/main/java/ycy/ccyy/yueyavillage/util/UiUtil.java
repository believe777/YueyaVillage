package ycy.ccyy.yueyavillage.util;

import android.util.TypedValue;

import ycy.ccyy.yueyavillage.YcyApplication;

public class UiUtil {

    public static float dpTopx(float dp) {
        return TypedValue.applyDimension(1, dp, YcyApplication.getApp().getResources().getDisplayMetrics());
    }
}
