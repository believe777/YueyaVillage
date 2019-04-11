package ycy.ccyy.yueyavillage.mvp.presenter;

import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import ycy.ccyy.yueyavillage.base.BasePresenter;
import ycy.ccyy.yueyavillage.bean.ResponseBase;
import ycy.ccyy.yueyavillage.mvp.contract.PublishContract;
import ycy.ccyy.yueyavillage.mvp.module.PublishModule;
import ycy.ccyy.yueyavillage.net.HttpContent;
import ycy.ccyy.yueyavillage.util.RxUtil;

public class PublishPresenter extends BasePresenter<PublishContract.View> implements PublishContract.Presenter {
    private PublishContract.Module module;

    @Inject
    public PublishPresenter() {
        module = new PublishModule();
    }

    @Override
    public void publish(String msg, List<String> imagesPath) {
        HashMap map = new HashMap();

        module.publish(map)
                .compose(RxUtil.<ResponseBase>observerToMainScheduler())
                .subscribe(new Observer<ResponseBase>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ResponseBase responseBase) {
                        if (responseBase.resultCode == HttpContent.SUCCESS) {
                            mView.pushlishSuccess();
                        } else {
                            mView.publishFailed(responseBase.error);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        mView.publishFailed(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
