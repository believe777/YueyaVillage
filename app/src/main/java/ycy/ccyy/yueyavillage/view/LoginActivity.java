package ycy.ccyy.yueyavillage.view;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.TextView;

import com.tencent.connect.common.Constants;
import com.tencent.tauth.Tencent;

import org.json.JSONException;
import org.json.JSONObject;

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
    public Handler mHandler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 0) {
                JSONObject response = (JSONObject) msg.obj;
                if (response.has("nickname")) {
                    try {
                        ((TextView) findViewById(R.id.test)).setText("" + response);
                        showToast("登录成功");
                        String userName = response.getString("nickname");//姓名
                        String sex = response.getString("gender");//性别
                        String userIcon = response.getString("figureurl_qq_2");//头像
                        String province = response.getString("province");//省份
                        String city = response.getString("city");//城市
                        String year = response.getString("year");//出生年
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

    };

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

}
