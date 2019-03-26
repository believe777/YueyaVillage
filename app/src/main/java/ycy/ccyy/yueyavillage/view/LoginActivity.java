package ycy.ccyy.yueyavillage.view;

import android.content.Intent;
import android.view.View;

import com.tencent.connect.common.Constants;
import com.tencent.tauth.Tencent;

import ycy.ccyy.yueyavillage.R;
import ycy.ccyy.yueyavillage.base.MvpActivity;
import ycy.ccyy.yueyavillage.contract.LoginContract;
import ycy.ccyy.yueyavillage.presenter.LoginPresenter;

/**
 * 微信登录流程
 * ①初次登录：  用户授权获取code --> 通过code获取access_token，并缓存 --> 通过access_token获取用户信息
 * ②非初次登录：检查缓存access_token是否有效 --> 无效则通过refresh_token刷新access_token --> 通过access_token获取用户信息
 */
//登录测试
public class LoginActivity extends MvpActivity<LoginPresenter> implements LoginContract.View, View.OnClickListener {
    @Override
    protected int getResourceId() {
        return R.layout.activity_login;
    }

    @Override
    protected void bindPresenter() {
        presenter = new LoginPresenter();
        presenter.onAttach(this);
    }

    @Override
    protected void initIntent() {

    }

    @Override
    protected void initControll() {

    }

    @Override
    protected void initObserable() {
        findViewById(R.id.btn_login_wx).setOnClickListener(this);
        findViewById(R.id.btn_login_qq).setOnClickListener(this);
    }

    //登录失败
    @Override
    public void onLoginFailed(String errorMsg) {
        showToast(errorMsg);
    }

    @Override
    public void pleaseInstallQQ() {
        showToast("请先安装QQ");
    }

    @Override
    public void qqLogin() {
        showToast("登录成功");
        jumpToHomePage();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_login_qq:
                presenter.qqLogin();
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == Constants.REQUEST_LOGIN || requestCode == Constants.REQUEST_APPBAR) {
            Tencent.onActivityResultData(requestCode, resultCode, data, presenter.getQQLoginListener());
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void jumpToHomePage() {
        Intent it = new Intent(this, HomeActivity.class);
        startActivity(it);
    }

}
