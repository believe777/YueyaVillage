package ycy.ccyy.yueyavillage.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;

public abstract class BaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getResourceId());
        bindPresenter();
        initIntent();
        initControll();
        initObserable();
    }

    //xml资源
    protected abstract int getResourceId();

    //绑定presenter
    protected abstract void bindPresenter();

    protected abstract void initIntent();

    //初始化控件
    protected abstract void initControll();

    //初始化监听
    protected abstract void initObserable();

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            finish();
        }
        return super.onKeyUp(keyCode, event);
    }
}
