package ycy.ccyy.yueyavillage.mvp.contract;

import java.util.List;

import io.reactivex.Observable;
import ycy.ccyy.yueyavillage.adapter.BorderInfoBean;
import ycy.ccyy.yueyavillage.base.AbstractView;

public interface ChooseHeadFrameContract {
    interface View extends AbstractView {
        void requestBordersInfoSuccess(List<BorderInfoBean> response);

        void requestBordersInfoFailed(String msg);
    }

    interface Presenter {
        void getBordersInfo();
    }

    interface Module {
        Observable<List<BorderInfoBean>> getBordersInfo();
    }
}
