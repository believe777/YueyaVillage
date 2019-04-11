package ycy.ccyy.yueyavillage.di.component;

import javax.inject.Singleton;

import dagger.Component;
import dagger.android.AndroidInjectionModule;
import dagger.android.support.AndroidSupportInjectionModule;
import ycy.ccyy.yueyavillage.YcyApplication;
import ycy.ccyy.yueyavillage.di.module.AbstractAllActivityModule;
import ycy.ccyy.yueyavillage.di.module.AbstractAllFragmentModule;
import ycy.ccyy.yueyavillage.di.module.AppModule;
import ycy.ccyy.yueyavillage.mvp.module.DataManager;

@Singleton
@Component(modules = {AndroidInjectionModule.class,
        AndroidSupportInjectionModule.class,
        AbstractAllActivityModule.class,
        AbstractAllFragmentModule.class,
        AppModule.class})
public interface AppComponent {
    void inject(YcyApplication wanAndroidApp);

    YcyApplication getContext();

    DataManager getDataManager();
}
