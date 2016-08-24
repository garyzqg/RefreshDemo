package com.feicuiedu.refreshdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import in.srain.cube.views.ptr.PtrClassicFrameLayout;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.header.StoreHouseHeader;

public class MainActivity extends AppCompatActivity {

    private ListView listView;
    private ArrayList<String> data;

//    刷新
    private PtrClassicFrameLayout ptrFrameLayout;


    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //开始添加数据
        initData();

        //初始化布局
        initView();
    }

    private void initData() {
        data = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            data.add("测试数据"+i);
        }
    }

    private void initView() {
        listView = (ListView) findViewById(R.id.listView);
        ptrFrameLayout = (PtrClassicFrameLayout) findViewById(R.id.ptrframelayout);
        adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,data);
        listView.setAdapter(adapter);

        initRefresh();

        //下拉刷新的监听
        ptrFrameLayout.setPtrHandler(new PtrDefaultHandler() {
            //刷新
            @Override public void onRefreshBegin(PtrFrameLayout frame) {
                refresh();
            }
        });
    }

    //用来对刷新效果做一些改变
    //设置一些属性
    private void initRefresh() {

        //当前页面刷新间隔太近，就不进行触发刷新
        ptrFrameLayout.setLastUpdateTimeRelateObject(this);
        //设置关闭的时长
        ptrFrameLayout.setDurationToCloseHeader(2000);

        /**
         * 改变我们刷新的视图（刷新的样式）
         * Header 改变的刷新的样式
         */
        StoreHouseHeader header = new StoreHouseHeader(this);
        //设置刷新的视图以文字显示
        header.initWithString("I LIKE ANDROID");

        //把刷新的样式设置到ptrFrameLayout上
        ptrFrameLayout.setHeaderView(header);
        ptrFrameLayout.addPtrUIHandler(header);
    }

    private void refresh() {
        adapter.clear();
        for (int i = 0; i < 20; i++) {
            adapter.add("aaaa"+i);
        }
        adapter.notifyDataSetChanged();
        ptrFrameLayout.refreshComplete();
    }
}
