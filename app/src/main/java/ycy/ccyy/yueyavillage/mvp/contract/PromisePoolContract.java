package ycy.ccyy.yueyavillage.mvp.contract;

import io.reactivex.Observable;
import ycy.ccyy.yueyavillage.base.AbstractView;
import ycy.ccyy.yueyavillage.bean.PromisePoolBean;

public interface PromisePoolContract {
    interface View extends AbstractView {
        void onRequestSuccess(PromisePoolBean response);

        void onRequestFailed(String msg);
    }

    interface Module {
        Observable<PromisePoolBean> queryPromisePool();
    }

    interface Presenterr {
        void queryPromisePool();
    }
}
