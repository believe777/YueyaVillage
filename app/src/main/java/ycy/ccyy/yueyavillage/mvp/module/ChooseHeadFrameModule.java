package ycy.ccyy.yueyavillage.mvp.module;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import ycy.ccyy.yueyavillage.R;
import ycy.ccyy.yueyavillage.adapter.BorderInfoBean;
import ycy.ccyy.yueyavillage.mvp.contract.ChooseHeadFrameContract;
import ycy.ccyy.yueyavillage.mvp.view.activity.ChooseHeadFrameActivity;


public class ChooseHeadFrameModule implements ChooseHeadFrameContract.Module {
    @Override
    public Observable<List<BorderInfoBean>> getBordersInfo() {
        return Observable.create(new ObservableOnSubscribe<List<BorderInfoBean>>() {
            @Override
            public void subscribe(ObservableEmitter<List<BorderInfoBean>> emitter) throws Exception {
                 emitter.onNext(initBorderList());
                 emitter.onComplete();
            }
        });
    }

    private List<BorderInfoBean> initBorderList() {
        List<BorderInfoBean> borderInfoBeanList = new ArrayList<>();
        borderInfoBeanList.add(initBorder(ChooseHeadFrameActivity.BORDER_DOUBAN, "res:///" + R.mipmap.border_douban, 10));
        borderInfoBeanList.add(initBorder(ChooseHeadFrameActivity.BORDER_TIEBA, "res:///" + R.mipmap.border_tieba, 15));
        borderInfoBeanList.add(initBorder(ChooseHeadFrameActivity.BORDER_HUPU, "res:///" + R.mipmap.border_hupu, 20));
        borderInfoBeanList.add(initBorder(ChooseHeadFrameActivity.BORDER_DOKI, "res:///" + R.mipmap.border_doki, 25));
        borderInfoBeanList.add(initBorder(ChooseHeadFrameActivity.BORDER_WEIBO, "res:///" + R.mipmap.border_weibo, 30));
        borderInfoBeanList.add(initBorder(ChooseHeadFrameActivity.BORDER_NGA, "res:///" + R.mipmap.border_nga, 35));
        borderInfoBeanList.add(initBorder(ChooseHeadFrameActivity.BORDER_BILIBILI, "res:///" + R.mipmap.border_bilibili, 40));
        borderInfoBeanList.add(initBorder(ChooseHeadFrameActivity.BORDER_ZHIHU, "res:///" + R.mipmap.border_zhihu, 45));
        return borderInfoBeanList;
    }

    private BorderInfoBean initBorder(int borderType, String borderResource, int useCount) {
        BorderInfoBean borderInfoBean = new BorderInfoBean();
        borderInfoBean.borderType = borderType;
        borderInfoBean.borderResource = borderResource;
        borderInfoBean.useCount = useCount;
        return borderInfoBean;
    }
}
