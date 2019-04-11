package ycy.ccyy.yueyavillage.mvp.view.fragment;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Build;
import android.view.View;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.wang.avi.AVLoadingIndicatorView;

import ycy.ccyy.yueyavillage.R;
import ycy.ccyy.yueyavillage.base.BaseFragment;
import ycy.ccyy.yueyavillage.mvp.view.activity.HomeActivity;
import ycy.ccyy.yueyavillage.util.CookieUtil;

public class AmusementParkFragment extends BaseFragment {
    private WebView webView;
    private AVLoadingIndicatorView loadingView;
    private static final String WEB_URL = "http://dogeggs.cn/game/kaipao/";

    public HomeActivity.OnWebViewBackListener listener = new HomeActivity.OnWebViewBackListener() {
        @Override
        public void onBack(Activity activity) {
            webViewBack(activity);
        }
    };

    @Override
    protected int getResourceId() {
        return R.layout.fragment_amusement_park;
    }

    @Override
    protected void initControll(View view) {
        webView = view.findViewById(R.id.wv_amusement_park);
        loadingView = view.findViewById(R.id.loading);
        loadingView.setIndicatorColor(Color.parseColor("#741DE4"));
        loadingView.hide();
        syncCookie(WEB_URL);
        init(WEB_URL);
    }

    @Override
    protected void initObserable() {

    }

    private void webViewBack(Activity activity) {
        if (webView.canGoBack()) {
            webView.goBack();
        } else {
            activity.finish();
        }
    }

    private void syncCookie(String url) {
        CookieManager cookieManager = CookieManager.getInstance();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            cookieManager.removeSessionCookies(null);
            cookieManager.flush();
        } else {
            cookieManager.removeSessionCookie();
            CookieSyncManager.getInstance().sync();
        }
        cookieManager.setAcceptCookie(true);
        cookieManager.setCookie(url, CookieUtil.getInstance().getCookie());
    }

    private void init(String url) {
        webView.loadUrl(url);
        WebSettings webSettings = webView.getSettings();
        webSettings.setDatabaseEnabled(true);
        webSettings.setDomStorageEnabled(true);
        webSettings.setDefaultTextEncodingName("UTF-8");
        webSettings.setJavaScriptEnabled(true);
        webSettings.setAppCacheEnabled(true);
        webSettings.setCacheMode(WebSettings.LOAD_DEFAULT);
        webSettings.setSavePassword(false);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            webSettings.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            webView.setWebContentsDebuggingEnabled(true);
        }
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                loadingView.show();
                super.onPageStarted(view, url, favicon);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                loadingView.hide();
                super.onPageFinished(view, url);
            }

            @Override
            public void onPageCommitVisible(WebView view, String url) {
                super.onPageCommitVisible(view, url);
            }
        });
    }
}
