package ycy.ccyy.yueyavillage.mvp.module;

import java.util.HashMap;

import javax.inject.Inject;

import dagger.Module;
import io.reactivex.Observable;
import ycy.ccyy.yueyavillage.bean.ResponseBase;
import ycy.ccyy.yueyavillage.mvp.contract.LoginContract;
import ycy.ccyy.yueyavillage.net.NetControl;

//登录测试
public class LoginModule implements LoginContract.Module {
    @Override
    public Observable<ResponseBase> regist(HashMap map) {
        return NetControl.getInstance().getApi().regist(map);
    }
}
