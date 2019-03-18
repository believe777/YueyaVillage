package ycy.ccyy.yueyavillage.util;

import java.util.Collection;

public class CollectionUtil {
    public static boolean isEmpty(Collection collection) {
        if (null == collection) {
            return true;
        } else {
            return collection.isEmpty();
        }
    }

    public static <E> E getFirstItem(Collection<E> collection) {
        return isEmpty(collection) ? null : collection.iterator().next();
    }
}
