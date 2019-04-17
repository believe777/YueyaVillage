package ycy.ccyy.yueyavillage.mvp.view.fragment;

import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewStub;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import ycy.ccyy.yueyavillage.R;
import ycy.ccyy.yueyavillage.base.BaseFragment;
import ycy.ccyy.yueyavillage.mvp.view.activity.ChooseHeadFrameActivity;
import ycy.ccyy.yueyavillage.mvp.view.activity.MyCollectionActivity;
import ycy.ccyy.yueyavillage.mvp.view.activity.MyWorksActivity;
import ycy.ccyy.yueyavillage.util.DataCacheUtil;
import ycy.ccyy.yueyavillage.widget.BaseHomeTitle;

public class YueyaVillageFragment extends BaseFragment implements View.OnClickListener {
    private TextView tvUserName;
    private TextView tvWorks, tvCollection, tvInformation, tvRanking;

    @Override
    protected int getResourceId() {
        return R.layout.fragment_yueya_village;
    }

    @Override
    protected void initControll(View view) {
        new BaseHomeTitle(view, "月芽村");
        tvUserName = view.findViewById(R.id.tv_village_user_name);
        if (DataCacheUtil.getInstance().getUserInfo() != null) {
            initUserIcon(view);
            tvUserName.setText(DataCacheUtil.getInstance().getUserInfo().userName);
        }
        tvWorks = view.findViewById(R.id.tv_village_works);
        tvCollection = view.findViewById(R.id.tv_village_collection);
        tvInformation = view.findViewById(R.id.tv_village_information);
        tvRanking = view.findViewById(R.id.tv_village_ranking);
    }

    @Override
    protected void initObserable() {
        tvWorks.setOnClickListener(this);
        tvCollection.setOnClickListener(this);
        tvInformation.setOnClickListener(this);
        tvRanking.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent it = new Intent();
        switch (v.getId()) {
            case R.id.tv_village_works:
                it.setClass(getActivity(), MyWorksActivity.class);
                startActivity(it);
                break;
            case R.id.tv_village_collection:
                it.setClass(getActivity(), MyCollectionActivity.class);
                startActivity(it);
                break;
            case R.id.tv_village_information:

                break;
            case R.id.tv_village_ranking:

                break;
        }
    }

    private void initUserIcon(View view) {
        int borderType = DataCacheUtil.getInstance().getUserInfo().borderType;
        switch (borderType) {
            case ChooseHeadFrameActivity.BORDER_DEFAULT:
                inflatIcon(view, R.id.vs_default);
                break;
            case ChooseHeadFrameActivity.BORDER_DOUBAN:
                inflatIcon(view, R.id.vs_douban);
                break;
            case ChooseHeadFrameActivity.BORDER_TIEBA:
                inflatIcon(view, R.id.vs_tieba);
                break;
            case ChooseHeadFrameActivity.BORDER_HUPU:
                inflatIcon(view, R.id.vs_hupu);
                break;
            case ChooseHeadFrameActivity.BORDER_DOKI:
                inflatIcon(view, R.id.vs_doki);
                break;
            case ChooseHeadFrameActivity.BORDER_WEIBO:
                inflatIcon(view, R.id.vs_weibo);
                break;
            case ChooseHeadFrameActivity.BORDER_NGA:
                inflatIcon(view, R.id.vs_nga);
                break;
            case ChooseHeadFrameActivity.BORDER_BILIBILI:
                inflatIcon(view, R.id.vs_bilibili);
                break;
            case ChooseHeadFrameActivity.BORDER_ZHIHU:
                inflatIcon(view, R.id.vs_zhihu);
                break;
        }
    }

    private void inflatIcon(View view, int showView) {
        ViewStub viewStub = view.findViewById(showView);
        View borderView = viewStub.inflate();
        SimpleDraweeView svUserIcon = borderView.findViewById(R.id.sv_village_user_icon);
        if(!TextUtils.isEmpty(DataCacheUtil.getInstance().getUserInfo().userBigIcon)) {
            svUserIcon.setImageURI(Uri.parse(DataCacheUtil.getInstance().getUserInfo().userBigIcon));
        } else {
            svUserIcon.setImageURI(Uri.parse(DataCacheUtil.getInstance().getUserInfo().userIcon));
        }
    }
}
