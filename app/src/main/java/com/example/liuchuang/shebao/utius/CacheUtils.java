package com.example.liuchuang.shebao.utius;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Environment;
import android.support.v4.app.FragmentActivity;

import org.xutils.common.util.LogUtil;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;


public class CacheUtils {


    public static void putString(FragmentActivity activity, String key, String value) {
        SharedPreferences sp = activity.getSharedPreferences("atguigu", Context.MODE_PRIVATE);
        sp.edit().putString(key, value).commit();
    }

    public static String getString(FragmentActivity activity, String key) {
        SharedPreferences sp = activity.getSharedPreferences("atguigu", Context.MODE_PRIVATE);
        return  sp.getString(key, "");
    }
    public static String getString2(FragmentActivity activity, String key) {
        SharedPreferences sp = activity.getSharedPreferences("atguigu", Context.MODE_PRIVATE);
        return  sp.getString(key, "");
    }
    public static String getString3(FragmentActivity activity, String key) {
        SharedPreferences sp = activity.getSharedPreferences("atguigu", Context.MODE_PRIVATE);
        return  sp.getString(key, "");
    }
}
