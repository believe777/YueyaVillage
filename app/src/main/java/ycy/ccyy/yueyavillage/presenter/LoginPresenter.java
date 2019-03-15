package ycy.ccyy.yueyavillage.presenter;

import java.util.HashMap;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import okhttp3.ResponseBody;
import ycy.ccyy.yueyavillage.base.BasePresenter;
import ycy.ccyy.yueyavillage.bean.LoginBean;
import ycy.ccyy.yueyavillage.contract.LoginContract;
import ycy.ccyy.yueyavillage.module.LoginModule;
import ycy.ccyy.yueyavillage.net.RxScheduler;
import ycy.ccyy.yueyavillage.view.LoginActivity;

//登录测试
public class LoginPresenter extends BasePresenter<LoginActivity> implements LoginContract.Presenter {
    private LoginContract.Module module;

    public LoginPresenter() {
        module = new LoginModule();
    }

    @Override
    public void login(HashMap map) {
        LoginBean bean = new LoginBean();
        bean.userName = (String) map.get("account");
        module.login(map)
                .compose(RxScheduler.<ResponseBody>observableToMain())
                .subscribe(new Observer<ResponseBody>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ResponseBody loginBean) {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
        mView.onLoginSuccess(bean);
    }
}
