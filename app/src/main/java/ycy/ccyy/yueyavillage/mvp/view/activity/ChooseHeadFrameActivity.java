package ycy.ccyy.yueyavillage.mvp.view.activity;

import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.View;

import java.util.List;

import ycy.ccyy.yueyavillage.R;
import ycy.ccyy.yueyavillage.adapter.BorderInfoBean;
import ycy.ccyy.yueyavillage.adapter.ChooseHeadFrameAdapter;
import ycy.ccyy.yueyavillage.base.MvpActivity;
import ycy.ccyy.yueyavillage.mvp.contract.ChooseHeadFrameContract;
import ycy.ccyy.yueyavillage.mvp.presenter.ChooseHeadFramePresenter;
import ycy.ccyy.yueyavillage.util.DataCacheUtil;
import ycy.ccyy.yueyavillage.util.UiUtil;
import ycy.ccyy.yueyavillage.widget.BaseTitle;
import ycy.ccyy.yueyavillage.widget.YcyDividerItemDecoration;

public class ChooseHeadFrameActivity extends MvpActivity<ChooseHeadFramePresenter> implements ChooseHeadFrameContract.View {
    public static final int BORDER_DEFAULT = 0;
    public static final int BORDER_DOUBAN = 1;
    public static final int BORDER_TIEBA = 2;
    public static final int BORDER_HUPU = 3;
    public static final int BORDER_DOKI = 4;
    public static final int BORDER_WEIBO = 5;
    public static final int BORDER_NGA = 6;
    public static final int BORDER_BILIBILI = 7;
    public static final int BORDER_ZHIHU = 8;
    private RecyclerView rvList;

    @Override
    protected int getResourceId() {
        return R.layout.activity_choose_head_frame;
    }

    @Override
    protected void initIntent() {

    }

    @Override
    protected void initControll() {
        rvList = findViewById(R.id.rv_choose_head_frame_list);
        new BaseTitle(this, "选择头像框", null, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jumpToPageChoose();
            }
        }, null, null);
        rvList.setLayoutManager(new GridLayoutManager(this, 2));
        rvList.addItemDecoration(new YcyDividerItemDecoration((int) UiUtil.dpTopx(20)));
        mPresenter.getBordersInfo();
    }

    @Override
    protected void initObserable() {

    }

    private void jumpToPageChoose() {
        Intent it = new Intent(this, MainActivity.class);
        startActivity(it);
        finish();
    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            jumpToPageChoose();
        }
        return super.onKeyUp(keyCode, event);
    }

    @Override
    public void requestBordersInfoSuccess(List<BorderInfoBean> response) {
        rvList.setAdapter(new ChooseHeadFrameAdapter(this, response, new ChooseHeadFrameAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BorderInfoBean borderInfoBean) {
                if (DataCacheUtil.getInstance().getUserInfo() != null) {
                    DataCacheUtil.getInstance().getUserInfo().borderType = borderInfoBean.borderType;
                    DataCacheUtil.getInstance().getUserInfo().update();
                }
                jumpToPageChoose();
            }
        }));
    }

    @Override
    public void requestBordersInfoFailed(String msg) {
        showToast(msg);
    }
}
