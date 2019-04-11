package ycy.ccyy.yueyavillage.mvp.contract;

import java.util.List;

import io.reactivex.Observable;
import ycy.ccyy.yueyavillage.base.AbstractView;
import ycy.ccyy.yueyavillage.bean.TabPagesInfoBean;

public interface HomeContract {
    interface View extends AbstractView {
        void tabPagesInfo(List<TabPagesInfoBean> response);
    }

    interface Presenter {
        void getTabPages();
    }

    interface Module {
        Observable<List<TabPagesInfoBean>> getTabPages();
    }
}
