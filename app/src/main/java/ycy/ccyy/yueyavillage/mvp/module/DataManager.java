package ycy.ccyy.yueyavillage.mvp.module;

import com.raizlabs.android.dbflow.sql.language.property.Property;
import com.raizlabs.android.dbflow.structure.BaseModel;

import java.util.HashMap;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import ycy.ccyy.yueyavillage.bean.ResponseBase;
import ycy.ccyy.yueyavillage.database.DataBase;
import ycy.ccyy.yueyavillage.net.HttpHelper;

public class DataManager implements HttpHelper,DataBase {
    private HttpHelper httpHelper;
    private DataBase dataBase;

    public DataManager(HttpHelper httpHelper, DataBase dataBase) {
        this.httpHelper = httpHelper;
        this.dataBase = dataBase;
    }

    @Override
    public Observable<ResponseBase> regist(HashMap map) {
        return httpHelper.regist(map);
    }

    @Override
    public Observable<ResponseBase> upload(RequestBody requestBody) {
        return httpHelper.upload(requestBody);
    }

    @Override
    public <T extends BaseModel> void insert(T bean) {
        dataBase.insert(bean);
    }

    @Override
    public <T extends BaseModel> T query(HashMap<Property, Object> params, Class<T> clazz) {
        return dataBase.query(params, clazz);
    }
}
