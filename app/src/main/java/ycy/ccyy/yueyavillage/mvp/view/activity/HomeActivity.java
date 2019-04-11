package ycy.ccyy.yueyavillage.mvp.view.activity;

import android.app.Activity;
import android.net.Uri;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import ycy.ccyy.yueyavillage.R;
import ycy.ccyy.yueyavillage.adapter.HomePagerAdapter;
import ycy.ccyy.yueyavillage.base.BaseFragment;
import ycy.ccyy.yueyavillage.base.MvpActivity;
import ycy.ccyy.yueyavillage.bean.TabPagesInfoBean;
import ycy.ccyy.yueyavillage.mvp.contract.HomeContract;
import ycy.ccyy.yueyavillage.mvp.presenter.HomePresenter;
import ycy.ccyy.yueyavillage.mvp.view.fragment.AmusementParkFragment;
import ycy.ccyy.yueyavillage.mvp.view.fragment.PromisePoolFragment;
import ycy.ccyy.yueyavillage.mvp.view.fragment.SmallYellowPacketFragment;
import ycy.ccyy.yueyavillage.mvp.view.fragment.YueyaVillageFragment;
import ycy.ccyy.yueyavillage.widget.HomeViewPager;

// TODO: 2019/4/11  模块可配置，默认4模块
public class HomeActivity extends MvpActivity<HomePresenter> implements HomeContract.View {
    public static final String PAGE = "page";
    public static final int PAGE_AMUSEMENT_PARK = 0;
    public static final int PAGE_SMALL_YELLOW_PACKET = 1;
    public static final int PAGE_PROMISE_POOL = 2;
    public static final int PAGE_YUEYA_VILLAGE = 3;
    private HomeViewPager homeViewPager;
    private TabLayout homeTabLayout;
    private OnWebViewBackListener listener;
    private int choosePage;

    @Override
    protected int getResourceId() {
        return R.layout.activity_home;
    }

    @Override
    protected void initIntent() {
        choosePage = getIntent().getIntExtra(PAGE, 0);
    }

    @Override
    protected void initControll() {
        homeViewPager = findViewById(R.id.home_viewpager);
        homeTabLayout = findViewById(R.id.home_tablayout);
        homeViewPager.setOffscreenPageLimit(4);
        initFragment();
        mPresenter.getTabPages();
        homeViewPager.setCurrentItem(choosePage);
    }

    @Override
    protected void initObserable() {
        homeTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                ((TextView) tab.getCustomView().findViewById(R.id.tv_tab)).setTextColor(getResources().getColor(R.color.tab_selected));
                ((ImageView) tab.getCustomView().findViewById(R.id.iv_tab)).setColorFilter(getResources().getColor(R.color.tab_selected));
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                ((TextView) tab.getCustomView().findViewById(R.id.tv_tab)).setTextColor(getResources().getColor(R.color.tab_unselected));
                ((ImageView) tab.getCustomView().findViewById(R.id.iv_tab)).setColorFilter(getResources().getColor(R.color.tab_unselected));
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    private void initFragment() {
        List<Fragment> fragmentList = new ArrayList<>();
        BaseFragment amusementParkFragment = new AmusementParkFragment();
        BaseFragment smallYellowPacketFragment = new SmallYellowPacketFragment();
        BaseFragment promisePoolFragment = new PromisePoolFragment();
        BaseFragment yueyaVillageFragment = new YueyaVillageFragment();
        fragmentList.add(amusementParkFragment);
        fragmentList.add(smallYellowPacketFragment);
        fragmentList.add(promisePoolFragment);
        fragmentList.add(yueyaVillageFragment);
        listener = ((AmusementParkFragment) amusementParkFragment).listener;
        homeViewPager.setAdapter(new HomePagerAdapter(getSupportFragmentManager(), fragmentList));
        homeViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(homeTabLayout));
        homeTabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(homeViewPager));
    }

    private View createCustomView(String resource, String text, boolean selected) {
        View view = getLayoutInflater().inflate(R.layout.layout_base_tab, homeTabLayout, false);
        ImageView tabIcon = view.findViewById(R.id.iv_tab);
        tabIcon.setColorFilter(getResources().getColor(selected ? R.color.tab_selected : R.color.tab_unselected));
        TextView tabText = view.findViewById(R.id.tv_tab);
        tabIcon.setImageURI(Uri.parse(resource));
        tabText.setText(text);
        tabText.setTextColor(getResources().getColor(selected ? R.color.tab_selected : R.color.tab_unselected));
        return view;
    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (homeViewPager.getCurrentItem() == 0) {
                if (listener != null) {
                    listener.onBack(this);
                }
                return false;
            }
        }
        return super.onKeyUp(keyCode, event);
    }

    @Override
    public void tabPagesInfo(List<TabPagesInfoBean> response) {
        for (TabPagesInfoBean tabPagesInfoBean : response) {
            homeTabLayout.addTab(homeTabLayout.newTab().setCustomView(createCustomView(tabPagesInfoBean.tabIcon, tabPagesInfoBean.tabName, choosePage == tabPagesInfoBean.pageType)));
        }
    }

    public interface OnWebViewBackListener {
        void onBack(Activity activity);
    }
}
