package ycy.ccyy.yueyavillage.mvp.module;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import ycy.ccyy.yueyavillage.R;
import ycy.ccyy.yueyavillage.bean.PageInfoBean;
import ycy.ccyy.yueyavillage.mvp.contract.MainContract;
import ycy.ccyy.yueyavillage.mvp.view.activity.HomeActivity;

public class MainModule implements MainContract.Module {
    @Override
    public Observable<List<PageInfoBean>> getPagesInfo() {
        return Observable.create(new ObservableOnSubscribe<List<PageInfoBean>>() {
            @Override
            public void subscribe(ObservableEmitter<List<PageInfoBean>> emitter) throws Exception {
                emitter.onNext(initPageLogo());
                emitter.onComplete();
            }
        });
    }

    private List<PageInfoBean> initPageLogo() {
        List<PageInfoBean> pageInfoBeanList = new ArrayList<>();
        pageInfoBeanList.add(initPage(true, HomeActivity.PAGE_AMUSEMENT_PARK, "res:///" + R.mipmap.logo_amusement_park, "游乐园", 10));
        pageInfoBeanList.add(initPage(false, -1, "res:///" + R.mipmap.logo_log_cabin, "小木屋", 0));
        pageInfoBeanList.add(initPage(true, HomeActivity.PAGE_SMALL_YELLOW_PACKET, "res:///" + R.mipmap.logo_small_yellow_packet, "小黄包", 15));
        pageInfoBeanList.add(initPage(false, -1, "res:///" + R.mipmap.logo_yueya_bridge, "月牙桥", 0));
        pageInfoBeanList.add(initPage(true, HomeActivity.PAGE_PROMISE_POOL, "res:///" + R.mipmap.logo_promise_pool, "许愿池", 20));
        pageInfoBeanList.add(initPage(false, -1, "res:///" + R.mipmap.logo_bulletin, "布告栏", 0));
        return pageInfoBeanList;
    }

    private PageInfoBean initPage(boolean canUse, int jumpUrl, String imageResource, String pageName, int pageUse) {
        PageInfoBean pageInfoBean = new PageInfoBean();
        pageInfoBean.canUse = canUse;
        pageInfoBean.jumpUrl = jumpUrl;
        pageInfoBean.imageResource = imageResource;
        pageInfoBean.pageName = pageName;
        pageInfoBean.pageUse = pageUse;
        return pageInfoBean;
    }
}
