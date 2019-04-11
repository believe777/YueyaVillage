package ycy.ccyy.yueyavillage.di.component;

import dagger.Subcomponent;
import dagger.android.AndroidInjectionModule;
import dagger.android.AndroidInjector;
import ycy.ccyy.yueyavillage.base.MvpActivity;

@Subcomponent(modules = {AndroidInjectionModule.class})
public interface MvpActivityComponent extends AndroidInjector<MvpActivity> {
    @Subcomponent.Builder
    abstract class BaseBuilder extends AndroidInjector.Builder<MvpActivity> {

    }
}
