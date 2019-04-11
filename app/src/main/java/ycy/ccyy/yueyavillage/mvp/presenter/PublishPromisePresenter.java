package ycy.ccyy.yueyavillage.mvp.presenter;

import javax.inject.Inject;

import ycy.ccyy.yueyavillage.base.BasePresenter;
import ycy.ccyy.yueyavillage.mvp.contract.PublishPromiseContract;
import ycy.ccyy.yueyavillage.mvp.module.PublishPromiseModule;

public class PublishPromisePresenter extends BasePresenter<PublishPromiseContract.View> implements PublishPromiseContract.Presenter {
    private PublishPromiseContract.Module module;
    @Inject
    public PublishPromisePresenter() {
        module = new PublishPromiseModule();
    }

    @Override
    public void publishPresenter(String message) {
        mView.onRequestFailed("接口未调通");
    }
}
