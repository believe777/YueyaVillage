package ycy.ccyy.yueyavillage.mvp.presenter;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import ycy.ccyy.yueyavillage.base.BasePresenter;
import ycy.ccyy.yueyavillage.bean.PromisePoolBean;
import ycy.ccyy.yueyavillage.mvp.contract.PromisePoolContract;
import ycy.ccyy.yueyavillage.mvp.module.PromisePoolModule;
import ycy.ccyy.yueyavillage.util.RxUtil;

public class PromisePoolPresenter extends BasePresenter<PromisePoolContract.View> implements PromisePoolContract.Presenterr {
    private PromisePoolContract.Module module;
    @Inject
    public PromisePoolPresenter() {
        module = new PromisePoolModule();
    }

    @Override
    public void queryPromisePool() {
        module.queryPromisePool()
                .compose(RxUtil.<PromisePoolBean>observerToMainScheduler())
                .subscribe(new Observer<PromisePoolBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(PromisePoolBean promisePoolBean) {
                        mView.onRequestSuccess(promisePoolBean);
                    }

                    @Override
                    public void onError(Throwable e) {
                        mView.onRequestFailed(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
