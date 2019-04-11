package ycy.ccyy.yueyavillage.di.module;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import ycy.ccyy.yueyavillage.di.component.MvpFragmentComponent;
import ycy.ccyy.yueyavillage.mvp.view.fragment.AmusementParkFragment;
import ycy.ccyy.yueyavillage.mvp.view.fragment.PromisePoolFragment;
import ycy.ccyy.yueyavillage.mvp.view.fragment.SmallYellowPacketFragment;
import ycy.ccyy.yueyavillage.mvp.view.fragment.YueyaVillageFragment;

@Module(subcomponents = MvpFragmentComponent.class)
public abstract class AbstractAllFragmentModule {
    @ContributesAndroidInjector()
    abstract AmusementParkFragment contributesAmusementParkFragmentInject();

    @ContributesAndroidInjector()
    abstract PromisePoolFragment contributesPromisePoolFragmentInject();

    @ContributesAndroidInjector()
    abstract SmallYellowPacketFragment contributesSmallYellowPacketFragmentInject();

    @ContributesAndroidInjector()
    abstract YueyaVillageFragment contributesYueyaVillageFragmentInject();
}
