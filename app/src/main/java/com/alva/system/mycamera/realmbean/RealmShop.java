package com.alva.system.mycamera.realmbean;


import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Administrator on 2018/2/8.
 */

public class RealmShop extends RealmObject{
    //表示为购物车列表
    public static final int TYPE_CART = 0x01;
    //表示为收藏列表
    public static final String TYPE_LOVE = "type";
    //不能用int
    @PrimaryKey
    private Long id;
    //商品名称
    private String name;
    //商品价格
    private String price;
    //已售数量
    private int sell_unm;
    //图标url
    private String image_url;
    //商家地址
    private String address;
    //商品列表类型
    private String type;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public int getSell_unm() {
        return sell_unm;
    }

    public void setSell_unm(int sell_unm) {
        this.sell_unm = sell_unm;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
