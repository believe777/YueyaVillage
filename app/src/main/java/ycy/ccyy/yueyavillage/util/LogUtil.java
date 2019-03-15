package ycy.ccyy.yueyavillage.util;

import android.util.Log;

//log工具类
// TODO: 2019/3/15 对于error和warm类log，后期考虑缓存本地或者上传服务器
public class LogUtil {
    private static final String TAG = "YueyaVillage";

    public static void i(String msg) {
        Log.i(TAG, msg);
    }

    public static void d(String msg) {
        Log.d(TAG, msg);
    }

    public static void e(String msg) {
        Log.e(TAG, msg);
    }

    public static void w(String msg) {
        Log.w(TAG, msg);
    }

    public static void v(String msg) {
        Log.v(TAG, msg);
    }
}
