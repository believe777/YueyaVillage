package ycy.ccyy.yueyavillage.database;

import com.raizlabs.android.dbflow.annotation.Database;
import com.raizlabs.android.dbflow.sql.language.SQLOperator;
import com.raizlabs.android.dbflow.sql.language.SQLite;
import com.raizlabs.android.dbflow.sql.language.property.Property;
import com.raizlabs.android.dbflow.structure.BaseModel;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Iterator;

import javax.inject.Inject;

import ycy.ccyy.yueyavillage.util.DESedeUtil;
import ycy.ccyy.yueyavillage.util.KeysUtil;

//加密数据存储
@Database(name = DataBaseImpl.NAME, version = DataBaseImpl.VERSION)
public class DataBaseImpl implements DataBase {
    public static final String NAME = "YueyaVillageDataBase";
    public static final int VERSION = 1;

    @Inject
    public DataBaseImpl() {
    }

    @Override
    public <T extends BaseModel> void insert(T bean) {
        try {
            T newBean = (T) bean.getClass().newInstance();
            Class clazz = bean.getClass();
            Class newClazz = newBean.getClass();
            Field[] fields = clazz.getFields();
            for (Field field : fields) {
                field.setAccessible(true);
                Object obj = field.get(bean);
                if (!(obj instanceof Number) && obj != null) {
                    Field newField = newClazz.getField(field.getName());
                    newField.setAccessible(true);
                    newField.set(newBean,DESedeUtil.encrypt(obj.toString(), KeysUtil.getKey()));
                    //field.set(bean, DESedeUtil.encrypt(obj.toString(), KeysUtil.getKey()));
                }
            }
            newBean.insert();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public <T extends BaseModel> T query(HashMap<Property, Object> params, Class<T> clazz) {
        try {
            SQLOperator[] sqlOperators = new SQLOperator[params.size()];
            Iterator<Property> iterator = params.keySet().iterator();
            int index = 0;
            while (iterator.hasNext()) {
                Property key = iterator.next();
                Object value = DESedeUtil.encrypt(params.get(key).toString(), KeysUtil.getKey());
                sqlOperators[index] = key.eq(value);
            }
            T response = SQLite.select().from(clazz).where(sqlOperators).querySingle();
            Class resClass = response.getClass();
            Field[] fields = resClass.getFields();
            for (Field field : fields) {
                field.setAccessible(true);
                Object obj = field.get(response);
                if (!(obj instanceof Number) && obj != null) {
                    field.set(response, DESedeUtil.decrypt(obj.toString(), KeysUtil.getKey()));
                }
            }
            return response;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
