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

    public static synchronized void saveBoolean(String key, boolean value) {
        init();
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(key, value);
        editor.apply();
    }

    public static synchronized void saveFloat(String key, float value) {
        init();
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putFloat(key, value);
        editor.apply();
    }

    //----------------------------------------------------------------------------
    public static synchronized String getString(String key, String defaultValue) {
        init();
        return sharedPreferences.getString(key, defaultValue);
    }

    public static synchronized Integer getInt(String key, int defaultValue) {
        init();
        return sharedPreferences.getInt(key, defaultValue);
    }

    public static synchronized boolean getBoolean(String key, boolean defaultValue) {
        init();
        return sharedPreferences.getBoolean(key, defaultValue);
    }

    public static synchronized float getFloat(String key, float defaultValue) {
        init();
        return sharedPreferences.getFloat(key, defaultValue);
    }

    public static synchronized long getLong(String key, long defaultValue) {
        init();
        return sharedPreferences.getLong(key, defaultValue);
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
