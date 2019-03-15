package ycy.ccyy.yueyavillage.util;

import android.content.SharedPreferences;

import ycy.ccyy.yueyavillage.YcyApplication;

//SharedPreferences工具类
public class SharedPreferenceUtil {
    static SharedPreferenceUtil sharedPreferenceUtil = null;
    static SharedPreferences sharedPreferences;

    public static synchronized void saveString(String key, String value) {
        init();
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key, value);
        editor.apply();
    }

    public static synchronized void saveLong(String key, long value) {
        init();
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putLong(key, value);
        editor.apply();
    }

    public static synchronized void saveInt(String key, int value) {
        init();
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(key, value);
        editor.apply();
    }


    SharedPreferenceUtil() {
        sharedPreferences = YcyApplication.getApp().getSharedPreferences(MD5Util.md5(YcyApplication.getApp().getPackageName()), YcyApplication.getApp().getApplicationContext().MODE_PRIVATE);

    }

    static SharedPreferenceUtil init() {
        if (sharedPreferenceUtil == null) {
            synchronized (SharedPreferenceUtil.class) {
                if (sharedPreferenceUtil == null) {
                    sharedPreferenceUtil = new SharedPreferenceUtil();
                }
            }
        }
        return sharedPreferenceUtil;
    }
}
