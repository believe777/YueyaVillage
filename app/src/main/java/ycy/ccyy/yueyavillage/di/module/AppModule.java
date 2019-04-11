package ycy.ccyy.yueyavillage.di.module;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import ycy.ccyy.yueyavillage.YcyApplication;
import ycy.ccyy.yueyavillage.mvp.module.DataManager;
import ycy.ccyy.yueyavillage.net.HttpHelper;
import ycy.ccyy.yueyavillage.net.HttpHelperImpl;

@Module
public class AppModule {
    private final YcyApplication application;

    public AppModule(YcyApplication application) {
        this.application = application;
    }

    @Provides
    @Singleton
    YcyApplication provideApplicationContext() {
        return application;
    }

    @Provides
    @Singleton
    HttpHelper provideHttpHelper(HttpHelperImpl httpHelperImpl) {
        return httpHelperImpl;
    }

    @Provides
    @Singleton
    DataManager provideDataManager(HttpHelper httpHelper) {
        return new DataManager(httpHelper);
    }

}
