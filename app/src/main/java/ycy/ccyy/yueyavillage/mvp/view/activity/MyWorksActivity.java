package ycy.ccyy.yueyavillage.mvp.view.activity;

import android.content.Intent;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import java.util.List;

import ycy.ccyy.yueyavillage.R;
import ycy.ccyy.yueyavillage.adapter.MyWorksAdapter;
import ycy.ccyy.yueyavillage.base.MvpActivity;
import ycy.ccyy.yueyavillage.bean.WorkBean;
import ycy.ccyy.yueyavillage.mvp.contract.MyWorkContract;
import ycy.ccyy.yueyavillage.mvp.presenter.MyWorkPresenter;
import ycy.ccyy.yueyavillage.widget.BaseTitle;

public class MyWorksActivity extends MvpActivity<MyWorkPresenter> implements MyWorkContract.View {
    private RecyclerView worksList;

    @Override
    protected int getResourceId() {
        return R.layout.activity_my_works;
    }

    @Override
    protected void initIntent() {

    }

    @Override
    protected void initControll() {
        initTitle();
        worksList = findViewById(R.id.rv_works_list);
        worksList.setLayoutManager(new LinearLayoutManager(this));
        mPresenter.queryWorkList();
    }

    @Override
    protected void initObserable() {

    }

    @Override
    public void onRequestSuccess(List<WorkBean> response) {
        DividerItemDecoration divider = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        divider.setDrawable(getDrawable(R.drawable.list_divider_work));
        worksList.addItemDecoration(divider);
        MyWorksAdapter adapter = new MyWorksAdapter(this, response);
        adapter.setOnWorksItemClickListener(new MyWorksAdapter.OnWorksItemClickListener() {
            @Override
            public void onClick(WorkBean bean) {
                Intent it = new Intent(MyWorksActivity.this, MyWorkDetailActivity.class);
                it.putExtra("id", bean.id);
                MyWorksActivity.this.startActivity(it);
            }
        });
        worksList.setAdapter(adapter);
    }

    @Override
    public void onRequestFailed(String msg) {
        showToast(msg);
    }

    private void initTitle() {
        BaseTitle title = new BaseTitle(this, "我的作品");
        ImageView imageView = new ImageView(this);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        imageView.setImageResource(R.mipmap.publish_work);
        title.setRightContent(imageView);
    }
}
