package ycy.ccyy.yueyavillage.util;

import ycy.ccyy.yueyavillage.bean.UserInfoBean;

public class DataCacheUtil {
    private static DataCacheUtil dataCacheUtil = null;
    private UserInfoBean userInfoBean;


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
}
