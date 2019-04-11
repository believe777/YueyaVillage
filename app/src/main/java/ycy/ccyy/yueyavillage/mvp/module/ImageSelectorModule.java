package ycy.ccyy.yueyavillage.mvp.module;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import ycy.ccyy.yueyavillage.YcyApplication;
import ycy.ccyy.yueyavillage.bean.ImageSelectorBean;
import ycy.ccyy.yueyavillage.mvp.contract.ImageSelectorContract;

public class ImageSelectorModule implements ImageSelectorContract.Module {
    @Override
    public Observable<List<ImageSelectorBean>> getLocalImages() {
        return Observable.create(new ObservableOnSubscribe<List<ImageSelectorBean>>() {
            @Override
            public void subscribe(ObservableEmitter<List<ImageSelectorBean>> emitter) throws Exception {
                emitter.onNext(loadAllLocalImage());
                emitter.onComplete();
            }
        });
    }

    private ArrayList<ImageSelectorBean> loadAllLocalImage() {
        ArrayList<ImageSelectorBean> result = new ArrayList<>();
        Uri uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
        ContentResolver contentResolver = YcyApplication.getApp().getContentResolver();
        Cursor cursor = contentResolver.query(uri, null, null, null, null);
        if (cursor == null || cursor.getCount() <= 0) {
            return null; // 没有图片
        }
        while (cursor.moveToNext()) {
            int index = cursor
                    .getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            String path = cursor.getString(index); // 文件地址
            File file = new File(path);
            if (file.exists()) {
                ImageSelectorBean bean = new ImageSelectorBean();
                bean.imagePath = path;
                result.add(bean);
            }
        }
        return result;
    }
}
