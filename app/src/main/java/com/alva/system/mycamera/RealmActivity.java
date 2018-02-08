package com.alva.system.mycamera;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.alva.system.mycamera.realmbean.RealmShop;
import com.alva.system.mycamera.realmbean.RealmShopListAdapter;
import com.alva.system.mycamera.realmbean.RealmUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/2/8.
 */

public class RealmActivity extends Activity implements View.OnClickListener {
    private Button bt_add, bt_delete, bt_update, bt_query;
    private ListView lv_content;
    private RealmShopListAdapter adapter;
    private List<RealmShop> shops;
    private static int i = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
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

    private void queryDate() {
        shops = new ArrayList<>();
        shops = RealmUtils.queryShop();
        Log.e("yangjie", "" + shops.size());
        adapter = new RealmShopListAdapter(this, shops);
        lv_content.setAdapter(adapter);

    }

    private void deleteDate() {
        if (!shops.isEmpty()) {
            RealmUtils.deleteShop(1);
            queryDate();
        }
    }

    private void addDate() {

        RealmUtils.insertShop();
        queryDate();
    }

    private void updateDate() {
        if (!shops.isEmpty()) {
            RealmShop shop = shops.get(0);
            shop.setName("安康蒸面");
            RealmUtils.updateShop(shop);
            queryDate();
        }
    }
}
