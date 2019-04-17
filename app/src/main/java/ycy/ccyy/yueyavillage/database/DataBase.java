package ycy.ccyy.yueyavillage.database;

import com.raizlabs.android.dbflow.sql.language.property.Property;
import com.raizlabs.android.dbflow.structure.BaseModel;

import java.util.HashMap;

public interface DataBase {
    <T extends BaseModel> void insert(T bean);
    <T extends BaseModel> T query(HashMap<Property, Object> params, Class<T> clazz);
}
