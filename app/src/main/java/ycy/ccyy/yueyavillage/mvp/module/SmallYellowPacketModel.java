package ycy.ccyy.yueyavillage.mvp.module;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import ycy.ccyy.yueyavillage.bean.SmallYellowPacketBean;
import ycy.ccyy.yueyavillage.mvp.contract.SmallYellowPacketContract;

public class SmallYellowPacketModel implements SmallYellowPacketContract.Module {
    @Override
    public Observable<List<SmallYellowPacketBean>> querySmallYellowPacket(final HashMap map) {
        return Observable.create(new ObservableOnSubscribe<List<SmallYellowPacketBean>>() {
            @Override
            public void subscribe(ObservableEmitter<List<SmallYellowPacketBean>> emitter) throws Exception {
                List<SmallYellowPacketBean> list = new ArrayList<>();
                int type = (int) map.get("type");
                if(type == 0) {
                    for (int i = 0; i < 6; i++) {
                        SmallYellowPacketBean smallYellowPacketBean = new SmallYellowPacketBean();
                        smallYellowPacketBean.collection = 0;
                        smallYellowPacketBean.id = i;
                        smallYellowPacketBean.publishName = "rua";
                        smallYellowPacketBean.publishName = "我的女鹅赖小七，亲亲抱抱举高高✌️";
                        smallYellowPacketBean.date = "2019-3-29";
                        smallYellowPacketBean.icon = "https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=160741862,2569068781&fm=26&gp=0.jpg";
                        smallYellowPacketBean.publishIcon = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1554465954&di=ebd2385adf342df4220e322b52d4b054&imgtype=jpg&er=1&src=http%3A%2F%2Fimgcache.dealmoon.com%2Ffsvr.dealmoon.com%2Favatar%2F1ac%2F459%2Fcfe%2F005%2F355%2F9d2%2F20a%2Fa79%2F006%2F7ed%2Fca.jpg_200_200_2_0a2f.jpg";
                        list.add(smallYellowPacketBean);
                    }
                } else {
                    for (int i = 0; i < 6; i++) {
                        SmallYellowPacketBean smallYellowPacketBean = new SmallYellowPacketBean();
                        smallYellowPacketBean.collection = 1;
                        smallYellowPacketBean.id = i;
                        smallYellowPacketBean.publishName = "rua";
                        smallYellowPacketBean.publishName = "我的女鹅赖小七，亲亲抱抱举高高✌️";
                        smallYellowPacketBean.date = "2019-3-29";
                        smallYellowPacketBean.icon = "https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=160741862,2569068781&fm=26&gp=0.jpg";
                        smallYellowPacketBean.publishIcon = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1554465954&di=ebd2385adf342df4220e322b52d4b054&imgtype=jpg&er=1&src=http%3A%2F%2Fimgcache.dealmoon.com%2Ffsvr.dealmoon.com%2Favatar%2F1ac%2F459%2Fcfe%2F005%2F355%2F9d2%2F20a%2Fa79%2F006%2F7ed%2Fca.jpg_200_200_2_0a2f.jpg";
                        list.add(smallYellowPacketBean);
                    }
                }
                emitter.onNext(list);
                emitter.onComplete();
            }
        });
    }
}
