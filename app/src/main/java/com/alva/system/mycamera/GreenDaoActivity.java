package com.alva.system.mycamera;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.alva.system.mycamera.greendaobean.CartDao;
import com.alva.system.mycamera.greendaobean.Shop;
import com.alva.system.mycamera.greendaobean.ShopListAdapter;

import java.util.ArrayList;
import java.util.List;

public class GreenDaoActivity extends AppCompatActivity implements View.OnClickListener{
    /**
     * GreenDao
     * 操作数据库
     * @param savedInstanceState
     */

    private Button bt_add, bt_delete, bt_update, bt_query;
    private ListView lv_content;
    private ShopListAdapter adapter;
    private List<Shop> shops;
    private static int i = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_green_dao);
        initView();
    }

    private void initView() {
        bt_add = (Button) findViewById(R.id.bt_add);
        bt_delete = (Button) findViewById(R.id.bt_delete);
        bt_update = (Button) findViewById(R.id.bt_update);
        bt_query = (Button) findViewById(R.id.bt_query);
        lv_content = (ListView) findViewById(R.id.lv_content);
        bt_add.setOnClickListener(this);
        bt_delete.setOnClickListener(this);
        bt_update.setOnClickListener(this);
        bt_query.setOnClickListener(this);
        queryDate();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_add:
                addDate();
                break;
            case R.id.bt_delete:
                deleteDate();
                break;
            case R.id.bt_update:
                updateDate();
                break;
            case R.id.bt_query:
                queryDate();
                break;
        }
    }

    private void queryDate(){
        shops = new ArrayList<>();
        shops = CartDao.queryShop();
        Log.e("yangjie","" + shops.size());
        adapter = new ShopListAdapter(this,shops);
        lv_content.setAdapter(adapter);

    }

    private void deleteDate(){
        if(!shops.isEmpty()){
            CartDao.deleteShop(shops.get(0).getId());
            queryDate();
        }
    }

    private void addDate() {
        Shop shop = new Shop();
        shop.setType(Shop.TYPE_LOVE);
        shop.setAddress("陕西安康");
        shop.setImage_url("http://onyxd5cd8.bkt.clouddn.com/greendaozhengmian_02.png");
        shop.setPrice("5.00");
        shop.setSell_unm(15263);
        shop.setName("安康正宗蒸面，香辣可口，其乃天下之美食耶" + i++);
        CartDao.insertShop(shop);
        queryDate();
    }

    private void updateDate() {
        if (!shops.isEmpty()) {
            Shop shop = shops.get(0);
            shop.setName("安康蒸面");
            CartDao.updateShop(shop);
            queryDate();
        }
    }
}
