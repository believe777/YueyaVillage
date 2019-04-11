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
import ycy.ccyy.yueyavillage.adapter.MyWorkDetailAdapter;
import ycy.ccyy.yueyavillage.base.MvpActivity;
import ycy.ccyy.yueyavillage.bean.WorkDetailBean;
import ycy.ccyy.yueyavillage.mvp.contract.MyWorkDetailContract;
import ycy.ccyy.yueyavillage.mvp.presenter.MyWorkDetailPresenter;
import ycy.ccyy.yueyavillage.util.CollectionUtil;
import ycy.ccyy.yueyavillage.widget.BaseTitle;

public class MyWorkDetailActivity extends MvpActivity<MyWorkDetailPresenter> implements MyWorkDetailContract.View {
    private SimpleDraweeView svUserIcon;
    private ViewPager vpPager;
    private TextView tvUserName, tvDate, tvTitle, tvIndex;
    private int iconCount;
    private int id;

    @Override
    protected int getResourceId() {
        return R.layout.activity_my_work_detail;
    }

    @Override
    protected void initIntent() {
        id = getIntent().getIntExtra("id", -1);
    }

    @Override
    protected void initControll() {
        new BaseTitle(this);
        vpPager = findViewById(R.id.vp_my_work_detail_pager);
        svUserIcon = findViewById(R.id.sv_my_work_detail_user_icon);
        tvUserName = findViewById(R.id.tv_my_work_detail_user_name);
        tvDate = findViewById(R.id.tv_my_work_detail_date);
        tvTitle = findViewById(R.id.tv_my_work_detail_title);
        tvIndex = findViewById(R.id.tv_my_work_detail_index);
        mPresenter.queryWorkDetail(id);
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
    public void onRequestSuccess(WorkDetailBean response) {
        svUserIcon.setImageURI(Uri.parse(response.userIcon));
        tvUserName.setText(response.userName);
        tvDate.setText(response.date);
        tvTitle.setText(response.title);
        if (!CollectionUtil.isEmpty(response.icons)) {
            List<View> iconList = new ArrayList<>();
            for (String icon : response.icons) {
                SimpleDraweeView simpleDraweeView = new SimpleDraweeView(this);
                simpleDraweeView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
                simpleDraweeView.setImageURI(Uri.parse(icon));
                simpleDraweeView.getHierarchy().setActualImageScaleType(ScalingUtils.ScaleType.FIT_XY);
                iconList.add(simpleDraweeView);
            }
            vpPager.setAdapter(new MyWorkDetailAdapter(iconList));
            iconCount = iconList.size();
            tvIndex.setText("1/" + iconCount);
        }
    }

    @Override
    public void onRequestFailed(String msg) {
        showToast(msg);
    }
}
