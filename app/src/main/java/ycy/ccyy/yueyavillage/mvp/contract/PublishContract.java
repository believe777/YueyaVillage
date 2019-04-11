package ycy.ccyy.yueyavillage.mvp.contract;

import java.util.HashMap;
import java.util.List;

import io.reactivex.Observable;
import ycy.ccyy.yueyavillage.base.AbstractView;
import ycy.ccyy.yueyavillage.bean.ResponseBase;

public interface PublishContract {
    interface View extends AbstractView {
        void pushlishSuccess();

        void publishFailed(String msg);
    }

    interface Presenter {
        void publish(String msg, List<String> imagesPath);
    }

    interface Module {
        Observable<ResponseBase> publish(HashMap map);
    }
}
