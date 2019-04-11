package ycy.ccyy.yueyavillage.base;

public interface AbstractPresenter<V extends AbstractView> {
    void onAttach(V mView);

    void onDetach();
}
