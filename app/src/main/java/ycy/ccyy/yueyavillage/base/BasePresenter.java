package ycy.ccyy.yueyavillage.base;

import android.support.annotation.CallSuper;

public class BasePresenter<V extends AbstractView> implements AbstractPresenter<V> {
    protected V mView;

    public boolean isAttached() {
        return mView == null;
    }

    @Override
    public void onAttach(V mView) {
        this.mView = mView;
    }

    @Override
    public void onDetach() {
        this.mView = null;
    }
}
