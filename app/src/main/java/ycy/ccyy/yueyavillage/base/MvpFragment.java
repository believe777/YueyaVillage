package ycy.ccyy.yueyavillage.base;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleOwner;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.uber.autodispose.AutoDispose;
import com.uber.autodispose.AutoDisposeConverter;
import com.uber.autodispose.android.lifecycle.AndroidLifecycleScopeProvider;

import javax.inject.Inject;

import dagger.android.support.AndroidSupportInjection;

public abstract class MvpFragment<T extends AbstractPresenter> extends BaseFragment implements AbstractView {
    @Inject
    protected T mPresenter;

    @Override
    public void onAttach(Context context) {
        AndroidSupportInjection.inject(this);
        super.onAttach(context);
    }

    @Override
    public void showToast(String text) {

    }

    @Override
    public void showDialog(String text) {

    }

    @Override
    public void showLoadingDailog() {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        if (mPresenter != null) {
            mPresenter.onAttach(this);
        }
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onDestroy() {
        if (mPresenter != null) {
            mPresenter.onDetach();
        }
        super.onDestroy();
    }

    @Override
    public AutoDisposeConverter<T> bindAutoDispose() {
        return AutoDispose.autoDisposable(AndroidLifecycleScopeProvider
                .from((LifecycleOwner) this, Lifecycle.Event.ON_DESTROY));
    }
}
