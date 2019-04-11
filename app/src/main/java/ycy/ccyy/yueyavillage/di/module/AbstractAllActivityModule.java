package ycy.ccyy.yueyavillage.di.module;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import ycy.ccyy.yueyavillage.di.component.MvpActivityComponent;
import ycy.ccyy.yueyavillage.mvp.view.activity.ChooseHeadFrameActivity;
import ycy.ccyy.yueyavillage.mvp.view.activity.HomeActivity;
import ycy.ccyy.yueyavillage.mvp.view.activity.ImageSelectorActivity;
import ycy.ccyy.yueyavillage.mvp.view.activity.LoginActivity;
import ycy.ccyy.yueyavillage.mvp.view.activity.MainActivity;
import ycy.ccyy.yueyavillage.mvp.view.activity.MyCollectionActivity;
import ycy.ccyy.yueyavillage.mvp.view.activity.MyCollectionDetailActivity;
import ycy.ccyy.yueyavillage.mvp.view.activity.MyWorkDetailActivity;
import ycy.ccyy.yueyavillage.mvp.view.activity.MyWorksActivity;
import ycy.ccyy.yueyavillage.mvp.view.activity.PublishActivity;
import ycy.ccyy.yueyavillage.mvp.view.activity.PublishPromiseActivity;

@Module(subcomponents = {MvpActivityComponent.class})
public abstract class AbstractAllActivityModule {

    @ContributesAndroidInjector()
    abstract LoginActivity contributesLoginActivityInjector();

    @ContributesAndroidInjector()
    abstract HomeActivity contributesHomeActivityInjector();

    @ContributesAndroidInjector()
    abstract ChooseHeadFrameActivity contributesChooseHeadFrameActivityInjector();

    @ContributesAndroidInjector()
    abstract ImageSelectorActivity contributesImageSelectorActivityInjector();

    @ContributesAndroidInjector()
    abstract MyCollectionActivity contributesMyCollectionActivityInjector();

    @ContributesAndroidInjector()
    abstract MyCollectionDetailActivity contributesMyCollectionDetailActivityInjector();

    @ContributesAndroidInjector()
    abstract MyWorkDetailActivity contributesMyWorkDetailActivityInjector();

    @ContributesAndroidInjector()
    abstract MyWorksActivity contributesMyWorksActivityInjector();

    @ContributesAndroidInjector()
    abstract MainActivity contributesMainActivityInjector();

    @ContributesAndroidInjector()
    abstract PublishActivity contributesPublishActivityInjector();

    @ContributesAndroidInjector()
    abstract PublishPromiseActivity contributesPublishPromiseActivityInjector();
}
