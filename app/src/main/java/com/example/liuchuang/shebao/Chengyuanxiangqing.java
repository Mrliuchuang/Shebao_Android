package com.example.liuchuang.shebao;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.liuchuang.shebao.SQLite.MyDatabaseHelper;

public class Chengyuanxiangqing extends AppCompatActivity {
    private MyDatabaseHelper dbHelper;
    private EditText name1;
    private EditText shenfen1;
    private EditText IDcard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chengyuanxiangqing);
        name1 = findViewById(R.id.name1);
        shenfen1 = findViewById(R.id.shenfen1);
        IDcard = findViewById(R.id.IDcard);
        //构建一个 MyDatabaseHelper 对象，通过构造函数将数据库名指定为 BookStore.db
        dbHelper = new MyDatabaseHelper(this, "Jiating.db", null, 1);
    }

    public void baocun(View view) {
        String n = name1.getText().toString();
        String s = shenfen1.getText().toString();
        String I = IDcard.getText().toString();
        if (IDcard.length() == 18 && name1.length() > 0 && shenfen1.length() > 0) {

            dbHelper.getWritableDatabase();
            //获取 SQLiteDatabase 对象
            SQLiteDatabase db = dbHelper.getWritableDatabase();
            //使用ContentValues 对数据进行组装
            ContentValues values = new ContentValues();
            //开始组装第一条数据
            values.put("name", n);
            values.put("shenfen", s);
            values.put("shenfenzhenghao", I);

            //插入第一条数据
            db.insert("jiatingchengyuan", null, values);
            values.clear();
            Toast.makeText(this, "添加成功", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(Chengyuanxiangqing.this, Chengyuanxinxi.class);
            startActivity(intent);
        } else {
            Toast.makeText(this, "输入有误，请重新输入", Toast.LENGTH_SHORT).show();
        }
    }

    public void back(View view) {
        this.finish();
    }
}
