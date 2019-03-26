package ycy.ccyy.yueyavillage;

import android.app.Application;
import android.text.TextUtils;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.stetho.Stetho;
import com.raizlabs.android.dbflow.config.FlowManager;
import com.raizlabs.android.dbflow.sql.language.SQLite;

import ycy.ccyy.yueyavillage.bean.UserInfoBean;
import ycy.ccyy.yueyavillage.bean.UserInfoBean_Table;
import ycy.ccyy.yueyavillage.presenter.LoginPresenter;
import ycy.ccyy.yueyavillage.util.CookieUtil;
import ycy.ccyy.yueyavillage.util.DataCacheUtil;
import ycy.ccyy.yueyavillage.util.SharedPreferenceUtil;

public class YcyApplication extends Application {
    private static YcyApplication app = null;

    @Override
    public void onCreate() {
        super.onCreate();
        app = this;
        Fresco.initialize(this);
        if (BuildConfig.DEBUG) {
            Stetho.initialize(Stetho.newInitializerBuilder(this)
                    .enableWebKitInspector(Stetho.defaultInspectorModulesProvider(this))
                    .build());
        }
        FlowManager.init(this);
        initUser();
    }

    public static YcyApplication getApp() {
        return app;
    }


    private void initUser() {
        String loginKey = SharedPreferenceUtil.getString(LoginPresenter.LOGIN_KEY, "");
        if (!TextUtils.isEmpty(loginKey)) {
            UserInfoBean userInfoBean = SQLite.select().from(UserInfoBean.class).where(UserInfoBean_Table.uId.eq(loginKey)).querySingle();
            if (userInfoBean == null) {
                return;
            } else {
                DataCacheUtil.getInstance().setUserInfoBean(userInfoBean);
                CookieUtil.getInstance().saveCookie(userInfoBean.user_id, false);
            }
        }
    }

}
