package com.example.liuchuang.shebao;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.liuchuang.shebao.SQLite.MyDatabaseHelper;

import java.util.ArrayList;
import java.util.List;

public class Chengyuanxinxi extends AppCompatActivity {
    private TextView back3;
    private ListView listView;
    private MyDatabaseHelper dbHelper;
    List<ItemBean> datelist = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chengyuanxinxi);
        back3 = findViewById(R.id.back3);
        back3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Chengyuanxinxi.this,MainActivity.class);
                startActivity(intent);
            }
        });
        //构建一个 MyDatabaseHelper 对象，通过构造函数将数据库名指定为 BookStore.db
        dbHelper = new MyDatabaseHelper(this, "Jiating.db", null, 1);

        listView = findViewById(R.id.lv_main);

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        //查询Book表中的所有数据
        Cursor cursor = db.query("jiatingchengyuan", null, null, null, null, null, null, null);
        //遍历Curosr对象，取出数据并打印
        while (cursor.moveToNext()) {

            String name = cursor.getString(cursor.getColumnIndex("name"));
            String shenfen = cursor.getString(cursor.getColumnIndex("shenfen"));
            String shenfenzhenghao = cursor.getString(cursor.getColumnIndex("shenfenzhenghao"));
            Log.e("woider", "Book Name:" + name + " Author:"
                    + shenfen);
            datelist.add(new ItemBean(name, shenfen, shenfenzhenghao));
        }
//        for (int i = 0; i < datelist.size(); i++) {
            // 设置适配器
            Myadapter myadapter = new Myadapter(Chengyuanxinxi.this, datelist);
            listView.setAdapter(myadapter);

//        }
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                String showText = "点击第" + position +  "，ID为：" + l;
                Intent intent = new Intent(Chengyuanxinxi.this,Chengyuanxinxi_xiugai.class);
                Toast.makeText(Chengyuanxinxi.this, showText, Toast.LENGTH_SHORT).show();
                Bundle bundle=new Bundle();
                bundle.putInt("ID", position+1);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }
//        //关闭Cursor
//        cursor.close();


//        List<ItemBean> datelist = new ArrayList<>();
//        for (int i = 0; i < 20; i++) {
//            datelist.add(new ItemBean("我是" + i, "我是标题" + i, "我是内容" + i));
//            // 设置适配器
//            Myadapter myadapter = new Myadapter(Chengyuanxinxi.this, datelist);
//            listView.setAdapter(myadapter);
//        }


    public void jiatingchengyuantianjia(View view) {
        Intent intent = new Intent(Chengyuanxinxi.this, Chengyuanxiangqing.class);
        startActivity(intent);
    }

}
