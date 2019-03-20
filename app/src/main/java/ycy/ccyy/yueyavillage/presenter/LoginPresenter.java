package ycy.ccyy.yueyavillage.presenter;

import android.os.Message;
import android.text.TextUtils;

import com.tencent.connect.UserInfo;
import com.tencent.connect.common.Constants;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;

import org.json.JSONObject;

import ycy.ccyy.yueyavillage.YcyApplication;
import ycy.ccyy.yueyavillage.base.BasePresenter;
import ycy.ccyy.yueyavillage.contract.LoginContract;
import ycy.ccyy.yueyavillage.module.LoginModule;
import ycy.ccyy.yueyavillage.view.LoginActivity;

/**
 * QQ开放平台
 * appID:   101557229
 * appkey:  c5feff957addbf3ce0c907a052924fad
 */
//登录测试
public class LoginPresenter extends BasePresenter<LoginActivity> implements LoginContract.Presenter {
    private LoginContract.Module module;
    public static final String QQ_APP_ID = "101557229";
    public static final String QQ_APP_KEY = "c5feff957addbf3ce0c907a052924fad";
    private Tencent mTencent;

    private IUiListener iUiListener = new BaseUiListener() {
        @Override
        protected void doComplete(JSONObject values) {
            initOpenidAndToken(values);
            IUiListener listener = new IUiListener() {

                @Override
                public void onError(UiError e) {

                }

                @Override
                public void onComplete(final Object response) {
                    Message msg = new Message();
                    msg.obj = response;
                    msg.what = 0;
                    mView.mHandler.sendMessage(msg);
                }

                @Override
                public void onCancel() {

                }
            };
            UserInfo userInfo = new UserInfo(mView, mTencent.getQQToken());
            userInfo.getUserInfo(listener);
        }
    };

    private void initOpenidAndToken(JSONObject values) {
        try {
            String token = values.getString(Constants.PARAM_ACCESS_TOKEN);
            String expires = values.getString(Constants.PARAM_EXPIRES_IN);
            String openId = values.getString(Constants.PARAM_OPEN_ID);
            if (!TextUtils.isEmpty(token) && !TextUtils.isEmpty(expires)
                    && !TextUtils.isEmpty(openId)) {
                mTencent.setAccessToken(token, expires);
                mTencent.setOpenId(openId);
            }
        } catch (Exception e) {
        }
    }

    public LoginPresenter() {
        module = new LoginModule();
    }


    @Override
    public void qqLogin() {
        mTencent = Tencent.createInstance(QQ_APP_ID, YcyApplication.getApp());
        if (!mTencent.isSessionValid()) {
            mTencent.login(mView, "all", iUiListener);
        }
    }

    @Override
    public IUiListener getQQLoginListener() {
        return iUiListener;
    }

    private class BaseUiListener implements IUiListener {

        @Override
        public void onComplete(Object response) {
            if (null == response) {
                mView.onLoginFailed("登录失败");
                return;
            }
            JSONObject jsonResponse = (JSONObject) response;
            if (null != jsonResponse && jsonResponse.length() == 0) {
                mView.onLoginFailed("登录失败");
                return;
            }
            doComplete((JSONObject) response);
        }

        protected void doComplete(JSONObject values) {

        }

        @Override
        public void onError(UiError e) {
            mView.onLoginFailed("登录错误");
        }

        @Override
        public void onCancel() {
            mView.onLoginFailed("登录取消");
        }
    }
}
