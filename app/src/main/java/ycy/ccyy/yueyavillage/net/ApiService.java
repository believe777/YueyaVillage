package ycy.ccyy.yueyavillage.net;

import java.util.HashMap;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.Body;
import retrofit2.http.POST;

//请求配置，暂不区分业务
public interface ApiService {
    @POST("user/register")
    Observable<ResponseBody> login(@Body HashMap map);

}
