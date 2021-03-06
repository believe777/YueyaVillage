package ycy.ccyy.yueyavillage;

import android.app.Activity;
import android.app.Application;
import android.text.TextUtils;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.stetho.Stetho;
import com.raizlabs.android.dbflow.config.FlowManager;

import java.util.HashMap;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;
import ycy.ccyy.yueyavillage.bean.UserInfoBean;
import ycy.ccyy.yueyavillage.bean.UserInfoBean_Table;
import ycy.ccyy.yueyavillage.di.component.AppComponent;
import ycy.ccyy.yueyavillage.di.component.DaggerAppComponent;
import ycy.ccyy.yueyavillage.di.module.AppModule;
import ycy.ccyy.yueyavillage.mvp.presenter.LoginPresenter;
import ycy.ccyy.yueyavillage.util.CookieUtil;
import ycy.ccyy.yueyavillage.util.DataCacheUtil;
import ycy.ccyy.yueyavillage.util.SharedPreferenceUtil;

public class YcyApplication extends Application implements HasActivityInjector {
    private static YcyApplication app = null;
    private static volatile AppComponent appComponent;
    @Inject
    DispatchingAndroidInjector<Activity> mAndroidInjector;

    @Override
    public void onCreate() {
        super.onCreate();
        Stetho.initialize(
                Stetho.newInitializerBuilder(this)
                        .enableDumpapp(
                                Stetho.defaultDumperPluginsProvider(this))
                        .enableWebKitInspector(
                                Stetho.defaultInspectorModulesProvider(this))
                        .build());
        app = this;
        appComponent = DaggerAppComponent.builder().appModule(new AppModule(app)).build();
        appComponent.inject(this);
        Fresco.initialize(this);
        FlowManager.init(this);
        initUser();
    }

    public static YcyApplication getApp() {
        return app;
    }


    private void initUser() {
        String loginKey = SharedPreferenceUtil.getString(LoginPresenter.LOGIN_KEY, "");
        if (!TextUtils.isEmpty(loginKey)) {
            HashMap map = new HashMap();
            map.put(UserInfoBean_Table.uId, loginKey);
            UserInfoBean userInfoBean = getAppComponent().getDataManager().query(map, UserInfoBean.class);
            if (userInfoBean == null) {
                return;
            } else {
                DataCacheUtil.getInstance().setUserInfoBean(userInfoBean);
                CookieUtil.getInstance().saveCookie(userInfoBean.user_id, false);
            }
        }
    }

    public static AppComponent getAppComponent() {
        if (appComponent == null) {
            synchronized (YcyApplication.class) {
                if (appComponent == null) {
                    appComponent = DaggerAppComponent.builder().appModule(new AppModule(app)).build();
                    appComponent.inject(app);
                }
            }
        }
        return appComponent;
    }

    @Override
    public AndroidInjector<Activity> activityInjector() {
        return mAndroidInjector;
    }
}
