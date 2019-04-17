package ycy.ccyy.yueyavillage.util;

public class KeysUtil {
    static {
        System.loadLibrary("app-info");
    }

    public static native String getAppID();
    public static native String getAppKey();
    public static native String getKey();
}
