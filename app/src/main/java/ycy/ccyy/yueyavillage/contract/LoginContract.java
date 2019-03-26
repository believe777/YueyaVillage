package ycy.ccyy.yueyavillage.contract;

import com.tencent.tauth.IUiListener;

import java.util.HashMap;

import ycy.ccyy.yueyavillage.base.BaseView;
import ycy.ccyy.yueyavillage.bean.UserInfoBean;

//登录测试
public interface LoginContract {
    interface Module {
    }

    interface View extends BaseView {

        void onLoginFailed(String errorMsg);

        void pleaseInstallQQ();

        void qqLogin();
    }

    interface Presenter {
        void qqLogin();
        IUiListener getQQLoginListener();
    }
}
