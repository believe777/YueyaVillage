package ycy.ccyy.yueyavillage.mvp.module;


import java.util.ArrayList;
import java.util.HashMap;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import ycy.ccyy.yueyavillage.bean.CollectionDetailBean;
import ycy.ccyy.yueyavillage.mvp.contract.MyCollectionDetailContract;

public class MyCollectionDetailModule implements MyCollectionDetailContract.Module {
    @Override
    public Observable<CollectionDetailBean> queryCollectionDetail(HashMap map) {
        return Observable.create(new ObservableOnSubscribe<CollectionDetailBean>() {
            @Override
            public void subscribe(ObservableEmitter<CollectionDetailBean> emitter) throws Exception {
                CollectionDetailBean bean = new CollectionDetailBean();
                bean.date = "2019-03-28";
                bean.publisherName = "rua";
                bean.publisherIcon = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1553754244814&di=40eacd3791c7f725a170826f3ab95f22&imgtype=0&src=http%3A%2F%2Fimgcache.dealmoon.com%2Ffsvr.dealmoon.com%2Favatar%2F1ac%2F459%2Fcfe%2F005%2F355%2F9d2%2F20a%2Fa79%2F006%2F7ed%2Fca.jpg_200_200_2_0a2f.jpg";
                bean.title = "我的女鹅赖小七，亲亲抱抱举高高✌️";
                bean.icons = new ArrayList<>();
                bean.icons.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1553754586554&di=fd85a95729b53a665391bc74106ecaef&imgtype=0&src=http%3A%2F%2Fi1.hdslb.com%2Fbfs%2Farchive%2Fc52c02181beef0424b25c387c11b17694dcb6a2a.jpg");
                bean.icons.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1553766517808&di=d4afe4448e20a2c17c2848d5611c1c57&imgtype=0&src=http%3A%2F%2Fb-ssl.duitang.com%2Fuploads%2Fitem%2F201805%2F24%2F20180524123012_jqxsp.jpg");
                bean.icons.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1553766551060&di=785e21cdcd6d1354385379edfd5a5c28&imgtype=0&src=http%3A%2F%2Fi1.hdslb.com%2Fbfs%2Farchive%2F3d742a81256c9d6a26f936ce2ef69baff011802a.jpg");
                bean.icons.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1553766583027&di=957aa12ec2000585644a17e9419f3a8c&imgtype=0&src=http%3A%2F%2Fuploadfile.redquan.com%2F2018%2F1005%2F20181005053748599.jpg");
                bean.icons.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1553766661536&di=fdf40984055ec5812645636482a3fccf&imgtype=0&src=http%3A%2F%2Fb-ssl.duitang.com%2Fuploads%2Fitem%2F201806%2F20%2F20180620143524_plpjm.jpg");
                bean.icons.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1553766684631&di=0587e697cfced4769b4592b2e6065689&imgtype=0&src=http%3A%2F%2Fi1.hdslb.com%2Fbfs%2Farchive%2Fdd4836634304061539a154f14b266a7325539d2f.jpg");
                emitter.onNext(bean);
                emitter.onComplete();
            }
        });
    }
}
