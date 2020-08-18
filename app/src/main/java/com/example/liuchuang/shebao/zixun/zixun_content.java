package com.example.liuchuang.shebao.zixun;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;


import com.example.liuchuang.shebao.R;

import com.google.gson.Gson;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

public class zixun_content extends AppCompatActivity {
    String uRl;
    //声明引用
    ProgressDialog dialog;
    private WebView mWVmhtml;
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zixun_content);
        dialog = new ProgressDialog(zixun_content.this);
        dialog.setMessage("正在加载。。。。。。");
        dialog.show();
        Bundle bundle = this.getIntent().getExtras();//跳转传值
        uRl = bundle.getString("ID");
        //  Toast.makeText(this, ID , Toast.LENGTH_SHORT).show();

        jiazaiwangluo();
        dialog.dismiss();


    }
    private void jiazaiwangluo() {
        //获取控件对象
        mWVmhtml = (WebView) findViewById(R.id.WV_Id);
        //加载本地html文件
        // mWVmhtml.loadUrl("file:///android_asset/hello.html");
        //加载网络URL
        //mWVmhtml.loadUrl("https://blog.csdn.net/qq_36243942/article/details/82187204");
        //设置JavaScrip
        mWVmhtml.getSettings().setJavaScriptEnabled(true);
        //访问百度首页
        mWVmhtml.loadUrl(uRl);
        //设置在当前WebView继续加载网页
        //   mWVmhtml.setWebViewClient(new MyWebViewClient());
    }

    public void wangyefinsh(View view) {
        finish();
    }
}

class MyWebViewClient extends WebViewClient {
    @Override  //WebView代表是当前的WebView
    public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
//        //表示在当前的WebView继续打开网页
        view.loadUrl(request.getUrl().toString());
        return true;
    }

    @Override
    public void onPageStarted(WebView view, String url, Bitmap favicon) {
        super.onPageStarted(view, url, favicon);
        Log.d("WebView", "开始访问网页");
    }

    @Override
    public void onPageFinished(WebView view, String url) {
        super.onPageFinished(view, url);
        Log.d("WebView", "访问网页结束");
    }
}

