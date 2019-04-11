package ycy.ccyy.yueyavillage.mvp.contract;


import java.util.HashMap;

import io.reactivex.Observable;
import ycy.ccyy.yueyavillage.base.AbstractView;
import ycy.ccyy.yueyavillage.bean.CollectionDetailBean;

public interface MyCollectionDetailContract {
    interface View extends AbstractView {
        void onRequestSuccess(CollectionDetailBean response);

        void onRequestFailed(String msg);
    }

    interface Module {
        Observable<CollectionDetailBean> queryCollectionDetail(HashMap map);
    }

    interface Presenter {
        void queryCollectionDetail(int id);
    }
}
