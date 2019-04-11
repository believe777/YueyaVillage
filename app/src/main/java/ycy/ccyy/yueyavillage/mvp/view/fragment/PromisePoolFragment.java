package ycy.ccyy.yueyavillage.mvp.view.fragment;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import ycy.ccyy.yueyavillage.R;
import ycy.ccyy.yueyavillage.adapter.PromisePoolAdapter;
import ycy.ccyy.yueyavillage.adapter.PromisePoolTransformer;
import ycy.ccyy.yueyavillage.base.MvpFragment;
import ycy.ccyy.yueyavillage.bean.PromisePoolBean;
import ycy.ccyy.yueyavillage.mvp.contract.PromisePoolContract;
import ycy.ccyy.yueyavillage.mvp.presenter.PromisePoolPresenter;
import ycy.ccyy.yueyavillage.mvp.view.activity.PublishPromiseActivity;
import ycy.ccyy.yueyavillage.util.CollectionUtil;
import ycy.ccyy.yueyavillage.widget.BaseHomeTitle;
import ycy.ccyy.yueyavillage.widget.PromisePoolPager;

public class PromisePoolFragment extends MvpFragment<PromisePoolPresenter> implements PromisePoolContract.View {
    private PromisePoolPager vpPager;

    @Override
    protected int getResourceId() {
        return R.layout.fragment_promise_pool;
    }

    @Override
    protected void initControll(View view) {
        initTitle(view);
        vpPager = view.findViewById(R.id.vp_promise_pool_pager);
        vpPager.setPageTransformer(true, new PromisePoolTransformer());
        vpPager.setOffscreenPageLimit(3);
        mPresenter.queryPromisePool();
    }

    @Override
    protected void initObserable() {

    }

    private void initTitle(View view) {
        ImageView imageView = new ImageView(getContext());
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(getActivity(), PublishPromiseActivity.class);
                startActivity(it);
            }
        });
        imageView.setImageResource(R.mipmap.publish_work);
        new BaseHomeTitle(view, "许愿池", null, imageView);
    }

    @Override
    public void onRequestSuccess(PromisePoolBean response) {
        if (!CollectionUtil.isEmpty(response.message)) {
            List<View> list = new ArrayList<>();
            for (String s : response.message) {
                View view = LayoutInflater.from(getContext()).inflate(R.layout.layout_promise_pool_item, vpPager, false);
                TextView tvMessage = view.findViewById(R.id.tv_promise_pool_message);
                tvMessage.setText(s);
                list.add(view);
            }
            vpPager.setAdapter(new PromisePoolAdapter(list));
        }
    }

    @Override
    public void onRequestFailed(String msg) {

    }
}
