package ycy.ccyy.yueyavillage.base;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleOwner;

import com.uber.autodispose.AutoDispose;
import com.uber.autodispose.AutoDisposeConverter;
import com.uber.autodispose.android.lifecycle.AndroidLifecycleScopeProvider;

public abstract class MvpFragment<T extends BasePresenter> extends BaseFragment implements BaseView {
    protected T presenter;

    @Override
    public void showToast(String text) {

    }

    @Override
    public void showDialog(String text) {

    }

    @Override
    public void onDestroy() {
        if (presenter != null) {
            presenter.onDetach();
        }
        super.onDestroy();
    }

    @Override
    public AutoDisposeConverter<T> bindAutoDispose() {
        return AutoDispose.autoDisposable(AndroidLifecycleScopeProvider
                .from((LifecycleOwner) this, Lifecycle.Event.ON_DESTROY));
    }
}
