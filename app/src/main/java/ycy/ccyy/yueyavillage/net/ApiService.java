package ycy.ccyy.yueyavillage.net;

import java.util.HashMap;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import ycy.ccyy.yueyavillage.bean.ResponseBase;

//请求配置，暂不区分业务
public interface ApiService {
    @POST(HttpContent.REGIST)
    Observable<ResponseBase> regist(@Body HashMap<String, Object> map);

    @POST(HttpContent.UPLOADByBinary)
    Observable<ResponseBase> upload(@Body RequestBody requestBody);
}
