package ycy.ccyy.yueyavillage.view;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import ycy.ccyy.yueyavillage.R;
import ycy.ccyy.yueyavillage.adapter.HomePagerAdapter;
import ycy.ccyy.yueyavillage.base.MvpActivity;
import ycy.ccyy.yueyavillage.base.MvpFragment;
import ycy.ccyy.yueyavillage.bean.UserInfoBean;
import ycy.ccyy.yueyavillage.contract.HomeContract;
import ycy.ccyy.yueyavillage.fragment.AmusementParkFragment;
import ycy.ccyy.yueyavillage.fragment.PromisePoolFragment;
import ycy.ccyy.yueyavillage.fragment.SmallYellowPacketFragment;
import ycy.ccyy.yueyavillage.fragment.YueyaVillageFragment;
import ycy.ccyy.yueyavillage.presenter.HomePresenter;
import ycy.ccyy.yueyavillage.util.DataCacheUtil;
import ycy.ccyy.yueyavillage.widget.HomeViewPager;


public class HomeActivity extends MvpActivity implements HomeContract.View {
    private HomePresenter mHomePresenter;
    private HomeViewPager homeViewPager;
    private TabLayout homeTabLayout;

    @Override
    protected int getResourceId() {
        return R.layout.activity_home;
    }

    @Override
    protected void bindPresenter() {
        mHomePresenter = new HomePresenter();
    }

    @Override
    protected void initIntent() {
        UserInfoBean userInfoBean = DataCacheUtil.getInstance().getUserInfo();
        showToast("UserName is " + userInfoBean.userName);
    }

    @Override
    protected void initControll() {
        homeViewPager = findViewById(R.id.home_viewpager);
        homeTabLayout = findViewById(R.id.home_tablayout);
        initTabLayout();
        initFragment();
    }

    @Override
    protected void initObserable() {
        homeTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                ((TextView) tab.getCustomView().findViewById(R.id.tv_tab)).setTextColor(getResources().getColor(R.color.tab_selected));
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                ((TextView) tab.getCustomView().findViewById(R.id.tv_tab)).setTextColor(getResources().getColor(R.color.tab_unselected));
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    private void initFragment() {
        List<Fragment> fragmentList = new ArrayList<>();
        MvpFragment amusementParkFragment = new AmusementParkFragment();
        MvpFragment smallYellowPacketFragment = new SmallYellowPacketFragment();
        MvpFragment promisePoolFragment = new PromisePoolFragment();
        MvpFragment yueyaVillageFragment = new YueyaVillageFragment();
        fragmentList.add(amusementParkFragment);
        fragmentList.add(smallYellowPacketFragment);
        fragmentList.add(promisePoolFragment);
        fragmentList.add(yueyaVillageFragment);
        homeViewPager.setAdapter(new HomePagerAdapter(getSupportFragmentManager(), fragmentList));
        homeViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(homeTabLayout));
        homeTabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(homeViewPager));
        homeViewPager.setCurrentItem(0);
    }

    private void initTabLayout() {
        homeTabLayout.addTab(homeTabLayout.newTab().setCustomView(createCustomView(R.mipmap.ic_tab_amusement_park, "游乐园")));
        homeTabLayout.addTab(homeTabLayout.newTab().setCustomView(createCustomView(R.mipmap.ic_tab_small_yellow_packet, "小黄包")));
        homeTabLayout.addTab(homeTabLayout.newTab().setCustomView(createCustomView(R.mipmap.ic_tab_promise_pool, "许愿池")));
        homeTabLayout.addTab(homeTabLayout.newTab().setCustomView(createCustomView(R.mipmap.ic_tab_yueyavillage, "月芽村")));
    }

    private View createCustomView(int resource, String text) {
        View view = getLayoutInflater().inflate(R.layout.layout_base_tab, homeTabLayout, false);
        ImageView tabIcon = view.findViewById(R.id.iv_tab);
        TextView tabText = view.findViewById(R.id.tv_tab);
        tabIcon.setImageResource(resource);
        tabText.setText(text);
        return view;
    }
}
