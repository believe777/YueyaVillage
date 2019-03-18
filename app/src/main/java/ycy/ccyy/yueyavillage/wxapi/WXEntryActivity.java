package ycy.ccyy.yueyavillage.wxapi;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;

import org.greenrobot.eventbus.EventBus;

import ycy.ccyy.yueyavillage.event.WXCodeEvent;
import ycy.ccyy.yueyavillage.util.MD5Util;

//微信登录回调
public class WXEntryActivity extends Activity implements IWXAPIEventHandler {
    public static final String STATE = "向杨而生，超越一切";
    public static final String WX_APP_ID = "wxd39c1cb96f11b216";
    public static final String WX_APP_SECRET = "021cf942b8a79db6b9a8ee0d82a94294";
    public static final String QQ_APP_ID = "101557229";
    public static final String QQ_APP_KEY = "c5feff957addbf3ce0c907a052924fad";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
    }

    @Override
    public void onReq(BaseReq baseReq) {

    }

    @Override
    public void onResp(BaseResp baseResp) {
        switch (baseResp.errCode) {
            case BaseResp.ErrCode.ERR_OK://用户同意
                SendAuth.Resp resp = ((SendAuth.Resp) baseResp);
                //校验结果是否被篡改
                if (MD5Util.md5(STATE).equals(resp.state)) {
                    EventBus.getDefault().post(new WXCodeEvent(resp.code));
                    finish();
                }
                break;
            case BaseResp.ErrCode.ERR_USER_CANCEL://用户取消
                break;
            case BaseResp.ErrCode.ERR_AUTH_DENIED://用户拒绝授权
                break;
        }
    }
}
