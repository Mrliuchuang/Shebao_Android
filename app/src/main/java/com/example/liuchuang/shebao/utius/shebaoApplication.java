package com.example.liuchuang.shebao.utius;

import android.app.Application;

import org.xutils.x;

/**
 * Created by Administrator on 2019/3/9 0009.
 */

public class shebaoApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        x.Ext.setDebug(true);
        x.Ext.init(this);
    }
}