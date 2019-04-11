package ycy.ccyy.yueyavillage.mvp.presenter;

import java.util.HashMap;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import ycy.ccyy.yueyavillage.base.BasePresenter;
import ycy.ccyy.yueyavillage.bean.CollectionDetailBean;
import ycy.ccyy.yueyavillage.mvp.contract.MyCollectionDetailContract;
import ycy.ccyy.yueyavillage.mvp.module.MyCollectionDetailModule;
import ycy.ccyy.yueyavillage.util.RxUtil;

public class MyCollectionDetailPresenter extends BasePresenter<MyCollectionDetailContract.View> implements MyCollectionDetailContract.Presenter {
    private MyCollectionDetailContract.Module module;
    @Inject
    public MyCollectionDetailPresenter() {
        module = new MyCollectionDetailModule();
    }

    @Override
    public void queryCollectionDetail(int id) {
        final HashMap map = new HashMap();
        map.put("id", id);
        module.queryCollectionDetail(map)
                .compose(RxUtil.<CollectionDetailBean>observerToMainScheduler())
                .subscribe(new Observer<CollectionDetailBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(CollectionDetailBean bean) {
                        mView.onRequestSuccess(bean);
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
