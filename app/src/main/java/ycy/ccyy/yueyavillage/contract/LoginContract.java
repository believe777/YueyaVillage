package ycy.ccyy.yueyavillage.contract;

import java.util.HashMap;

import io.reactivex.Observable;
import ycy.ccyy.yueyavillage.base.BaseView;
import ycy.ccyy.yueyavillage.bean.UserInfoBean;
import ycy.ccyy.yueyavillage.bean.WXAccessTokenBean;
import ycy.ccyy.yueyavillage.bean.WXBaseBean;
import ycy.ccyy.yueyavillage.bean.WXRefreshToken;

//登录测试
public interface LoginContract {
    interface Module {
        Observable<WXAccessTokenBean> getWXToken(HashMap map);

        Observable<WXRefreshToken> refreshWXToken(HashMap map);

        Observable<WXBaseBean> checkWXToken(HashMap map);

        Observable<UserInfoBean> getUserInfo(HashMap map);
    }

    interface View extends BaseView {
        void onWXLoginSuccess(UserInfoBean userInfoBean);

        void onGetWXTokenSuccess(WXAccessTokenBean wxAccessTokenBean);

        void onRefreshWXTokenSuccess(WXRefreshToken wxRefreshToken);

        void onCheckWXTokenSuccess();

        void onLoginFailed(Throwable throwable);
    }

    interface Presenter {
        void wxLogin(HashMap map);

        void wxGetToken(HashMap map);

        void wxCheckToken(HashMap map);

        void wxGetUserInfo(HashMap map);

        void wxRefreshToken(HashMap map);

        void qqLogin(HashMap map);
    }
}
