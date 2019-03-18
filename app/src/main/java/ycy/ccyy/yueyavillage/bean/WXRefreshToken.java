package ycy.ccyy.yueyavillage.bean;

//刷新微信token
public class WXRefreshToken extends WXBaseBean {
    public String access_token;//	接口调用凭证
    public int expires_in;//	access_token接口调用凭证超时时间，单位（秒）
    public String refresh_token;//	用户刷新access_token
    public String openid;//	授权用户唯一标识
    public String scope;//	用户授权的作用域，使用逗号（,）分隔
}
