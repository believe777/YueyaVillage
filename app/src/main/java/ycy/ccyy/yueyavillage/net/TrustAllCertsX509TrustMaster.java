package ycy.ccyy.yueyavillage.net;

import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.X509TrustManager;

//证书信任
public class TrustAllCertsX509TrustMaster implements X509TrustManager {

    @Override
    public X509Certificate[] getAcceptedIssuers() {
        return null;
    }


    @Override
    public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
    }

    @Override
    public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
    }

}