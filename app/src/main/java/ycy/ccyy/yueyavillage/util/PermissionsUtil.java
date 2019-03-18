package ycy.ccyy.yueyavillage.util;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

import ycy.ccyy.yueyavillage.YcyApplication;

//权限工具类
public class PermissionsUtil {
    //应用启动必须权限
    private static final String[] NecessaryPermissions = {
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.READ_PHONE_STATE
    };
    public static final int NecessaryPermissionsCode = 1000;

    public static boolean hadNecessaryPermissions() {
        return checkPermissions(NecessaryPermissions);
    }

    //多个权限检测
    public static boolean checkPermissions(String[] permissions) {
        if (permissions != null) {
            for (int i = 0; i < permissions.length; i++) {
                if (!checkPermission(permissions[i])) {
                    return false;
                }
            }
        }
        return true;
    }

    //单个权限检测
    public static boolean checkPermission(String psermission) {
        return ContextCompat.checkSelfPermission(YcyApplication.getApp().getApplicationContext(), psermission) == PackageManager.PERMISSION_GRANTED;
    }

    //授权框
    public static void requestPermissions(Activity activity, String[] permissions, int requestCode) {
        ActivityCompat.requestPermissions(activity, permissions, requestCode);
    }

    public static void requestNecessaryPermissions(Activity activity) {
        requestPermissions(activity, NecessaryPermissions, NecessaryPermissionsCode);
    }
}
