package ycy.ccyy.yueyavillage.mvp.module;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import ycy.ccyy.yueyavillage.R;
import ycy.ccyy.yueyavillage.bean.WorkBean;
import ycy.ccyy.yueyavillage.mvp.contract.MyWorkContract;

public class MyWorkModule implements MyWorkContract.Module {
    @Override
    public Observable<List<WorkBean>> queryWorkList() {
        return Observable.create(new ObservableOnSubscribe<List<WorkBean>>() {
            @Override
            public void subscribe(ObservableEmitter<List<WorkBean>> emitter) throws Exception {
                List<WorkBean> list = new ArrayList<>();
                for (int i = 0; i < 6; i++) {
                    WorkBean bean = new WorkBean();
                    bean.id = i;
                    bean.title = "我的老婆超越酱，亲亲抱抱举高高(づ￣3￣)づ╭❤～️";
                    bean.date = "2019-3-27";
                    bean.icon = "res:///" + R.mipmap.work_list_icon;
                    list.add(bean);
                }
                emitter.onNext(list);
                emitter.onComplete();
            }
        });
    }
}
