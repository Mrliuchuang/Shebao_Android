package com.example.liuchuang.shebao.shebaochaxun;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.liuchuang.shebao.R;

public class yanglaobaoxian extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yanglaobaoxian);
    }



    public void kaishichaxun(View view) {
        Toast.makeText(this, "无此信息", Toast.LENGTH_SHORT).show();
    }

    public void backyanglao(View view) {
        finish();
    }
}
