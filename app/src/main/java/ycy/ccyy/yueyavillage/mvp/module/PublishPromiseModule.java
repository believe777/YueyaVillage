package ycy.ccyy.yueyavillage.mvp.module;

import java.util.HashMap;

import io.reactivex.Observable;
import ycy.ccyy.yueyavillage.bean.ResponseBase;
import ycy.ccyy.yueyavillage.mvp.contract.PublishPromiseContract;

public class PublishPromiseModule implements PublishPromiseContract.Module {
    @Override
    public Observable<ResponseBase> publishPromise(HashMap map) {
        return null;
    }
}
