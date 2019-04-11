package ycy.ccyy.yueyavillage.mvp.view.activity;

import android.graphics.Color;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import ycy.ccyy.yueyavillage.R;
import ycy.ccyy.yueyavillage.base.MvpActivity;
import ycy.ccyy.yueyavillage.bean.ResponseBase;
import ycy.ccyy.yueyavillage.mvp.contract.PublishPromiseContract;
import ycy.ccyy.yueyavillage.mvp.presenter.PublishPromisePresenter;
import ycy.ccyy.yueyavillage.widget.BaseTitle;

public class PublishPromiseActivity extends MvpActivity<PublishPromisePresenter> implements PublishPromiseContract.View {
    private EditText etMessage;

    @Override
    protected int getResourceId() {
        return R.layout.activity_publish_promise;
    }

    @Override
    protected void initIntent() {

    }

    @Override
    protected void initControll() {
        initTitle();
        etMessage = findViewById(R.id.et_promise_message);
    }

    @Override
    protected void initObserable() {

    }

    private void initTitle() {
        TextView cancel = new TextView(this);
        cancel.setText("取消");
        cancel.setTextSize(12);
        cancel.setTextColor(Color.parseColor("#615F6A"));
        TextView publish = new TextView(this);
        publish.setText("发布");
        publish.setTextColor(Color.parseColor("#615F6A"));
        publish.setTextSize(12);
        new BaseTitle(this, "许愿", cancel, publish);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        publish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.publishPresenter(etMessage.getText().toString());
            }
        });
    }

    @Override
    public void onRequestSuccess(ResponseBase responseBase) {
        showToast("越哥与你同在");
    }

    @Override
    public void onRequestFailed(String msg) {
        showToast(msg);
    }
}
