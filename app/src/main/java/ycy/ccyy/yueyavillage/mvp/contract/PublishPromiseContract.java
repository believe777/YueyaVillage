package ycy.ccyy.yueyavillage.mvp.contract;

import java.util.HashMap;

import io.reactivex.Observable;
import ycy.ccyy.yueyavillage.base.AbstractView;
import ycy.ccyy.yueyavillage.bean.ResponseBase;

public interface PublishPromiseContract {
    interface View extends AbstractView {
        void onRequestSuccess(ResponseBase responseBase);

        void onRequestFailed(String msg);
    }

    interface Module {
        Observable<ResponseBase> publishPromise(HashMap map);
    }

    interface Presenter {
        void publishPresenter(String message);
    }
}
