package ycy.ccyy.yueyavillage.mvp.module;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import ycy.ccyy.yueyavillage.bean.CollectionBean;
import ycy.ccyy.yueyavillage.mvp.contract.MyCollectionContract;

public class MyCollectionModule implements MyCollectionContract.Module {
    @Override
    public Observable<List<CollectionBean>> queryCollectionList() {
        return Observable.create(new ObservableOnSubscribe<List<CollectionBean>>() {
            @Override
            public void subscribe(ObservableEmitter<List<CollectionBean>> emitter) throws Exception {
                List<CollectionBean> list = new ArrayList<>();
                for (int i = 0; i < 6; i++) {
                    CollectionBean bean = new CollectionBean();
                    bean.id = i;
                    bean.date = "2019-03-2" + i;
                    bean.icon = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1553772382451&di=d40f406a45e0c49229d550752f82522f&imgtype=0&src=http%3A%2F%2Fwx2.sinaimg.cn%2Flarge%2F0066IDMxly1fwd5axv4zkj30yi0yigqo.jpg";
                    bean.title = "我的女鹅赖小七，亲亲抱抱举高高";
                    bean.publisherName = "rua";
                    bean.publisherIcon = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1553754244814&di=40eacd3791c7f725a170826f3ab95f22&imgtype=0&src=http%3A%2F%2Fimgcache.dealmoon.com%2Ffsvr.dealmoon.com%2Favatar%2F1ac%2F459%2Fcfe%2F005%2F355%2F9d2%2F20a%2Fa79%2F006%2F7ed%2Fca.jpg_200_200_2_0a2f.jpg";
                    list.add(bean);
                }
                emitter.onNext(list);
                emitter.onComplete();
            }
        });
    }
}
