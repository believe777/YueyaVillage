package ycy.ccyy.yueyavillage.net;

import java.util.HashMap;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import ycy.ccyy.yueyavillage.bean.ResponseBase;

public interface HttpHelper {
    //用户注册
    Observable<ResponseBase> regist(HashMap<String, Object> map);
    //单图片上传
    Observable<ResponseBase> upload(RequestBody requestBody);
}
