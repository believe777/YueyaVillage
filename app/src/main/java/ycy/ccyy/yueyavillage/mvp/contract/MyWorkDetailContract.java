package ycy.ccyy.yueyavillage.mvp.contract;

import java.util.HashMap;

import io.reactivex.Observable;
import ycy.ccyy.yueyavillage.base.AbstractView;
import ycy.ccyy.yueyavillage.bean.WorkDetailBean;

public interface MyWorkDetailContract {
    interface View extends AbstractView {
        void onRequestSuccess(WorkDetailBean response);

        void onRequestFailed(String msg);
    }

    interface Module {
        Observable<WorkDetailBean> queryWorkDetail(HashMap map);
    }

    interface Presenter {
        void queryWorkDetail(int id);
    }
}
