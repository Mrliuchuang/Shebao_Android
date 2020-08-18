package com.example.liuchuang.shebao.SQLite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

/**
 * Created by Administrator on 2019/3/10 0010.
 */

public class MyDatabaseHelper extends SQLiteOpenHelper {
    public static final String CREATE_JTCY = "CREATE TABLE jiatingchengyuan ("
            + "id  integer PRIMARY KEY Autoincrement ,"
            + "name text ,"
            + "shenfen text ,"
            + "shenfenzhenghao text )";
    /**
     * integer：整形
     * real：浮点型
     * text：文本类型
     * blob：二进制类型
     * PRIMARY KEY将id列设置为主键
     * AutoIncrement关键字表示id列是自动增长的
     */

    private Context myContent;

    public MyDatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        myContent = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //创建数据库的同时创建Book表
        db.execSQL(CREATE_JTCY);
        //提示数据库创建成功
        Toast.makeText(myContent, "数据库创建成功", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
