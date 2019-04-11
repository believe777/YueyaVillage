package ycy.ccyy.yueyavillage.mvp.presenter;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import ycy.ccyy.yueyavillage.base.BasePresenter;
import ycy.ccyy.yueyavillage.bean.TabPagesInfoBean;
import ycy.ccyy.yueyavillage.mvp.contract.HomeContract;
import ycy.ccyy.yueyavillage.mvp.module.HomeModule;
import ycy.ccyy.yueyavillage.util.RxUtil;


public class HomePresenter extends BasePresenter<HomeContract.View> implements HomeContract.Presenter {
    private HomeContract.Module module;

    @Inject
    protected HomePresenter() {
        module = new HomeModule();
    }

    @Override
    public void getTabPages() {
        module.getTabPages()
                .compose(RxUtil.<List<TabPagesInfoBean>>observerToMainScheduler())
                .subscribe(new Observer<List<TabPagesInfoBean>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(List<TabPagesInfoBean> tabPagesInfoBeans) {
                        mView.tabPagesInfo(tabPagesInfoBeans);
                    }

                    @Override
                    public void onError(Throwable e) {
                        // TODO: 2019/4/11  失败加载默认模块
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
