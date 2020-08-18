package com.example.liuchuang.shebao.login;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.liuchuang.shebao.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


public class Zhuce extends AppCompatActivity implements View.OnClickListener {
    int retCode;
    String s;
    private EditText userName_et, passWord_et;
    private EditText mEditText, mEditText2;
    private Button Message_btn, login_btn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zhuce);


        mEditText2 = (EditText) findViewById(R.id.mima);
        login_btn = (Button) findViewById(R.id.login_btn);
        initView();
        initEvent();
    }

    private void initEvent() {
        login_btn.setOnClickListener(this);
        Message_btn.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {

        Log.e("MESSAGE:", "1");
        String userName = userName_et.getText().toString();
        String passWord = passWord_et.getText().toString();
        switch (v.getId()) {
            case R.id.Message_btn:
                Log.e("MESSAGE:", "2");
                if (userName.length() != 11) {
                    Toast.makeText(this, "请输入11位有效手机号码", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.login_btn:
                Log.e("MESSAGE:", "5");
                if (userName.length() == 0 || userName.length() != 11) {
                    Log.e("MESSAGE:", "6");
                    Toast.makeText(this, "手机号或验证码输入不合法", Toast.LENGTH_SHORT).show();
                } else {

                                //初始化okhttp客户端
                                OkHttpClient client = new OkHttpClient.Builder().build();
                                //创建post表单，获取username和password（没有做非空判断）
                                String user = userName_et.getText().toString();
                                String pass = mEditText2.getText().toString();
                                if (user.length() > 0 && pass.length() > 0) {
                                    RequestBody post = new FormBody.Builder()
                                            .add("username", user)
                                            .add("password", pass)
                                            .build();
                                    //开始请求，填入url，和表单
                                    final Request request = new Request.Builder()
                                            .url("http://39.106.34.160/myphp/Register.php")
                                            .post(post)
                                            .build();

                                    //客户端回调
                                    client.newCall(request).enqueue(new Callback() {
                                        @Override
                                        public void onFailure(Call call, IOException e) {
                                            //失败的情况（一般是网络链接问题，服务器错误等）
                                        }

                                        @Override
                                        public void onResponse(Call call, final Response response) throws IOException {
                                            //UI线程运行
                                            runOnUiThread(new Runnable() {
                                                @Override
                                                public void run() {
                                                    try {
                                                        //临时变量（这是okhttp的一个锅，一次请求的response.body().string()只能用一次，否则就会报错）
                                                        Zhuce.this.s = response.body().string();
                                                        //解析出后端返回的数据来
                                                        JSONObject jsonObject = new JSONObject(String.valueOf(Zhuce.this.s));
                                                        retCode = jsonObject.getInt("success");
                                                    } catch (JSONException e) {
                                                        e.printStackTrace();
                                                    } catch (IOException e) {
                                                        e.printStackTrace();
                                                    }

                                                    //客户端自己判断是否成功。
                                                    if (retCode == 1) {
                                                        Toast.makeText(Zhuce.this, "账号注册成功!", Toast.LENGTH_SHORT).show();
                                                        Intent intent = new Intent(Zhuce.this, Login.class);
                                                        startActivity(intent);
                                                    } else {
                                                        Toast.makeText(Zhuce.this, "账号注册成功!", Toast.LENGTH_SHORT).show();
                                                    }
                                                }
                                            });
                                        }
                                    });
                                } else {
                                    Toast.makeText(Zhuce.this, "手机号输入不合法!", Toast.LENGTH_SHORT).show();
                                }


                                //通过验证，上传数据库信息
//                                Toast.makeText(Zhuce.this, "登录成功", Toast.LENGTH_SHORT).show();
//                                Intent intent = new Intent(Zhuce.this, MainActivity.class);
//                                startActivity(intent);
                                Zhuce.this.finish();
                            }

                }

        }



        private void initView() {
            userName_et = (EditText) findViewById(R.id.userName_et);
            passWord_et = (EditText) findViewById(R.id.passWord_et);
            Message_btn = (Button) findViewById(R.id.Message_btn);
            login_btn = (Button) findViewById(R.id.login_btn);
        }
}

