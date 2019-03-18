package ycy.ccyy.yueyavillage.module;

import java.util.HashMap;

import io.reactivex.Observable;
import ycy.ccyy.yueyavillage.bean.UserInfoBean;
import ycy.ccyy.yueyavillage.bean.WXAccessTokenBean;
import ycy.ccyy.yueyavillage.bean.WXBaseBean;
import ycy.ccyy.yueyavillage.bean.WXRefreshToken;
import ycy.ccyy.yueyavillage.contract.LoginContract;
import ycy.ccyy.yueyavillage.net.NetControl;

//登录测试
public class LoginModule implements LoginContract.Module {

    @Override
    public Observable<WXAccessTokenBean> getWXToken(HashMap map) {
        return NetControl.getInstance().getWX().getWXToken(map);
    }

    @Override
    public Observable<WXRefreshToken> refreshWXToken(HashMap map) {
        return NetControl.getInstance().getWX().refreshWXToken(map);
    }

    @Override
    public Observable<WXBaseBean> checkWXToken(HashMap map) {
        return NetControl.getInstance().getWX().checkToken(map);
    }

    @Override
    public Observable<UserInfoBean> getUserInfo(HashMap map) {
        return NetControl.getInstance().getWX().getUserInfo(map);
    }
}
