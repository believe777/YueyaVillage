package ycy.ccyy.yueyavillage.net;

import android.content.Context;

import com.facebook.cache.disk.DiskCacheConfig;
import com.facebook.common.internal.Supplier;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.imagepipeline.core.ImagePipelineConfig;

import java.io.File;

import ycy.ccyy.yueyavillage.util.LogUtil;
import ycy.ccyy.yueyavillage.util.StorageUtils;

public class FrescoControl {
    public static void init(Context context) {
        DiskCacheConfig diskCacheConfig = DiskCacheConfig.newBuilder(context)
                .setBaseDirectoryPathSupplier(new Supplier() {
                    public File get() {
                        return StorageUtils.getCacheDir(StorageUtils.FILE_TYPE.ROOT);
                    }
                })
                .setBaseDirectoryName(StorageUtils.IMAGE)
                .setMaxCacheSize(41943040L)
                .setMaxCacheSizeOnLowDiskSpace(10485760L)
                .setMaxCacheSizeOnVeryLowDiskSpace(2097152L)
                .build();
        ImagePipelineConfig config = ImagePipelineConfig.newBuilder(context)
                .setMainDiskCacheConfig(diskCacheConfig).build();
        try {
            Fresco.initialize(context, config);
        } catch (Exception e) {
            LogUtil.d("Fresco.initialize error");
        }
    }
}
