package ycy.ccyy.yueyavillage.util;

import com.tencent.tauth.Tencent;

import ycy.ccyy.yueyavillage.YcyApplication;
import ycy.ccyy.yueyavillage.bean.UserInfoBean;
import ycy.ccyy.yueyavillage.presenter.LoginPresenter;

public class DataCacheUtil {
    private Tencent mTencent;
    private static DataCacheUtil dataCacheUtil = null;
    private UserInfoBean userInfoBean;

    DataCacheUtil() {
        mTencent = Tencent.createInstance(LoginPresenter.QQ_APP_ID, YcyApplication.getApp());
    }

    public static DataCacheUtil getInstance() {
        if (dataCacheUtil == null) {
            synchronized (DataCacheUtil.class) {
                if (dataCacheUtil == null) {
                    dataCacheUtil = new DataCacheUtil();
                }
            }
        }
        return dataCacheUtil;
    }

    public void setUserInfoBean(UserInfoBean userInfoBean) {
        this.userInfoBean = userInfoBean;
    }

    public UserInfoBean getUserInfo() {
        return userInfoBean;
    }

    public boolean isLogin() {
        return userInfoBean != null;
    }

    public Tencent getTencent(){
        return mTencent;
    }
}
