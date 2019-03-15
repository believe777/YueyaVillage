package ycy.ccyy.yueyavillage.base;

import com.uber.autodispose.AutoDisposeConverter;

public interface BaseView {
    void showToast(String text);

    void showDialog(String text);

    //防止rxjava内存泄漏
    <T> AutoDisposeConverter<T> bindAutoDispose();
}
