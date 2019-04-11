package ycy.ccyy.yueyavillage.mvp.module;

import java.util.HashMap;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import ycy.ccyy.yueyavillage.bean.ResponseBase;
import ycy.ccyy.yueyavillage.net.HttpHelper;

public class DataManager implements HttpHelper {
    private HttpHelper httpHelper;

    public DataManager(HttpHelper httpHelper) {
        this.httpHelper = httpHelper;
    }

    @Override
    public Observable<ResponseBase> regist(HashMap map) {
        return httpHelper.regist(map);
    }

    @Override
    public Observable<ResponseBase> upload(RequestBody requestBody) {
        return httpHelper.upload(requestBody);
    }
}
