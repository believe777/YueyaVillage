package ycy.ccyy.yueyavillage.mvp.presenter;

import android.net.Uri;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import ycy.ccyy.yueyavillage.base.BasePresenter;
import ycy.ccyy.yueyavillage.bean.ImageSelectorBean;
import ycy.ccyy.yueyavillage.mvp.contract.ImageSelectorContract;
import ycy.ccyy.yueyavillage.mvp.module.ImageSelectorModule;
import ycy.ccyy.yueyavillage.util.CollectionUtil;
import ycy.ccyy.yueyavillage.util.LogUtil;
import ycy.ccyy.yueyavillage.util.RxUtil;

public class ImageSelectorPresenter extends BasePresenter<ImageSelectorContract.View> implements ImageSelectorContract.Presenter {
    private static final int MAX_SELECTED = 9;
    private ImageSelectorContract.Module module;
    List<ImageSelectorBean> imagesPath = new ArrayList<>();
    private int selectedNumber;
    private ArrayList<Integer> selectedIndex = new ArrayList<>();

    @Inject
    public ImageSelectorPresenter() {
        module = new ImageSelectorModule();
    }

    @Override
    public void getLocalImages() {
        module.getLocalImages()
                .compose(RxUtil.<List<ImageSelectorBean>>observerToMainScheduler())
                .subscribe(new Observer<List<ImageSelectorBean>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(List<ImageSelectorBean> imageSelectorBeans) {
                        if (!CollectionUtil.isEmpty(imageSelectorBeans)) {
                            imagesPath.addAll(imageSelectorBeans);
                        }
                        mView.getLocalImages(imageSelectorBeans);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void onImageSelected(int position) {
        if (position > -1) {
            ImageSelectorBean bean = imagesPath.get(position);
            if (bean.isSelected) {
                selectedNumber--;
                for (Integer index : selectedIndex) {
                    ImageSelectorBean changeBean = imagesPath.get(index);
                    LogUtil.d("changeBean index " + changeBean.index);
                    if (changeBean.index > bean.index) {
                        changeBean.index--;
                    }
                }
                selectedIndex.remove(position);
                bean.isSelected = false;
                bean.index = 0;
            } else {
                if (selectedNumber >= MAX_SELECTED) {
                    //showToast("最多选择9张照片");
                    return;
                }
                selectedNumber++;
                selectedIndex.add(position);
                bean.isSelected = true;
                bean.index = selectedNumber;
            }
            mView.setFinishTextStatus(selectedNumber);
        }
    }

    @Override
    public void onImagePreview(int position) {
        if (position > -1) {
            ImageSelectorBean bean = imagesPath.get(position);
            mView.previewImage(Uri.parse(bean.imagePath));
        }
    }

    @Override
    public ArrayList<String> selectedImagesPath() {
        ArrayList<String> selectedImagePath = new ArrayList<>();
        for (Integer index : selectedIndex) {
            selectedImagePath.add(imagesPath.get(index).imagePath);
        }
        return selectedImagePath;
    }
}
