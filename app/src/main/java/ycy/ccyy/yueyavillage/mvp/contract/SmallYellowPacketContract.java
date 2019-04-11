package ycy.ccyy.yueyavillage.mvp.contract;

import java.util.HashMap;
import java.util.List;

import io.reactivex.Observable;
import ycy.ccyy.yueyavillage.base.AbstractView;
import ycy.ccyy.yueyavillage.bean.SmallYellowPacketBean;

public interface SmallYellowPacketContract {
    interface View extends AbstractView {
        void onRequestSuccess(List<List<SmallYellowPacketBean>> response);

        void onRequestFailed(String msg);
    }

    interface Module {
        Observable<List<SmallYellowPacketBean>> querySmallYellowPacket(HashMap map);
    }

    interface Presenter {
        void querySmallYellowPacket();
    }
}
