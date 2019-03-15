package ycy.ccyy.yueyavillage.base;

public class BasePresenter<V extends BaseView> {
    protected V mView;

    public void onAttach(V mView) {
        this.mView = mView;
    }

    public void onDetach() {
        this.mView = null;
    }

    public boolean isAttached() {
        return mView == null;
    }
}
