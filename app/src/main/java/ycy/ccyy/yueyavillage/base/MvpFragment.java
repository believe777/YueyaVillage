package ycy.ccyy.yueyavillage.base;

public abstract class MvpFragment<T extends BasePresenter> extends BaseFragment implements BaseView {
    protected T presenter;

    @Override
    public void onDestroy() {
        if (presenter != null) {
            presenter.onDetach();
        }
        super.onDestroy();
    }
}
