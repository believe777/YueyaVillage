package ycy.ccyy.yueyavillage.net;

import java.util.HashMap;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;
import ycy.ccyy.yueyavillage.bean.UserInfoBean;
import ycy.ccyy.yueyavillage.bean.WXAccessTokenBean;
import ycy.ccyy.yueyavillage.bean.WXBaseBean;
import ycy.ccyy.yueyavillage.bean.WXRefreshToken;

public interface WXService {
    //通过code获取access_token
    @GET("sns/oauth2/access_token")
    Observable<WXAccessTokenBean> getWXToken(@QueryMap HashMap map);//appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code

    //刷新或续期access_token使用
    @GET("sns/oauth2/refresh_token")
    Observable<WXRefreshToken> refreshWXToken(@QueryMap HashMap map);//appid=APPID&grant_type=refresh_token&refresh_token=REFRESH_TOKEN

    //检验授权凭证（access_token）是否有效
    @GET("sns/auth")
    Observable<WXBaseBean> checkToken(@QueryMap HashMap map);//access_token=ACCESS_TOKEN&openid=OPENID

    //获取用户个人信息（UnionID机制）
    @GET("sns/userinfo")
    Observable<UserInfoBean> getUserInfo(@QueryMap HashMap map);//access_token=ACCESS_TOKEN&openid=OPENID
}
