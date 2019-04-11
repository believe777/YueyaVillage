package ycy.ccyy.yueyavillage.base;

import com.uber.autodispose.AutoDisposeConverter;

public interface AbstractView {
    void showToast(String text);

    void showDialog(String text);

    void showLoadingDailog();

    //防止rxjava内存泄漏
    <T> AutoDisposeConverter<T> bindAutoDispose();
}
