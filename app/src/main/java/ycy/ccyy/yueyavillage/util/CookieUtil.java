package ycy.ccyy.yueyavillage.util;

//cookie存取
public class CookieUtil {
    private volatile static CookieUtil instance = null;

    private String cookie;

    public static CookieUtil getInstance() {
        if (instance == null) {
            synchronized (CookieUtil.class) {
                if (instance == null) {
                    instance = new CookieUtil();
                }
            }
        }
        return instance;
    }

    // TODO: 2019/3/18 cookie存数据库，每次拉起应用先去数据库查看是否有cookie；用于免登陆 
    public void saveCookie(String cookie) {

    }

    public String getCookie() {
        return cookie;
    }

    public void clearCookie(){

    }
}