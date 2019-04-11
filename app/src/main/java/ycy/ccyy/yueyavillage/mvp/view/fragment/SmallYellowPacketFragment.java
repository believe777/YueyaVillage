package ycy.ccyy.yueyavillage.mvp.view.fragment;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import ycy.ccyy.yueyavillage.R;
import ycy.ccyy.yueyavillage.adapter.BaseViewPagerAdapter;
import ycy.ccyy.yueyavillage.adapter.SmallYellowPacketAdapter;
import ycy.ccyy.yueyavillage.base.MvpFragment;
import ycy.ccyy.yueyavillage.bean.SmallYellowPacketBean;
import ycy.ccyy.yueyavillage.mvp.contract.SmallYellowPacketContract;
import ycy.ccyy.yueyavillage.mvp.presenter.SmallYellowPacketPresent;
import ycy.ccyy.yueyavillage.mvp.view.activity.PublishActivity;
import ycy.ccyy.yueyavillage.util.CollectionUtil;
import ycy.ccyy.yueyavillage.widget.BaseHomeTitle;
import ycy.ccyy.yueyavillage.widget.TitleSelectorView;

public class SmallYellowPacketFragment extends MvpFragment<SmallYellowPacketPresent> implements SmallYellowPacketContract.View {
    private ViewPager vpPager;
    private TitleSelectorView titleSelectorView;

    private TitleSelectorView.OnSelectorItemClickListener listener = new TitleSelectorView.OnSelectorItemClickListener() {
        @Override
        public void onItemClick(View view, int position) {
            vpPager.setCurrentItem(position);
        }
    };

    @Override
    protected int getResourceId() {
        return R.layout.fragment_small_yellow_packet;
    }

    @Override
    protected void initControll(View view) {
        initTitle(view);
        vpPager = view.findViewById(R.id.vp_yellow_packet_pager);
        mPresenter.querySmallYellowPacket();
    }

    @Override
    protected void initObserable() {
        vpPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                titleSelectorView.onItemSelected(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void initTitle(View view) {
        BaseHomeTitle title = new BaseHomeTitle(view);
        ImageView rightView = new ImageView(getContext());
        rightView.setImageResource(R.mipmap.publish_work);
        title.setRight(rightView, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(getActivity(), PublishActivity.class);
                getActivity().startActivity(it);
            }
        });
        titleSelectorView = new TitleSelectorView(getContext());
        titleSelectorView.addItem("最新发布", "收藏榜单");
        titleSelectorView.setOnSelectorItemClickListener(listener);
        title.setTitle(titleSelectorView);
    }


    @Override
    public void onRequestSuccess(List<List<SmallYellowPacketBean>> response) {
        if (!CollectionUtil.isEmpty(response)) {
            List<View> list = new ArrayList<>();
            for (List<SmallYellowPacketBean> smallYellowPacketBeans : response) {
                RecyclerView recyclerView = (RecyclerView) LayoutInflater.from(getContext()).inflate(R.layout.layout_small_yellow_packet_pager, vpPager, false);
                DividerItemDecoration divier = new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL);
                divier.setDrawable(getResources().getDrawable(R.drawable.list_divider_work));
                recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                recyclerView.addItemDecoration(divier);
                recyclerView.setAdapter(new SmallYellowPacketAdapter(getContext(), smallYellowPacketBeans));
                list.add(recyclerView);
            }
            vpPager.setAdapter(new BaseViewPagerAdapter(list));
        }
    }

    @Override
    public void onRequestFailed(String msg) {
        showToast(msg);
    }
}
