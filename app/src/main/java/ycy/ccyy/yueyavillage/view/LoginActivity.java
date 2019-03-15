package ycy.ccyy.yueyavillage.view;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import java.util.HashMap;

import ycy.ccyy.yueyavillage.R;
import ycy.ccyy.yueyavillage.base.MvpActivity;
import ycy.ccyy.yueyavillage.bean.LoginBean;
import ycy.ccyy.yueyavillage.contract.LoginContract;
import ycy.ccyy.yueyavillage.presenter.LoginPresenter;
import ycy.ccyy.yueyavillage.util.LogUtil;
import ycy.ccyy.yueyavillage.widget.BaseTitle;

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
    public void onLoginSuccess(LoginBean loginBean) {
        LogUtil.d("onLoginSuccess");
    }

    @Override
    public void onLoginFailed(Throwable throwable) {
        LogUtil.e("onLoginFailed");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_login:
                HashMap map = new HashMap();
                map.put("username", etLoginAccount.getText().toString());
                map.put("password", etLoginPwd.getText().toString());
                presenter.login(map);
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
}
