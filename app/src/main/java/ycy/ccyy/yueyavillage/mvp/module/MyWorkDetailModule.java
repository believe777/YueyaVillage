package ycy.ccyy.yueyavillage.mvp.module;

import java.util.ArrayList;
import java.util.HashMap;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import ycy.ccyy.yueyavillage.bean.WorkDetailBean;
import ycy.ccyy.yueyavillage.mvp.contract.MyWorkDetailContract;

public class MyWorkDetailModule implements MyWorkDetailContract.Module {
    @Override
    public Observable<WorkDetailBean> queryWorkDetail(HashMap map) {
        return Observable.create(new ObservableOnSubscribe<WorkDetailBean>() {
            @Override
            public void subscribe(ObservableEmitter<WorkDetailBean> emitter) throws Exception {
                WorkDetailBean workDetailBean = new WorkDetailBean();
                workDetailBean.userName = "rua";
                workDetailBean.userIcon = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1553754244814&di=40eacd3791c7f725a170826f3ab95f22&imgtype=0&src=http%3A%2F%2Fimgcache.dealmoon.com%2Ffsvr.dealmoon.com%2Favatar%2F1ac%2F459%2Fcfe%2F005%2F355%2F9d2%2F20a%2Fa79%2F006%2F7ed%2Fca.jpg_200_200_2_0a2f.jpg";
                workDetailBean.date = "2019-3-28";
                workDetailBean.title = "我的老婆超越酱，亲亲抱抱举高高(づ￣3￣)づ╭❤～️";
                workDetailBean.icons = new ArrayList<>();
                workDetailBean.icons.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1553779665182&di=d601916b4f28e0688e21223c235b7090&imgtype=0&src=http%3A%2F%2Fn.sinaimg.cn%2Fsinacn10%2F128%2Fw2048h1280%2F20180808%2Fd36e-hhkuskt6111162.jpg");
                workDetailBean.icons.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1553779702121&di=d5db9db37922689efe6adfa781a7ff67&imgtype=0&src=http%3A%2F%2F05imgmini.eastday.com%2Fmobile%2F20180930%2F20180930171344_f7d20f03893016d9c25e0b661ca3c27a_4.jpeg");
                workDetailBean.icons.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1553779739467&di=ac5d98f6e791036be8db3f5c1b9ab524&imgtype=0&src=http%3A%2F%2F05imgmini.eastday.com%2Fmobile%2F20180930%2F20180930171344_f7d20f03893016d9c25e0b661ca3c27a_6.jpeg");
                workDetailBean.icons.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1553779778665&di=506142df4b19759af3d700b759487b44&imgtype=0&src=http%3A%2F%2Fn.sinaimg.cn%2Fsinacn11%2F529%2Fw640h689%2F20181003%2Fee2e-hkrzyam6883755.jpg");
                workDetailBean.icons.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1553779837554&di=8838d090bbf43502d96419e1f3d1a566&imgtype=0&src=http%3A%2F%2Fwww.aihami.com%2Fuploads%2Fallimg%2F190328%2F166-1Z32Q12234S9.jpg");
                workDetailBean.icons.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1553779953435&di=883397d6c9c5172fa0857474a295254b&imgtype=0&src=http%3A%2F%2Fdingyue.nosdn.127.net%2FVUEC8PQzRkX26qcw44ityN9cIZVnmZHB8V9ChnBplJhu%3D1538295730385compressflag.jpg");
                emitter.onNext(workDetailBean);
                emitter.onComplete();
            }
        });
    }
}
