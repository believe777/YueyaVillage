package ycy.ccyy.yueyavillage.mvp.contract;

import java.util.List;

import io.reactivex.Observable;
import ycy.ccyy.yueyavillage.base.AbstractView;
import ycy.ccyy.yueyavillage.bean.PageInfoBean;

public interface MainContract {
    interface View extends AbstractView {
        void requestPagesInfoSuccess(List<PageInfoBean> response);

        void requestPagesInfoFailed(String msg);
    }

    interface Presenter {
        void getPagesInfo();
    }

    interface Module {
        Observable<List<PageInfoBean>> getPagesInfo();
    }
}
