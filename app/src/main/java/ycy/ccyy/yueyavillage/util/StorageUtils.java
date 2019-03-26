package ycy.ccyy.yueyavillage.util;

import android.os.Environment;

import java.io.File;

//文件缓存类
// TODO: 2019/3/15 需要增加应用删除时，将相关缓存文件同步删除；
public class StorageUtils {
    public static final String ROOT_DIR = "YueyaVillage";
    public static final String CACHE = "cache";
    public static final String IMAGE = "image";

    //根目录
    private static File getRootDir() {
        File file = null;
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            file = new File(Environment.getExternalStorageDirectory(), ROOT_DIR);
            if (!file.exists()) {
                file.mkdirs();
            }
        }
        return file;
    }

    public static File getCacheDir(FILE_TYPE fileType) {
        File file = null;
        switch (fileType) {
            case ROOT:
                file = getRootDir();
                break;
            case CACHE:
                file = new File(getRootDir(), CACHE);
                break;
        }
        if (file != null && !file.exists()) {
            file.mkdir();
        }
        return file;
    }

    public enum FILE_TYPE {
        ROOT,
        CACHE
    }


}
