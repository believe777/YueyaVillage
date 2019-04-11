package ycy.ccyy.yueyavillage.mvp.presenter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.BiFunction;
import ycy.ccyy.yueyavillage.base.BasePresenter;
import ycy.ccyy.yueyavillage.bean.SmallYellowPacketBean;
import ycy.ccyy.yueyavillage.mvp.contract.SmallYellowPacketContract;
import ycy.ccyy.yueyavillage.mvp.module.SmallYellowPacketModel;
import ycy.ccyy.yueyavillage.util.RxUtil;

public class SmallYellowPacketPresent extends BasePresenter<SmallYellowPacketContract.View> implements SmallYellowPacketContract.Presenter {
    private SmallYellowPacketContract.Module module;

    @Inject
    public SmallYellowPacketPresent() {
        module = new SmallYellowPacketModel();
    }

    @Override
    public void querySmallYellowPacket() {
        HashMap collection = new HashMap();
        collection.put("type", 0);
        HashMap unCollection = new HashMap();
        unCollection.put("type", 1);
        module.querySmallYellowPacket(collection)
                .zipWith(module.querySmallYellowPacket(unCollection), new BiFunction<List<SmallYellowPacketBean>, List<SmallYellowPacketBean>, List<List<SmallYellowPacketBean>>>() {
                    @Override
                    public List<List<SmallYellowPacketBean>> apply(List<SmallYellowPacketBean> smallYellowPacketBeans, List<SmallYellowPacketBean> smallYellowPacketBeans2) throws Exception {
                        List<List<SmallYellowPacketBean>> viewDateList = new ArrayList<>();
                        viewDateList.add(smallYellowPacketBeans);
                        viewDateList.add(smallYellowPacketBeans2);
                        return viewDateList;
                    }
                }).compose(RxUtil.<List<List<SmallYellowPacketBean>>>observerToMainScheduler())
                .subscribe(new Observer<List<List<SmallYellowPacketBean>>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(List<List<SmallYellowPacketBean>> lists) {
                        mView.onRequestSuccess(lists);
                    }

                    @Override
                    public void onError(Throwable e) {
                        mView.onRequestFailed(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
