package ycy.ccyy.yueyavillage.net;

public class HttpContent {
    public final static Integer SUCCESS=1000;//返回成功
    public final static Integer FAIL=1001;//返回错误
    public final static Integer PARAMMISS=1002;//参数缺失
    public final static Integer NODATA=1003;//没有数据
    public static final Integer PARAMERROR = 1004;//参数错误

    //注册
    public static final String REGIST = "adduser";
    //
    public static final String UPLOAD = "upload";
    public static final String UPLOAD_BATCH = "upload/batch";
    public static final String UPLOADByBinary = "uploadByBinary";
}
