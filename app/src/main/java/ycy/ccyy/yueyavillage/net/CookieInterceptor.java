package ycy.ccyy.yueyavillage.net;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Response;
import ycy.ccyy.yueyavillage.util.CookieUtil;

//cookie拦截
public class CookieInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        okhttp3.Response originalResponse = chain.proceed(chain.request());
        if (!originalResponse.headers("Set-Cookie").isEmpty()) {
            for (String header : originalResponse.headers("Set-Cookie")) {
                //保存cookie
                CookieUtil.getInstance().saveCookie(header, true);
            }
        }
        return originalResponse;
    }
}
