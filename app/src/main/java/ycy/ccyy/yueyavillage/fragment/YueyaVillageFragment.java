package ycy.ccyy.yueyavillage.fragment;

import android.net.Uri;
import android.view.View;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import ycy.ccyy.yueyavillage.R;
import ycy.ccyy.yueyavillage.base.MvpFragment;
import ycy.ccyy.yueyavillage.util.DataCacheUtil;
import ycy.ccyy.yueyavillage.widget.BaseHomeTitle;

public class YueyaVillageFragment extends MvpFragment implements View.OnClickListener {
    private SimpleDraweeView svUserIcon;
    private TextView tvUserName;

    @Override
    protected int getResourceId() {
        return R.layout.fragment_yueya_village;
    }

    @Override
    protected void initControll(View view) {
        new BaseHomeTitle(getActivity(), "月芽村");
        svUserIcon = getView().findViewById(R.id.sv_village_user_icon);
        tvUserName = getView().findViewById(R.id.tv_village_user_name);
        svUserIcon.setImageURI(Uri.parse(DataCacheUtil.getInstance().getUserInfo().userIcon));
        tvUserName.setText(DataCacheUtil.getInstance().getUserInfo().userName);
    }

    @Override
    protected void initObserable() {
        getView().findViewById(R.id.tv_village_works).setOnClickListener(this);
        getView().findViewById(R.id.tv_village_collection).setOnClickListener(this);
        getView().findViewById(R.id.tv_village_information).setOnClickListener(this);
        getView().findViewById(R.id.tv_village_ranking).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_village_works:

                break;
            case R.id.tv_village_collection:

                break;
            case R.id.tv_village_information:

                break;
            case R.id.tv_village_ranking:

                break;
        }
    }
}
