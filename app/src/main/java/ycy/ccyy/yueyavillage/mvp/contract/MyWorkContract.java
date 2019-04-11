package ycy.ccyy.yueyavillage.mvp.contract;

import java.util.List;

import io.reactivex.Observable;
import ycy.ccyy.yueyavillage.base.AbstractView;
import ycy.ccyy.yueyavillage.bean.WorkBean;

public interface MyWorkContract {
    interface View extends AbstractView {
        void onRequestSuccess(List<WorkBean> response);

        void onRequestFailed(String msg);
    }

    interface Module {
        Observable<List<WorkBean>> queryWorkList();
    }

    interface Presenter {
        void queryWorkList();
    }
}
