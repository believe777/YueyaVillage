package ycy.ccyy.yueyavillage.mvp.module;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import ycy.ccyy.yueyavillage.R;
import ycy.ccyy.yueyavillage.bean.TabPagesInfoBean;
import ycy.ccyy.yueyavillage.mvp.contract.HomeContract;
import ycy.ccyy.yueyavillage.mvp.view.activity.HomeActivity;

public class HomeModule implements HomeContract.Module {
    @Override
    public Observable<List<TabPagesInfoBean>> getTabPages() {
        return Observable.create(new ObservableOnSubscribe<List<TabPagesInfoBean>>() {
            @Override
            public void subscribe(ObservableEmitter<List<TabPagesInfoBean>> emitter) throws Exception {
                List<TabPagesInfoBean> list = new ArrayList<>();
                list.add(initTabPage("res:///" + R.mipmap.ic_tab_amusement_park, "游乐园", HomeActivity.PAGE_AMUSEMENT_PARK));
                list.add(initTabPage("res:///" + R.mipmap.ic_tab_small_yellow_packet, "小黄包", HomeActivity.PAGE_SMALL_YELLOW_PACKET));
                list.add(initTabPage("res:///" + R.mipmap.ic_tab_promise_pool, "许愿池", HomeActivity.PAGE_PROMISE_POOL));
                list.add(initTabPage("res:///" + R.mipmap.ic_tab_yueyavillage, "月芽村", HomeActivity.PAGE_YUEYA_VILLAGE));
                emitter.onNext(list);
                emitter.onComplete();
            }
        });
    }

    private TabPagesInfoBean initTabPage(String resource, String name, int pageType) {
        TabPagesInfoBean tabPagesInfoBean = new TabPagesInfoBean();
        tabPagesInfoBean.pageType = pageType;
        tabPagesInfoBean.tabIcon = resource;
        tabPagesInfoBean.tabName = name;
        return tabPagesInfoBean;
    }
}
