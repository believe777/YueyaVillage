package ycy.ccyy.yueyavillage.view;

import android.content.Intent;
import android.support.annotation.NonNull;

import com.tencent.tauth.Tencent;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.functions.Consumer;
import ycy.ccyy.yueyavillage.R;
import ycy.ccyy.yueyavillage.base.BaseActivity;
import ycy.ccyy.yueyavillage.presenter.LoginPresenter;
import ycy.ccyy.yueyavillage.util.DataCacheUtil;
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
    protected void initIntent() {

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
        Observable.create(new ObservableOnSubscribe<Boolean>() {

            @Override
            public void subscribe(ObservableEmitter<Boolean> emitter) throws Exception {
                if (DataCacheUtil.getInstance().isLogin()) {
                    Tencent mTencent = DataCacheUtil.getInstance().getTencent();
                    //token过期
                    if (!mTencent.checkSessionValid(LoginPresenter.QQ_APP_ID)) {
                        emitter.onNext(true);
                        emitter.onComplete();
                    }
                }
                emitter.onNext(false);
                emitter.onComplete();
            }
        }).delay(2, TimeUnit.SECONDS).subscribe(new Consumer<Boolean>() {
            @Override
            public void accept(Boolean aLong) throws Exception {
                if (aLong) {
                    jumpToHomePage();
                } else {
                    jumpToLoginPage();
                }
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
