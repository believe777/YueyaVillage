package ycy.ccyy.yueyavillage.bean;

/**
 * {
 * "openid":"OPENID",
 * "nickname":"NICKNAME",
 * "sex":1,
 * "province":"PROVINCE",
 * "city":"CITY",
 * "country":"COUNTRY",
 * "headimgurl": "http://wx.qlogo.cn/mmopen/g3MonUZtNHkdmzicIlibx6iaFqAc56vxLSUfpb6n5WKSYVY0ChQKkiaJSgQ1dZuTOgvLLrhJbERQQ4eMsv84eavHiaiceqxibJxCfHe/0",
 * "privilege":[
 * "PRIVILEGE1",
 * "PRIVILEGE2"
 * ],
 * "unionid": " o6_bmasdasdsad6_2sgVt7hMZOPfL"
 * }
 */
public class UserInfoBean {
    public String openid;//普通用户的标识，对当前开发者帐号唯一
    public String nickname;//普通用户昵称
    public int sex;//普通用户性别，1为男性，2为女性
    public String provice;//普通用户个人资料填写的省份
    public String city;//普通用户个人资料填写的城市
    public String country;//国家，如中国为CN
    public String headimgurl;//用户头像，最后一个数值代表正方形头像大小（有0、46、64、96、132数值可选，0代表640*640正方形头像），用户没有头像时该项为空
    public String[] privilege;//用户特权信息，json数组，如微信沃卡用户为（chinaunicom）
    public String unionid;//用户统一标识。针对一个微信开放平台帐号下的应用，同一用户的unionid是唯一的。
}
