package ycy.ccyy.yueyavillage.presenter;

import android.text.TextUtils;

import com.tencent.connect.UserInfo;
import com.tencent.connect.common.Constants;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;

import org.json.JSONException;
import org.json.JSONObject;

import ycy.ccyy.yueyavillage.YcyApplication;
import ycy.ccyy.yueyavillage.base.BasePresenter;
import ycy.ccyy.yueyavillage.bean.UserInfoBean;
import ycy.ccyy.yueyavillage.contract.LoginContract;
import ycy.ccyy.yueyavillage.module.LoginModule;
import ycy.ccyy.yueyavillage.util.DataCacheUtil;
import ycy.ccyy.yueyavillage.util.MD5Util;
import ycy.ccyy.yueyavillage.util.SharedPreferenceUtil;
import ycy.ccyy.yueyavillage.view.LoginActivity;

/**
 * QQ开放平台
 * appID:   101557229
 * appkey:  c5feff957addbf3ce0c907a052924fad
 */
//登录测试
public class LoginPresenter extends BasePresenter<LoginActivity> implements LoginContract.Presenter {
    public static final String LOGIN_KEY = "login_key";
    public static final String QQ_APP_ID = "101557229";
    public static final String QQ_APP_KEY = "c5feff957addbf3ce0c907a052924fad";
    private LoginContract.Module module;
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
                    JSONObject obj = (JSONObject) response;
                    if (obj.has("nickname")) {
                        try {
                            UserInfoBean userInfo = DataCacheUtil.getInstance().getUserInfo();
                            if (userInfo == null) {
                                userInfo = new UserInfoBean();
                            }
                            userInfo.userName = obj.getString("nickname");//姓名
                            userInfo.sex = obj.getString("gender");//性别
                            userInfo.userIcon = obj.getString("figureurl_qq_1");//头像
                            userInfo.province = obj.getString("province");//省份
                            userInfo.city = obj.getString("city");//城市
                            userInfo.year = obj.getString("year");//出生年
                            DataCacheUtil.getInstance().setUserInfoBean(userInfo);
                            userInfo.update();
                            mView.qqLogin();
                        } catch (JSONException e) {
                            e.printStackTrace();
                            mView.onLoginFailed("解析异常");
                        }
                    }
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
                UserInfoBean userInfoBean = DataCacheUtil.getInstance().getUserInfo();
                if (userInfoBean == null) {
                    userInfoBean = new UserInfoBean();
                }
                userInfoBean.token = token;
                userInfoBean.expires = expires;
                userInfoBean.openId = openId;
                userInfoBean.uId = MD5Util.md5("ycy" + openId);
                DataCacheUtil.getInstance().setUserInfoBean(userInfoBean);
                SharedPreferenceUtil.saveString(LOGIN_KEY, userInfoBean.uId);
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
