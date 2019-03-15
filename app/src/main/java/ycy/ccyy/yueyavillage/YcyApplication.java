package ycy.ccyy.yueyavillage;

import android.app.Application;

import com.facebook.stetho.Stetho;

import ycy.ccyy.yueyavillage.util.PermissionsUtil;
import ycy.ccyy.yueyavillage.util.StorageUtils;

public class YcyApplication extends Application {
    private static YcyApplication app = null;

    @Override
    public void onCreate() {
        super.onCreate();
        app = this;
        if (BuildConfig.DEBUG) {
            Stetho.initialize(Stetho.newInitializerBuilder(this)
                    .enableWebKitInspector(Stetho.defaultInspectorModulesProvider(this))
                    .build());
        }
    }

    public static YcyApplication getApp() {
        return app;
    }


}
