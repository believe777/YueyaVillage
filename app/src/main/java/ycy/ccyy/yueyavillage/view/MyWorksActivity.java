package ycy.ccyy.yueyavillage.view;

import android.support.v7.widget.RecyclerView;

import ycy.ccyy.yueyavillage.R;
import ycy.ccyy.yueyavillage.base.BaseActivity;

public class MyWorksActivity extends BaseActivity {
    private RecyclerView worksList;
    @Override
    protected int getResourceId() {
        return R.layout.activity_my_works;
    }

    @Override
    protected void bindPresenter() {

    }

    @Override
    protected void initIntent() {

    }

    @Override
    protected void initControll() {
        worksList = findViewById(R.id.rv_works_list);
    }

    @Override
    protected void initObserable() {

    }
}
