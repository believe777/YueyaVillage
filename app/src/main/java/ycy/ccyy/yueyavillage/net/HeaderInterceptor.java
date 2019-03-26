package ycy.ccyy.yueyavillage.net;

import android.text.TextUtils;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import ycy.ccyy.yueyavillage.util.CookieUtil;

public class HeaderInterceptor implements Interceptor {
    public static final String HEADER_COOKIE = "Cookie";
    public static final String CONTENT_TYPE = "Content-Type";
    public static final String ACCEPT = "Accept";

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        String function = request.url().encodedPath();
        Request.Builder builder = request.newBuilder();
        if (!TextUtils.isEmpty(CookieUtil.getInstance().getCookie())) {
            builder.addHeader(HEADER_COOKIE, CookieUtil.getInstance().getCookie());
        }
        if (HttpContent.UPLOAD.equals(function) || HttpContent.UPLOAD1.equals(function) || HttpContent.UPLOAD_BATCH.equals(function)) {
            builder.addHeader(CONTENT_TYPE, "application/octet-stream");
        } else {
            builder.addHeader(CONTENT_TYPE, "application/json");
        }
        builder.addHeader(ACCEPT, "application/json,*/*");
        return chain.proceed(builder.build());
    }
}
