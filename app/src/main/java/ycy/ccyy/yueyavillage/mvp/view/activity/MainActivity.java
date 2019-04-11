package ycy.ccyy.yueyavillage.mvp.view.activity;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

import ycy.ccyy.yueyavillage.R;
import ycy.ccyy.yueyavillage.adapter.MainAdapter;
import ycy.ccyy.yueyavillage.base.MvpActivity;
import ycy.ccyy.yueyavillage.bean.PageInfoBean;
import ycy.ccyy.yueyavillage.mvp.contract.MainContract;
import ycy.ccyy.yueyavillage.mvp.presenter.MainPresenter;
import ycy.ccyy.yueyavillage.util.DataCacheUtil;
import ycy.ccyy.yueyavillage.util.UiUtil;
import ycy.ccyy.yueyavillage.widget.YcyDividerItemDecoration;

public class MainActivity extends MvpActivity<MainPresenter> implements MainContract.View {
    private ImageView ivBack;
    private SimpleDraweeView svUserIcon;
    private TextView tvUserName, tvUserIntegral, tvRanking;
    private RecyclerView rvList;

    @Override
    protected int getResourceId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initIntent() {

    }

    @Override
    protected void initControll() {
        ivBack = findViewById(R.id.iv_main_back);
        svUserIcon = findViewById(R.id.sv_main_user_icon);
        tvUserName = findViewById(R.id.tv_main_user_name);
        tvUserIntegral = findViewById(R.id.tv_main_user_integral);
        tvRanking = findViewById(R.id.tv_main_ranking);
        rvList = findViewById(R.id.rv_main_list);

        if (DataCacheUtil.getInstance().getUserInfo() != null) {
            svUserIcon.setImageURI(Uri.parse(DataCacheUtil.getInstance().getUserInfo().userIcon));
            tvUserName.setText(DataCacheUtil.getInstance().getUserInfo().userName);
        }
        rvList.setLayoutManager(new GridLayoutManager(this, 2));
        rvList.addItemDecoration(new YcyDividerItemDecoration((int) UiUtil.dpTopx(20)));
        mPresenter.getPagesInfo();
    }

    @Override
    protected void initObserable() {
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jumpToHomePage(HomeActivity.PAGE_AMUSEMENT_PARK);
            }
        });
    }


    private void jumpToHomePage(int page) {
        Intent it = new Intent(this, HomeActivity.class);
        it.putExtra(HomeActivity.PAGE, page);
        startActivity(it);
        finish();
    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            jumpToHomePage(HomeActivity.PAGE_AMUSEMENT_PARK);
        }
        return super.onKeyUp(keyCode, event);
    }

    @Override
    public void requestPagesInfoSuccess(List<PageInfoBean> response) {
        rvList.setAdapter(new MainAdapter(this, response, new MainAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(PageInfoBean pageInfoBean) {
                if (pageInfoBean.canUse) {
                    jumpToHomePage(pageInfoBean.jumpUrl);
                }
            }
        }));
    }

    @Override
    public void requestPagesInfoFailed(String msg) {
        showToast(msg);
    }
}
