package ycy.ccyy.yueyavillage.mvp.contract;

import android.net.Uri;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import ycy.ccyy.yueyavillage.base.AbstractView;
import ycy.ccyy.yueyavillage.bean.ImageSelectorBean;

public interface ImageSelectorContract {
    interface View extends AbstractView {
        void getLocalImages(List<ImageSelectorBean> imagesPath);

        void setFinishTextStatus(int selectedNum);

        void previewImage(Uri imagePath);
    }

    interface Presenter {
        void getLocalImages();

        void onImageSelected(int position);

        void onImagePreview(int posiion);
        ArrayList<String> selectedImagesPath();
    }

    interface Module {
        Observable<List<ImageSelectorBean>> getLocalImages();
    }
}
