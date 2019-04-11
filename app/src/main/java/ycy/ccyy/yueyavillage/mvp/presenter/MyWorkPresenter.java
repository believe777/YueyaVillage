package ycy.ccyy.yueyavillage.mvp.presenter;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import ycy.ccyy.yueyavillage.base.BasePresenter;
import ycy.ccyy.yueyavillage.bean.WorkBean;
import ycy.ccyy.yueyavillage.mvp.contract.MyWorkContract;
import ycy.ccyy.yueyavillage.mvp.module.MyWorkModule;
import ycy.ccyy.yueyavillage.util.RxUtil;

public class MyWorkPresenter extends BasePresenter<MyWorkContract.View> implements MyWorkContract.Presenter {
    private MyWorkContract.Module module;
    @Inject
    public MyWorkPresenter() {
        module = new MyWorkModule();
    }

    @Override
    public void queryWorkList() {
        module.queryWorkList().compose(RxUtil.<List<WorkBean>>observerToMainScheduler()).subscribe(new Observer<List<WorkBean>>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(List<WorkBean> workBeans) {
                mView.onRequestSuccess(workBeans);
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
