package ycy.ccyy.yueyavillage.mvp.contract;

import java.util.List;

import io.reactivex.Observable;
import ycy.ccyy.yueyavillage.base.AbstractView;
import ycy.ccyy.yueyavillage.bean.CollectionBean;

public interface MyCollectionContract {
    interface View extends AbstractView {
        void onRequestSuccess(List<CollectionBean> bean);

        void onRequestFailed(String msg);
    }

    interface Module {
        Observable<List<CollectionBean>> queryCollectionList();
    }

    interface Presenter {
        void queryCollectionList();
    }
}
