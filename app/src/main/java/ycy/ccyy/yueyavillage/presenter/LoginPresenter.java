package ycy.ccyy.yueyavillage.presenter;

import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import java.util.HashMap;

import ycy.ccyy.yueyavillage.YcyApplication;
import ycy.ccyy.yueyavillage.base.BasePresenter;
import ycy.ccyy.yueyavillage.contract.LoginContract;
import ycy.ccyy.yueyavillage.module.LoginModule;
import ycy.ccyy.yueyavillage.util.MD5Util;
import ycy.ccyy.yueyavillage.view.LoginActivity;
import ycy.ccyy.yueyavillage.wxapi.WXEntryActivity;

/**
 * QQ开放平台
 * appID:   101557229
 * appkey:  c5feff957addbf3ce0c907a052924fad
 * <p>
 * 微信开放平台
 * AppID：wxd39c1cb96f11b216
 * AppSecret:  021cf942b8a79db6b9a8ee0d82a94294
 */
//登录测试
public class LoginPresenter extends BasePresenter<LoginActivity> implements LoginContract.Presenter {
    private LoginContract.Module module;
    private IWXAPI api;

    public LoginPresenter() {
        module = new LoginModule();
    }

    @Override
    public void wxLogin(HashMap map) {
        //----------注册---------------
        api = WXAPIFactory.createWXAPI(YcyApplication.getApp(), WXEntryActivity.WX_APP_ID, true);
        api.registerApp(WXEntryActivity.WX_APP_ID);
        //-----------拉起授权登录页----------------
        final SendAuth.Req req = new SendAuth.Req();
        req.scope = "snsapi_userinfo";
        req.state = MD5Util.md5(WXEntryActivity.STATE);
        api.sendReq(req);
    }

    @Override
    public void wxGetToken(HashMap map) {

    }

    @Override
    public void wxCheckToken(HashMap map) {

    }

    @Override
    public void wxGetUserInfo(HashMap map) {

    }

    @Override
    public void wxRefreshToken(HashMap map) {

    }

    @Override
    public void qqLogin(HashMap map) {

    }
}
