package ycy.ccyy.yueyavillage.mvp.presenter;

import java.util.HashMap;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import ycy.ccyy.yueyavillage.base.BasePresenter;
import ycy.ccyy.yueyavillage.bean.WorkDetailBean;
import ycy.ccyy.yueyavillage.mvp.contract.MyWorkDetailContract;
import ycy.ccyy.yueyavillage.mvp.module.MyWorkDetailModule;
import ycy.ccyy.yueyavillage.util.RxUtil;

public class MyWorkDetailPresenter extends BasePresenter<MyWorkDetailContract.View> implements MyWorkDetailContract.Presenter {
    private MyWorkDetailContract.Module module;
    @Inject
    public MyWorkDetailPresenter() {
        module = new MyWorkDetailModule();
    }

    @Override
    public void queryWorkDetail(int id) {
        HashMap map = new HashMap();
        map.put("id", id);
        module.queryWorkDetail(map)
                .compose(RxUtil.<WorkDetailBean>observerToMainScheduler())
                .subscribe(new Observer<WorkDetailBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(WorkDetailBean workDetailBean) {
                        mView.onRequestSuccess(workDetailBean);
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
