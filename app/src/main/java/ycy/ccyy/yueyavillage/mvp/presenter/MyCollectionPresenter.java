package ycy.ccyy.yueyavillage.mvp.presenter;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import ycy.ccyy.yueyavillage.base.BasePresenter;
import ycy.ccyy.yueyavillage.bean.CollectionBean;
import ycy.ccyy.yueyavillage.mvp.contract.MyCollectionContract;
import ycy.ccyy.yueyavillage.mvp.module.MyCollectionModule;
import ycy.ccyy.yueyavillage.util.RxUtil;

public class MyCollectionPresenter extends BasePresenter<MyCollectionContract.View> implements MyCollectionContract.Presenter {
    private MyCollectionContract.Module module;

    @Inject
    public MyCollectionPresenter() {
        module = new MyCollectionModule();
    }

    @Override
    public void queryCollectionList() {
        module.queryCollectionList()
                .compose(RxUtil.<List<CollectionBean>>observerToMainScheduler())
                .subscribe(new Observer<List<CollectionBean>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(List<CollectionBean> collectionBeans) {
                        mView.onRequestSuccess(collectionBeans);
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
