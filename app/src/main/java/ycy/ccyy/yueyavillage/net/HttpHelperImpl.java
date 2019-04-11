package ycy.ccyy.yueyavillage.net;

import java.util.HashMap;

import javax.inject.Inject;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import ycy.ccyy.yueyavillage.bean.ResponseBase;

public class HttpHelperImpl implements HttpHelper {
    @Inject
    public HttpHelperImpl(){

    }

    @Override
    public Observable<ResponseBase> regist(HashMap<String, Object> map) {
        return NetControl.getInstance().getApi().regist(map);
    }

    @Override
    public Observable<ResponseBase> upload(RequestBody requestBody) {
        return NetControl.getInstance().getApi().upload(requestBody);
    }
}
