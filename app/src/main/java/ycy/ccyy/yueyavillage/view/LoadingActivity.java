package ycy.ccyy.yueyavillage.view;

import android.content.Intent;
import android.support.annotation.NonNull;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;
import ycy.ccyy.yueyavillage.R;
import ycy.ccyy.yueyavillage.base.BaseActivity;
import ycy.ccyy.yueyavillage.util.PermissionsUtil;

//loading测试
public class LoadingActivity extends BaseActivity {
    @Override
    protected int getResourceId() {
        return R.layout.activity_loading;
    }

    @Override
    protected void bindPresenter() {

    }

    @Override
    protected void initControll() {
        if (!PermissionsUtil.hadNecessaryPermissions()) {
            PermissionsUtil.requestNecessaryPermissions(this);
        } else {
            waiting();
        }
    }

    @Override
    protected void initObserable() {

    }

    void waiting() {
        Observable.timer(2, TimeUnit.SECONDS).subscribe(new Consumer<Long>() {
            @Override
            public void accept(Long aLong) throws Exception {
                jumpToLoginPage();
            }
        });
    }

    void jumpToLoginPage() {
        Intent it = new Intent(this, LoginActivity.class);
        startActivity(it);
    }

    void jumpToHomePage() {
        Intent it = new Intent(this, HomeActivity.class);
        startActivity(it);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == PermissionsUtil.NecessaryPermissionsCode) {
            waiting();
        }
    }
}
