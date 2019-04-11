package ycy.ccyy.yueyavillage.mvp.presenter;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import ycy.ccyy.yueyavillage.base.BasePresenter;
import ycy.ccyy.yueyavillage.bean.PageInfoBean;
import ycy.ccyy.yueyavillage.mvp.contract.MainContract;
import ycy.ccyy.yueyavillage.mvp.module.MainModule;
import ycy.ccyy.yueyavillage.util.RxUtil;

public class MainPresenter extends BasePresenter<MainContract.View> implements MainContract.Presenter {
    private MainContract.Module module;

    @Inject
    public MainPresenter() {
        module = new MainModule();
    }

    @Override
    public void getPagesInfo() {
        module.getPagesInfo()
                .compose(RxUtil.<List<PageInfoBean>>observerToMainScheduler())
                .subscribe(new Observer<List<PageInfoBean>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(List<PageInfoBean> pageInfoBeans) {
                        mView.requestPagesInfoSuccess(pageInfoBeans);
                    }

                    @Override
                    public void onError(Throwable e) {
                        mView.requestPagesInfoFailed(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
