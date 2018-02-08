package com.alva.system.mycamera.realmbean;

import com.alva.system.mycamera.App;
import com.alva.system.mycamera.greendaobean.Shop;
import com.alva.system.mycamera.greendaobean.ShopDao;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by Administrator on 2018/2/8.
 */

public class RealmUtils {

   static  long i =10;
    /**
     * 添加数据，如果有重复则覆盖
     */
    public static void insertShop() {
        // App.getDaoInstant().getShopDao().insertOrReplace(shop);
        final RealmShop shop1 = new RealmShop();
        shop1.setType("type");
        shop1.setAddress("陕西安康");
        shop1.setImage_url("http://onyxd5cd8.bkt.clouddn.com/greendaozhengmian_02.png");
        shop1.setPrice("5.00");
        shop1.setSell_unm(15263);
        shop1.setName("安康正宗蒸面，香辣可口，其乃天下之美食耶" );
        shop1.setId(++i);
        App.getRealmInstance().executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.copyToRealm(shop1);
            }
        });
//        i++;
    }

    /**
     * 删除数据
     *
     * @param id
     */
    public static void deleteShop(long id) {
        //  App.getDaoInstant().getShopDao().deleteByKey(id);

        App.getRealmInstance().executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                RealmResults<RealmShop> realmShops = realm.where(RealmShop.class).findAll();

                // remove a single object  删除某一个对象
//                RealmShop shop =  realmShops.get(1);
//                shop.deleteFromRealm();

                // remove single match  删除单个匹配
                realmShops.deleteFirstFromRealm();  // 删除第一个
                //realmShops.deleteLastFromRealm();    删除最后一个

                // Delete all matches   删除所有匹配
                // realmShops.deleteAllFromRealm();
            }
        });

    }

    /**
     * 更新数据
     *
     * @param shop
     */
    public static void updateShop(final RealmShop shop) {
        // App.getDaoInstant().getShopDao().update(shop);
        App.getRealmInstance().executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.copyToRealmOrUpdate(shop);
            }
        });
    }

    /**
     * 查询条件为TYPE_LOVE的数据
     *
     * @return
     */
    public static List<RealmShop> queryShop() {
//        return App.getDaoInstant().getShopDao().queryBuilder().where(ShopDao.Properties.Type.eq(Shop.TYPE_LOVE)).list();
        Realm realm = App.getRealmInstance();
        RealmResults<RealmShop> puppies = realm.where(RealmShop.class).equalTo("type",RealmShop.TYPE_LOVE).findAll();
        puppies.size();
        return realm.copyFromRealm(puppies);
    }

    /**
     * 查询全部数据
     */
    public static List<RealmShop> queryAll() {
//        return App.getDaoInstant().getShopDao().loadAll();

        Realm realm = App.getRealmInstance();
        RealmResults<RealmShop> puppies = realm.where(RealmShop.class).findAll();
        puppies.size();
        return realm.copyFromRealm(puppies);
    }
}
