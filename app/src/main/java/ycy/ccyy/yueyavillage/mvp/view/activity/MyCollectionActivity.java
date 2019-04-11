package ycy.ccyy.yueyavillage.mvp.view.activity;

import android.content.Intent;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.List;

import ycy.ccyy.yueyavillage.R;
import ycy.ccyy.yueyavillage.adapter.MyCollectionAdapter;
import ycy.ccyy.yueyavillage.base.MvpActivity;
import ycy.ccyy.yueyavillage.bean.CollectionBean;
import ycy.ccyy.yueyavillage.mvp.contract.MyCollectionContract;
import ycy.ccyy.yueyavillage.mvp.presenter.MyCollectionPresenter;
import ycy.ccyy.yueyavillage.widget.BaseTitle;

public class MyCollectionActivity extends MvpActivity<MyCollectionPresenter> implements MyCollectionContract.View {
    private RecyclerView rvList;

    @Override
    protected int getResourceId() {
        return R.layout.activity_my_collection;
    }

    @Override
    protected void initIntent() {

    }

    @Override
    protected void initControll() {
        new BaseTitle(this, "我的收藏");
        rvList = findViewById(R.id.rv_my_collection_list);
        mPresenter.queryCollectionList();
    }

    @Override
    protected void initObserable() {

    }

    @Override
    public void onRequestSuccess(List<CollectionBean> bean) {
        rvList.setLayoutManager(new LinearLayoutManager(this));
        DividerItemDecoration divider = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        divider.setDrawable(getDrawable(R.drawable.list_divider_work));
        rvList.addItemDecoration(divider);
        MyCollectionAdapter adapter = new MyCollectionAdapter(bean, this);
        rvList.setAdapter(adapter);
        adapter.setOnCollectionItemClickListener(new MyCollectionAdapter.OnCollectionItemClickListener() {
            @Override
            public void onClick(CollectionBean bean) {
                Intent it = new Intent(MyCollectionActivity.this, MyCollectionDetailActivity.class);
                it.putExtra("id", bean.id);
                MyCollectionActivity.this.startActivity(it);
            }
        });
    }

    @Override
    public void onRequestFailed(String msg) {
        showToast(msg);
    }
}
