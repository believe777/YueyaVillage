package ycy.ccyy.yueyavillage.mvp.module;

import java.util.HashMap;

import io.reactivex.Observable;
import ycy.ccyy.yueyavillage.bean.ResponseBase;
import ycy.ccyy.yueyavillage.mvp.contract.PublishContract;

public class PublishModule implements PublishContract.Module {
    @Override
    public Observable<ResponseBase> publish(HashMap map) {
        return null;
    }
}
