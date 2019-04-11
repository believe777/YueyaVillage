package ycy.ccyy.yueyavillage.di.component;

import dagger.Subcomponent;
import dagger.android.AndroidInjectionModule;
import dagger.android.AndroidInjector;
import ycy.ccyy.yueyavillage.base.BaseFragment;
import ycy.ccyy.yueyavillage.base.MvpFragment;

@Subcomponent(modules = {AndroidInjectionModule.class})
public interface MvpFragmentComponent extends AndroidInjector<MvpFragment> {
    @Subcomponent.Builder
    abstract class BaseBuilder extends AndroidInjector.Builder<MvpFragment>{

    }
}
