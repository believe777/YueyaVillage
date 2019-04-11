package ycy.ccyy.yueyavillage.mvp.contract;

import android.app.Activity;

import com.tencent.tauth.IUiListener;

import java.util.HashMap;

import io.reactivex.Observable;
import ycy.ccyy.yueyavillage.base.AbstractView;
import ycy.ccyy.yueyavillage.bean.ResponseBase;

//登录测试
public interface LoginContract {
    interface Module {
        Observable<ResponseBase> regist(HashMap map);
    }

    interface View extends AbstractView {

        void onLoginFailed(String errorMsg);

        void pleaseInstallQQ();

        void qqLogin();
    }

    interface Presenter {
        void qqLogin(Activity activity);

        IUiListener getQQLoginListener();
    }
}
