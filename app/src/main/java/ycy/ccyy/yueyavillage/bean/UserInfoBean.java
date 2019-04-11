package ycy.ccyy.yueyavillage.bean;

import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

import ycy.ccyy.yueyavillage.util.DataBaseUtil;

@Table(database = DataBaseUtil.class)
public class UserInfoBean extends BaseModel {
    @PrimaryKey
    public String uId;
    @Column
    public String user_id;//服务器用户id
    @Column
    public String openId;
//    @Column
//    public String token;
    @Column
    public String expires;
    @Column
    public String userName;//姓名
    @Column
    public String sex;//性别
    @Column
    public String userIcon;//头像
    @Column
    public String province;//省份
    @Column
    public String city;//城市
    @Column
    public String year;//出生年
    @Column
    public int borderType;//选择边框样式
}
