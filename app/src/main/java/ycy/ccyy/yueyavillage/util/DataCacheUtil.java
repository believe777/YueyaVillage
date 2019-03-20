package ycy.ccyy.yueyavillage.util;

public class DataCacheUtil {
    private static DataCacheUtil dataCacheUtil = null;

    public DataCacheUtil getInstance() {
        if (dataCacheUtil == null) {
            synchronized (DataCacheUtil.class) {
                if (dataCacheUtil == null) {
                    dataCacheUtil = new DataCacheUtil();
                }
            }
        }
        return dataCacheUtil;
    }
}
