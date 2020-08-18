package com.example.liuchuang.shebao;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.liuchuang.shebao.SQLite.MyDatabaseHelper;

public class Chengyuanxinxi_xiugai extends AppCompatActivity {
    private TextView back1;
    private TextView delet;
    private MyDatabaseHelper dbHelper;
    private Button xiugai1;
    String int1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chengyuanxinxi_xiugai);
        dbHelper = new MyDatabaseHelper(this, "Jiating.db", null, 1);
        back1 = findViewById(R.id.back1);
        delet = findViewById(R.id.delet);

        xiugai1 = findViewById(R.id.xiugai1);
        xiugai1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(Chengyuanxinxi_xiugai.this, "等待", Toast.LENGTH_SHORT).show();
            }
        });
        back1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Chengyuanxinxi_xiugai.this.finish();
                Toast.makeText(Chengyuanxinxi_xiugai.this, "back", Toast.LENGTH_SHORT).show();
            }
        });

        Bundle bundle = this.getIntent().getExtras();//跳转传值
        int ID = bundle.getInt("ID");
        int1 = String.valueOf(ID);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        //查询Book表中的所有数据
        Toast.makeText(this, int1, Toast.LENGTH_SHORT).show();
        Cursor cursor = db.query("jiatingchengyuan", new String[]{"id", "name", "shenfen", "shenfenzhenghao"}, "id=?", new String[]{int1}, null, null, null);
        //遍历Curosr对象，取出数据并打印
        while (cursor.moveToNext()) {
            String name = cursor.getString(cursor.getColumnIndex("name"));
            String shenfen = cursor.getString(cursor.getColumnIndex("shenfen"));
            String shenfenzhenghao = cursor.getString(cursor.getColumnIndex("shenfenzhenghao"));
            EditText textView1 = findViewById(R.id.name_xiugai);
            EditText textView2 = findViewById(R.id.shenfen_xiugai);
            EditText textView3 = findViewById(R.id.IDcard_xiugai);
            textView1.setText(name);
            textView2.setText(shenfen);
            textView3.setText(shenfenzhenghao);
        }
        //关闭Cursor
        cursor.close();
        delet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                db.delete("jiatingchengyuan", "id = ?", new String[]{int1});
                Toast.makeText(Chengyuanxinxi_xiugai.this, "删除成功", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Chengyuanxinxi_xiugai.this, Chengyuanxinxi.class);
                startActivity(intent);
            }
        });
    }
}
