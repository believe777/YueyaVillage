package ycy.ccyy.yueyavillage.net;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class HeaderInterceptor implements Interceptor {
    public static final String HEADER_COOKIE = "Cookie";
    public static final String CONTENT_TYPE = "Content-Type";
    public static final String ACCEPT = "Accept";

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        String function = request.url().encodedPath();
        function = function.substring(1, function.length());
        Request.Builder builder = request.newBuilder();
//        if (!TextUtils.isEmpty(CookieUtil.getInstance().getCookie())) {
//            builder.addHeader(HEADER_COOKIE, CookieUtil.getInstance().getCookie());
//        }
        builder.addHeader(HEADER_COOKIE, "userid=1015");
        if (HttpContent.UPLOAD.equals(function) || HttpContent.UPLOADByBinary.equals(function) || HttpContent.UPLOAD_BATCH.equals(function)) {
            builder.addHeader(CONTENT_TYPE, "application/octet-stream");//图片上传
        } else {
            builder.addHeader(CONTENT_TYPE, "application/json");
        }
        builder.addHeader(ACCEPT, "application/json,*/*");
        return chain.proceed(builder.build());
    }
}
