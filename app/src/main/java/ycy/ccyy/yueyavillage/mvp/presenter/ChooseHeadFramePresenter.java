package ycy.ccyy.yueyavillage.mvp.presenter;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import ycy.ccyy.yueyavillage.adapter.BorderInfoBean;
import ycy.ccyy.yueyavillage.base.BasePresenter;
import ycy.ccyy.yueyavillage.mvp.contract.ChooseHeadFrameContract;
import ycy.ccyy.yueyavillage.mvp.module.ChooseHeadFrameModule;
import ycy.ccyy.yueyavillage.util.RxUtil;

public class ChooseHeadFramePresenter extends BasePresenter<ChooseHeadFrameContract.View> implements ChooseHeadFrameContract.Presenter {
    private ChooseHeadFrameContract.Module module;

    @Inject
    public ChooseHeadFramePresenter() {
        module = new ChooseHeadFrameModule();
    }

    @Override
    public void getBordersInfo() {
        module.getBordersInfo()
                .compose(RxUtil.<List<BorderInfoBean>>observerToMainScheduler())
                .subscribe(new Observer<List<BorderInfoBean>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(List<BorderInfoBean> list) {
                        mView.requestBordersInfoSuccess(list);
                    }

                    @Override
                    public void onError(Throwable e) {
                        mView.requestBordersInfoFailed(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
