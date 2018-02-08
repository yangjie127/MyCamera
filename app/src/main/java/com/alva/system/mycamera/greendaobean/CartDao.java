package com.alva.system.mycamera.greendaobean;

import com.alva.system.mycamera.App;

import java.util.List;

/**
 * Created by Administrator on 2018/2/8.
 */

public class CartDao {
    /**
     * 添加数据，如果有重复则覆盖
     */
    public static void insertShop(Shop shop){
        App.getDaoInstant().getShopDao().insertOrReplace(shop);
    }

    /**
     * 删除数据
     * @param id
     */
    public static void deleteShop(long id){
        App.getDaoInstant().getShopDao().deleteByKey(id);
    }

    /**
     * 更新数据
     * @param shop
     */
    public static void updateShop(Shop shop){
        App.getDaoInstant().getShopDao().update(shop);
    }

    /**
     * 查询条件为TYPE_LOVE的数据
     * @return
     */
    public static List<Shop>queryShop(){
        return App.getDaoInstant().getShopDao().queryBuilder().where(ShopDao.Properties.Type.eq(Shop.TYPE_LOVE)).list();
    }

    /**
     * 查询全部数据
     */
    public static List<Shop> queryAll(){
        return App.getDaoInstant().getShopDao().loadAll();
    }

}
