package com.alva.system.mycamera;

import android.app.Application;
import android.database.sqlite.SQLiteDatabase;

import com.alva.system.mycamera.greendaobean.DaoMaster;
import com.alva.system.mycamera.greendaobean.DaoSession;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by Administrator on 2018/2/7.
 */

public class App extends Application{
    private static DaoSession daoSession;

    @Override
    public void onCreate() {
        super.onCreate();
        //配置数据库
        setupDatabase();

        Realm.init(this);

        RealmConfiguration config = new  RealmConfiguration.Builder()
                .name("myRealm.realm")
                .deleteRealmIfMigrationNeeded()
                .build();
        Realm.setDefaultConfiguration(config);

    }

    private void setupDatabase() {
        //创建数据库
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this,"shop.db",null);
        //获取可写数据库
        SQLiteDatabase db = helper.getWritableDatabase();

        //获取数据库对象
        DaoMaster daoMaster = new DaoMaster(db);
        //获取Dao对象管理者
        daoSession = daoMaster.newSession();
    }
    public static DaoSession getDaoInstant(){
        return daoSession;
    }


    public static Realm getRealmInstance(){
        return Realm.getDefaultInstance();
    }
}
