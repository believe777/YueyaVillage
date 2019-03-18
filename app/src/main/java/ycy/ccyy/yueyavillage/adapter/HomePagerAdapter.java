package ycy.ccyy.yueyavillage.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

import ycy.ccyy.yueyavillage.util.CollectionUtil;
import ycy.ccyy.yueyavillage.util.LogUtil;

public class HomePagerAdapter extends FragmentPagerAdapter {
    List<Fragment> fragmentList;

    public HomePagerAdapter(FragmentManager fm, List<Fragment> fragmentList) {
        super(fm);
        this.fragmentList = CollectionUtil.isEmpty(fragmentList) ? new ArrayList<Fragment>() : fragmentList;
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }
}
