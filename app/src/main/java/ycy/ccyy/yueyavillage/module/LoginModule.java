package ycy.ccyy.yueyavillage.module;

import java.util.HashMap;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import ycy.ccyy.yueyavillage.contract.LoginContract;
import ycy.ccyy.yueyavillage.net.NetControl;

//登录测试
public class LoginModule implements LoginContract.Module {

    @Override
    public Observable<ResponseBody> login(HashMap map) {
        return NetControl.getInstance().getApi().login(map);
    }
}
