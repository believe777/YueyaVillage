package ycy.ccyy.yueyavillage.mvp.view.activity;

import android.net.Uri;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.drawable.ScalingUtils;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

import ycy.ccyy.yueyavillage.R;
import ycy.ccyy.yueyavillage.adapter.MyCollectionDetailAdapter;
import ycy.ccyy.yueyavillage.base.MvpActivity;
import ycy.ccyy.yueyavillage.bean.CollectionDetailBean;
import ycy.ccyy.yueyavillage.mvp.contract.MyCollectionDetailContract;
import ycy.ccyy.yueyavillage.mvp.presenter.MyCollectionDetailPresenter;
import ycy.ccyy.yueyavillage.util.CollectionUtil;
import ycy.ccyy.yueyavillage.widget.BaseTitle;

public class MyCollectionDetailActivity extends MvpActivity<MyCollectionDetailPresenter> implements MyCollectionDetailContract.View {
    private ViewPager vpPager;
    private SimpleDraweeView svPublisherIcon;
    private TextView tvPublishName, tvDate, tvTitle, tvIndex;
    private int iconCount;
    private int id;

    @Override
    protected int getResourceId() {
        return R.layout.activity_my_collection_detail;
    }

    @Override
    protected void initIntent() {
        id = getIntent().getIntExtra("id", -1);
    }

    @Override
    protected void initControll() {
        new BaseTitle(this);
        vpPager = findViewById(R.id.vp_my_collection_detail_pager);
        svPublisherIcon = findViewById(R.id.sv_my_collection_detail_publisher_icon);
        tvDate = findViewById(R.id.tv_my_collection_detail_date);
        tvPublishName = findViewById(R.id.tv_my_collection_detail_publisher_name);
        tvTitle = findViewById(R.id.tv_my_collection_detail_title);
        tvIndex = findViewById(R.id.tv_my_collection_detail_index);
        mPresenter.queryCollectionDetail(id);
    }

    @Override
    protected void initObserable() {
        vpPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                tvIndex.setText((position + 1) + "/" + iconCount);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    public void onRequestSuccess(CollectionDetailBean response) {
        svPublisherIcon.setImageURI(Uri.parse(response.publisherIcon));
        tvDate.setText(response.date);
        tvTitle.setText(response.title);
        tvPublishName.setText(response.publisherName);
        if (!CollectionUtil.isEmpty(response.icons)) {
            iconCount = response.icons.size();
            tvIndex.setText("1/" + iconCount);
            List<View> list = new ArrayList<>();
            for (String icon : response.icons) {
                SimpleDraweeView simpleDraweeView = new SimpleDraweeView(this);
                simpleDraweeView.getHierarchy().setActualImageScaleType(ScalingUtils.ScaleType.FIT_XY);
                simpleDraweeView.setImageURI(Uri.parse(icon));
                simpleDraweeView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
                list.add(simpleDraweeView);
            }
            vpPager.setAdapter(new MyCollectionDetailAdapter(list));
        }
    }

    @Override
    public void onRequestFailed(String msg) {
        showToast(msg);
    }
}
