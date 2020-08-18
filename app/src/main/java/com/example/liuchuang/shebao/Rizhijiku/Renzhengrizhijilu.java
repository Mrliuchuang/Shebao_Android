package com.example.liuchuang.shebao.Rizhijiku;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.liuchuang.shebao.R;
import com.example.liuchuang.shebao.VideoChatViewActivity;
import com.example.liuchuang.shebao.xListView.XListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Renzhengrizhijilu extends Activity implements XListView.IXListViewListener {

    private XListView mListView;
    private Handler mHandler;
    private MyAdapter myAdapter;
    List<ImageEntry> mList;
    //要显示的参数及图片地址
    public String[] urls = new String[]{"http://39.106.34.160/shebao/20190325181542.jpg", "http://39.106.34.160/shebao/20190325181831.jpg"};
    public String[] time = new String[]{"2017.08.01 11.11", "2017.08.10 11.2"};
    public Long[] id = new Long[]{3L, 4L};

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_renzhengrizhijilu);
        mListView = (XListView) findViewById(R.id.xlistview);
        //mainAdapter = new MainAdapter(this);//实例化一个适配器对象
        TextView backsheherizhi = findViewById(R.id.backsheherizhi);
        backsheherizhi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        //实例化一个图片数组列表对象
        mList = new ArrayList<ImageEntry>();
        //datas = new ArrayList<>();
        mHandler = new Handler();
        //设置数据
        geneItems();
        //为xListView设置一些配置和适配器
        mListView.setPullLoadEnable(true);
        mListView.setAdapter(myAdapter);
        mListView.setXListViewListener(this);


        //设置item的点击事件
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //这里的i-1，是因为头视图占了一个position
                //Toast.makeText(MainActivity.this, "i:" + (i - 1), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Renzhengrizhijilu.this, VideoChatViewActivity.class);
                startActivity(intent);
//                //这里我修改为显示items的id，在MyAdapter的getItemId处修改
//                Toast.makeText(Renzhengrizhijilu.this, "你点击的item的id是:" +l, Toast.LENGTH_SHORT).show();
            }
        });
//        //设置长按事件
//        mListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
//            @Override
//            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
//                Toast.makeText(Renzhengrizhijilu.this, "长按事件 i:" + (i-1), Toast.LENGTH_SHORT).show();
//                return false;
//            }
//        });

        /*
        //添加头布局
        View view = getLayoutInflater().inflate(R.layout.head_view, null);
        mListView.addHeaderView(view);
        //添加尾视图
        View view2 = getLayoutInflater().inflate(R.layout.head_view, null);
        mListView.addFooterView(view2);
        */

        //设置禁止上拉
        //mListView.setPullLoadEnable(false);
        //设置禁止下拉
        //mListView.setPullRefreshEnable(false);

    }

    //为图片数组列表设置值并为适配器传递数组列表即传递数据,且还实例化了一个myAdapt对象
    private void geneItems() {
//        for(int i=0; i<2; i++){
//            ImageEntry entry = new ImageEntry();
//            entry.seturl(urls[i]);//为ImageEntry中url赋值
//            entry.settime(time[i]);
//            entry.setid(id[i]);
//            mList.add(entry);
//        }
        RequestParams params = new RequestParams("http://39.106.34.160/shebao/tijiaochakan.php");
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                try {
                    JSONArray jsonArray = new JSONArray(result);
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        String touxiang = jsonObject.getString("touxianglujing");
                        String name = jsonObject.getString("name");
                        String sfzh = jsonObject.getString("shenfenzhenghao");
                        String sjh = jsonObject.getString("shoujihao");
//                        int id = jsonObject.getInt("id");
                        ImageEntry entry = new ImageEntry();
                        entry.seturl(touxiang);//为ImageEntry中url赋值
                        entry.setname(name);
                        entry.setlianxifangshi(sjh);
                        entry.setshenfenzhenghao(sfzh);
                        mList.add(entry);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });


        myAdapter = new MyAdapter(this, mList);
        //为MyAdapter中xlist_v赋值
        myAdapter.SetXListView(mListView);
    }


    //下拉刷新
    @Override
    public void onRefresh() {
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {

                mList.clear();//清空数组列表变量
                geneItems();

                mListView.setAdapter(myAdapter);
                onLoad();
            }
        }, 2000);
    }

    //上拉加载
    @Override
    public void onLoadMore() {
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // geneItems();
                //mainAdapter.notifyDataSetChanged();
                myAdapter.notifyDataSetChanged();
                onLoad();
            }
        }, 2000);
    }

    //停止更新和加载并重置头尾部view。获取时间。
    private void onLoad() {
        mListView.stopRefresh();
        mListView.stopLoadMore();
        //获取当前时间
        Date curDate = new Date(System.currentTimeMillis());
        //格式化
        SimpleDateFormat formatter = new SimpleDateFormat();
        String time = formatter.format(curDate);
        mListView.setRefreshTime(time);
    }
}

