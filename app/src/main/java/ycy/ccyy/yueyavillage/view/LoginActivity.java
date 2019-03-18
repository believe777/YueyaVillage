package ycy.ccyy.yueyavillage.view;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.HashMap;

import ycy.ccyy.yueyavillage.R;
import ycy.ccyy.yueyavillage.base.MvpActivity;
import ycy.ccyy.yueyavillage.bean.UserInfoBean;
import ycy.ccyy.yueyavillage.bean.WXAccessTokenBean;
import ycy.ccyy.yueyavillage.bean.WXRefreshToken;
import ycy.ccyy.yueyavillage.contract.LoginContract;
import ycy.ccyy.yueyavillage.event.WXCodeEvent;
import ycy.ccyy.yueyavillage.presenter.LoginPresenter;
import ycy.ccyy.yueyavillage.util.LogUtil;
import ycy.ccyy.yueyavillage.widget.BaseTitle;
import ycy.ccyy.yueyavillage.wxapi.WXEntryActivity;

//登录测试
public class LoginActivity extends MvpActivity<LoginPresenter> implements LoginContract.View, View.OnClickListener {
    private EditText etLoginAccount;
    private EditText etLoginPwd;
    private ImageView ivLoginAccountClear;
    private Button btnLogin;

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
    protected void initControll() {
        new BaseTitle(this, "登录");
        etLoginAccount = findViewById(R.id.et_login_account);
        etLoginPwd = findViewById(R.id.et_login_pwd);
        btnLogin = findViewById(R.id.btn_login);
        ivLoginAccountClear = findViewById(R.id.iv_login_account_clear);
    }

    @Override
    protected void initObserable() {
        btnLogin.setOnClickListener(this);
        findViewById(R.id.iv_login_account_clear).setOnClickListener(this);
        findViewById(R.id.tv_login_forget_pwd);
        findViewById(R.id.btn_to_regist);
        etLoginAccount.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (etLoginAccount.getText().length() > 0) {
                    ivLoginAccountClear.setVisibility(View.VISIBLE);
                    if (etLoginPwd.getText().length() > 0) {
                        btnLogin.setEnabled(true);
                    } else {
                        btnLogin.setEnabled(false);
                    }
                } else {
                    ivLoginAccountClear.setVisibility(View.GONE);
                    btnLogin.setEnabled(false);
                }
            }
        });
        etLoginPwd.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (etLoginPwd.getText().length() > 0 && etLoginAccount.getText().length() > 0) {
                    btnLogin.setEnabled(true);
                } else {
                    btnLogin.setEnabled(false);
                }
            }
        });
    }

    @Override
    public void onWXLoginSuccess(UserInfoBean userInfoBean) {

    }

    @Override
    public void onGetWXTokenSuccess(WXAccessTokenBean wxAccessTokenBean) {

    }

    @Override
    public void onRefreshWXTokenSuccess(WXRefreshToken wxRefreshToken) {

    }

    @Override
    public void onCheckWXTokenSuccess() {

    }

    @Override
    public void onLoginFailed(Throwable throwable) {
        LogUtil.e("onLoginFailed");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_login:
                break;
            case R.id.iv_login_account_clear:
                etLoginAccount.setText("");
                break;
            case R.id.tv_login_forget_pwd:
                break;
            case R.id.btn_to_regist:
                break;
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(WXCodeEvent wxCodeEvent) {
        HashMap tokenMap = new HashMap<>();
        tokenMap.put("appid", WXEntryActivity.WX_APP_ID);
        tokenMap.put("secret", WXEntryActivity.WX_APP_SECRET);
        tokenMap.put("code", wxCodeEvent.code);
        tokenMap.put("grant_type", "authorization_code");
        presenter.wxGetToken(tokenMap);
    }
}
