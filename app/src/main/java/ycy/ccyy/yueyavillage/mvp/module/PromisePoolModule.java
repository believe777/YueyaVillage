package ycy.ccyy.yueyavillage.mvp.module;

import java.util.ArrayList;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import ycy.ccyy.yueyavillage.bean.PromisePoolBean;
import ycy.ccyy.yueyavillage.mvp.contract.PromisePoolContract;

public class PromisePoolModule implements PromisePoolContract.Module {
    @Override
    public Observable<PromisePoolBean> queryPromisePool() {
        return Observable.create(new ObservableOnSubscribe<PromisePoolBean>() {
            @Override
            public void subscribe(ObservableEmitter<PromisePoolBean> emitter) throws Exception {
                PromisePoolBean bean = new PromisePoolBean();
                bean.message = new ArrayList<>();
                bean.message.add("我要减肥");
                bean.message.add("我要脱单");
                bean.message.add("我要赚钱");
                bean.message.add("我要娶超越");
                bean.message.add("我女鹅长高");
                bean.message.add("炒团大🔥");
                emitter.onNext(bean);
                emitter.onComplete();
            }
        });
    }
}
