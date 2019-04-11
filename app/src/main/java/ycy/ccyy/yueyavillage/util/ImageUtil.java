package ycy.ccyy.yueyavillage.util;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class ImageUtil {
    private static final Uri mImageUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
    private static ContentResolver mContentResolver;

    public static List<String> searchAllImage(Context context) {
        mContentResolver = context.getContentResolver();
        Cursor mCursor = mContentResolver.query(mImageUri, null,
                MediaStore.Images.Media.MIME_TYPE + "=? or "
                        + MediaStore.Images.Media.MIME_TYPE + "=?",
                new String[]{"image/jpeg", "image/png"}, MediaStore.Images.Media.DATE_MODIFIED);

        if (mCursor == null) {
            return null;
        }
        List<String> pathList = new ArrayList<>();
        while (mCursor.moveToNext()) {
            pathList.add(mCursor.getString(mCursor
                    .getColumnIndex(MediaStore.Images.Media.DATA)));
        }
        return pathList;
    }

    public static Bitmap loadLocalImage(String url) {
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(url);
            return BitmapFactory.decodeStream(fis);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Bitmap loadLocalImage(Context context, Uri uri) {
        try {
            InputStream is = context.getContentResolver().openInputStream(uri);
            return BitmapFactory.decodeStream(is);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
