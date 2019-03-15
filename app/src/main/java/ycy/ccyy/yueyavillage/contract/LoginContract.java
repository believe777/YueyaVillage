package ycy.ccyy.yueyavillage.contract;

import java.util.HashMap;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import ycy.ccyy.yueyavillage.base.BaseView;
import ycy.ccyy.yueyavillage.bean.LoginBean;

//登录测试
public interface LoginContract {
    interface Module {
        Observable<ResponseBody> login(HashMap map);
    }

    interface View extends BaseView {
        void onLoginSuccess(LoginBean loginBean);

        void onLoginFailed(Throwable throwable);
    }

    interface Presenter {
        void login(HashMap map);
    }
}
