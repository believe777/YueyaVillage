package ycy.ccyy.yueyavillage.net;

import android.os.Build;

import com.facebook.stetho.okhttp3.StethoInterceptor;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import okhttp3.Cache;
import okhttp3.ConnectionSpec;
import okhttp3.OkHttpClient;
import okhttp3.TlsVersion;
import okhttp3.internal.Util;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import ycy.ccyy.yueyavillage.BuildConfig;
import ycy.ccyy.yueyavillage.util.StorageUtils;

//网络请求库
public class NetControl {
    private static final long MAX_SIZE = 1024 * 300;
    private static NetControl control = null;
    private static final String BASE_URL = "https://www.wanandroid.com/";
    public static final String WX_URL = "https://api.weixin.qq.com/";
    private Retrofit retrofit;
    private Retrofit wxRetrofit;

    public static NetControl getInstance() {
        if (control == null) {
            synchronized (NetControl.class) {
                if (control == null) {
                    control = new NetControl();
                }
            }
        }
        return control;
    }

    NetControl() {
        retrofit = initRotrofit(BASE_URL);
        wxRetrofit = initRotrofit(WX_URL);
    }

    public ApiService getApi() {
        return retrofit.create(ApiService.class);
    }

    public WXService getWX() {
        return wxRetrofit.create(WXService.class);
    }

    private Retrofit initRotrofit(String url) {
        return new Retrofit.Builder()
                .baseUrl(url)
                .client(initClient())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    private OkHttpClient initClient() {
        X509TrustManager trustManager = new TrustAllCertsX509TrustMaster();
        SSLSocketFactory sslSocketFactory = getDefaultSSLSocketFactory(trustManager);
        OkHttpClient.Builder builder = getOkHttpBuilder(trustManager, sslSocketFactory);
        return builder.build();
    }

    //-----------------证书信任--------------------------
    private SSLSocketFactory getDefaultSSLSocketFactory(X509TrustManager trustManager) {
        final String[] protocols = new String[]{"TLSv1", "TLSv1.1", "TLSv1.2"};
        SSLContext sslContext = null;
        try {
            sslContext = SSLContext.getInstance("TLS");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
        try {
            sslContext.init(null, new TrustManager[]{trustManager}, new SecureRandom());
        } catch (KeyManagementException e) {
            e.printStackTrace();
            return null;
        }
        final SSLSocketFactory sslImpl = sslContext.getSocketFactory();
        SSLSocketFactory sslFactoryProxy = new SSLSocketFactory() {
            @Override
            public String[] getDefaultCipherSuites() {
                return sslImpl.getDefaultCipherSuites();
            }

            @Override
            public String[] getSupportedCipherSuites() {
                return sslImpl.getSupportedCipherSuites();
            }

            @Override
            public Socket createSocket(Socket s, String host, int port, boolean autoClose) throws IOException {
                int localPort = s.getLocalPort();
                Socket ssl = sslImpl.createSocket(s, host, port, autoClose);
                if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP && ssl instanceof SSLSocket) {
                    ((SSLSocket) ssl).setEnabledProtocols(protocols);
                }
                return ssl;
            }

            @Override
            public Socket createSocket(String host, int port) throws IOException, UnknownHostException {
                Socket result = sslImpl.createSocket(host, port);
                if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP && result instanceof SSLSocket) {
                    ((SSLSocket) result).setEnabledProtocols(protocols);
                }
                return result;
            }

            @Override
            public Socket createSocket(String host, int port, InetAddress localHost, int localPort) throws IOException, UnknownHostException {
                Socket ssl = sslImpl.createSocket(host, port, localHost, localPort);
                if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP && ssl instanceof SSLSocket) {
                    ((SSLSocket) ssl).setEnabledProtocols(protocols);
                }
                return ssl;
            }

            @Override
            public Socket createSocket(InetAddress host, int port) throws IOException {
                Socket result = sslImpl.createSocket(host, port);
                if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP && result instanceof SSLSocket) {
                    ((SSLSocket) result).setEnabledProtocols(protocols);
                }
                return result;
            }

            @Override
            public Socket createSocket(InetAddress address, int port, InetAddress localAddress, int localPort) throws IOException {
                Socket ssl = sslImpl.createSocket(address, port, localAddress, localPort);
                if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP && ssl instanceof SSLSocket) {
                    ((SSLSocket) ssl).setEnabledProtocols(protocols);
                }
                return ssl;
            }
        };
        return sslFactoryProxy;
    }

    private OkHttpClient.Builder getOkHttpBuilder(final X509TrustManager trustManager, final SSLSocketFactory sslSocketFactory) {
        OkHttpClient.Builder okHttpClientBuilder = new OkHttpClient.Builder();
        okHttpClientBuilder.connectTimeout(20, TimeUnit.SECONDS).writeTimeout(120, TimeUnit.SECONDS).readTimeout(120, TimeUnit.SECONDS);
        okHttpClientBuilder.addInterceptor(new HttpLoggingInterceptor());
        okHttpClientBuilder.addInterceptor(new CookieInterceptor());
        okHttpClientBuilder.cache(new Cache(StorageUtils.getCacheDir(StorageUtils.FILE_TYPE.CACHE), MAX_SIZE));
        if (BuildConfig.DEBUG) {
            okHttpClientBuilder.addNetworkInterceptor(new StethoInterceptor());
        }
        okHttpClientBuilder.hostnameVerifier(getDebugHostNameVerifier());
        ConnectionSpec MODEN_TLS_WITH_SSL3 = new ConnectionSpec.Builder(ConnectionSpec.MODERN_TLS)
                .tlsVersions(TlsVersion.TLS_1_2, TlsVersion.TLS_1_1, TlsVersion.TLS_1_0, TlsVersion.SSL_3_0)
                .build();
        List<ConnectionSpec> DEFAULT_CONNECTION_SPECS_YQB = Util.immutableList(MODEN_TLS_WITH_SSL3, ConnectionSpec.COMPATIBLE_TLS, ConnectionSpec.CLEARTEXT);
        okHttpClientBuilder.connectionSpecs(DEFAULT_CONNECTION_SPECS_YQB);
        try {
            if (trustManager != null && sslSocketFactory != null) {
                okHttpClientBuilder.sslSocketFactory(sslSocketFactory, trustManager);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (BuildConfig.DEBUG) {
            okHttpClientBuilder.addNetworkInterceptor(new StethoInterceptor());
        }
        return okHttpClientBuilder;
    }

    private static HostnameVerifier getDebugHostNameVerifier() {
        HostnameVerifier result = new HostnameVerifier() {
            @Override
            public boolean verify(String hostname, SSLSession session) {
                return true;
            }
        };
        return result;
    }
}
